package com.example.demo.service.impl;

import com.example.demo.entity.QStudent;
import com.example.demo.entity.Relation;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.RelationRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.CommonPage;
import com.example.demo.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private static final int INT_ONE = 1;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public int sumStudentByStuNo(String stuNo) {
        return studentRepository.countStudentByStuNo(stuNo);
    }

    @Override
    public CommonPage<QStudent> findAllByStuNo(String stuNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("StudentServiceImpl findAllByStuNo enter with { stuNo : " + stuNo + ",pageSize : " + pageSize + ", pageNum : " + pageNum + "}");
        int num = studentRepository.countStudentByStuNo(stuNo);
        if (num != INT_ONE) {
            LOGGER.error("StudentService findAllByStuNo : 用户信息错误");
            throw new DemoException("用户信息错误");
        }
        Student student = studentRepository.findStudentByStuNo(stuNo);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<QStudent> criteriaQuery = criteriaBuilder.createQuery(QStudent.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("relation", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class).alias("subjectName"),
                relation.get("stuYear").as(String.class).alias("stuYear"),
                relation.get("score").as(BigDecimal.class).alias("minScore")

        );

        Predicate predicate = criteriaBuilder.equal(relation.get("stuId"), student.getId());
        criteriaQuery.where(predicate);

        TypedQuery<QStudent> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<QStudent> totalList = query.getResultList();
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<QStudent> resultList = query.getResultList();
        CommonPage<QStudent> page = CommonPage.restPage(resultList, pageNum, pageSize, totalList.size());
        LOGGER.info("StudentServiceImpl findAllByStuNo exit with page : " + page);
        return page;
    }

    @Override
    public List<Relation> findAll() {
        LOGGER.info("StudentServiceImpl findAll enter");
        Object obj = redisUtil.get("demo_stuFindAll");
        LOGGER.info("StudentServiceImpl findAll 读取redis数据为：" + obj);
        if (obj != null) {
            return (List<Relation>) obj;
        } else {
            List<Relation> all = relationRepository.findAll();
            redisUtil.set("demo_stuFindAll", all, 60 * 5);
            LOGGER.info("StudentServiceImpl findAll 从数据库重新获取值");
            return all;
        }
    }
}
