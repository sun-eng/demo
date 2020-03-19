package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.RelationRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;
import com.example.demo.util.CommonPage;
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
public class TeacherServiceImpl implements TeacherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private static final String STR_ONE = "1";

    @PersistenceContext
    private EntityManager em;

    private static final int INT_ONE = 1;

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public CommonPage<QStubjectYear> findScoreByYear(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByYear enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByYear : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.error("TeacherServiceImpl findScoreByYear : 无查询权限");
            throw new DemoException("无查询权限");
        }

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<QStubjectYear> criteriaQuery = criteriaBuilder.createQuery(QStubjectYear.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("relation", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class).alias("subjectName"),
                relation.get("stuYear").as(String.class).alias("stuYear"),
                criteriaBuilder.avg(relation.get("score").as(BigDecimal.class)),
                criteriaBuilder.max(relation.get("score").as(BigDecimal.class)),
                criteriaBuilder.min(relation.get("score").as(BigDecimal.class))

        );

        criteriaQuery.groupBy(
                root.get("subNo").as(String.class),
                root.get("name").as(String.class),
                relation.get("stuYear").as(String.class)
        );

        TypedQuery<QStubjectYear> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<QStubjectYear> totalList = query.getResultList();
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<QStubjectYear> resultList = query.getResultList();
        CommonPage<QStubjectYear> page = CommonPage.restPage(resultList, pageNum, pageSize, totalList.size());
        LOGGER.info("TeacherServiceImpl findScoreByYear exit with page : " + page);
        return page;
    }


    @Override
    public CommonPage<QSubjectTeacher> findScoreByTea(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByTea : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.error("TeacherServiceImpl findScoreByTea : 无查询权限");
            throw new DemoException("无查询权限");
        }
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<QSubjectTeacher> criteriaQuery = criteriaBuilder.createQuery(QSubjectTeacher.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> teacherJoin = root.join("teacher", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("relation", JoinType.LEFT);
        criteriaQuery.multiselect(
                teacherJoin.get("name").as(String.class).alias("teacherName"),
                root.get("name").as(String.class).alias("subjectName"),
                criteriaBuilder.avg(relation.get("score").as(BigDecimal.class)),
                criteriaBuilder.max(relation.get("score").as(BigDecimal.class)),
                criteriaBuilder.min(relation.get("score").as(BigDecimal.class))

        );

        Predicate predicate = criteriaBuilder.equal(teacherJoin.get("isAdmin"), "0");
        criteriaQuery.where(predicate);

        criteriaQuery.groupBy(
                root.get("name").as(String.class),
                teacherJoin.get("name").as(String.class),
                relation.get("subId").as(Long.class),
                relation.get("teaId").as(Long.class)
        );

        TypedQuery<QSubjectTeacher> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<QSubjectTeacher> totalList = query.getResultList();
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<QSubjectTeacher> resultList = query.getResultList();
        CommonPage<QSubjectTeacher> page = CommonPage.restPage(resultList, pageNum, pageSize, totalList.size());
        LOGGER.info("TeacherServiceImpl findScoreByTea exit with page : " + page);
        return page;
    }


    @Override
    public CommonPage<QTeacherYear> findScoreByTeaNo(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByTeaNo : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<QTeacherYear> criteriaQuery = criteriaBuilder.createQuery(QTeacherYear.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> teacherJoin = root.join("teacher", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("relation", JoinType.LEFT);
        criteriaQuery.multiselect(
                teacherJoin.get("name").as(String.class).alias("teacherName"),
                root.get("name").as(String.class).alias("subjectName"),
                relation.get("stuYear").as(String.class).alias("studyYear"),
                criteriaBuilder.avg(relation.get("score").as(BigDecimal.class)),
                criteriaBuilder.max(relation.get("score").as(BigDecimal.class)),
                criteriaBuilder.min(relation.get("score").as(BigDecimal.class))

        );

        Predicate predicate = criteriaBuilder.equal(teacherJoin.get("teaNo"), teaNo);
        criteriaQuery.where(predicate);

        criteriaQuery.groupBy(
                root.get("name").as(String.class),
                teacherJoin.get("name").as(String.class),
                relation.get("subId").as(Long.class),
                relation.get("teaId").as(Long.class),
                relation.get("stuYear").as(String.class)
        );

        TypedQuery<QTeacherYear> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<QTeacherYear> totalList = query.getResultList();
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<QTeacherYear> resultList = query.getResultList();
        CommonPage<QTeacherYear> page = CommonPage.restPage(resultList, pageNum, pageSize, totalList.size());
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo exit with page : " + page);
        return page;
    }
}
