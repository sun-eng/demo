package com.example.demo.repository.impl;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.StudentCustomRepository;
import com.example.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class StudentCustomRepositoryImpl implements StudentCustomRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentCustomRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> findAllByStuNo(String stuNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("StudentCustomRepositoryImpl findAllByStuNo enter with { stuNo : " + stuNo + ",pageSize : " + pageSize + ", pageNum : " + pageNum + "}");
        Student student = studentRepository.findStudentByStuNo(stuNo);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<StudentDto> criteriaQuery = criteriaBuilder.createQuery(StudentDto.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelations", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class),
                relation.get("stuYear").as(String.class),
                relation.get("score").as(BigDecimal.class)
        );

        Predicate predicate = criteriaBuilder.equal(relation.get("stuId"), student.getId());
        criteriaQuery.where(predicate);

        TypedQuery<StudentDto> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<StudentDto> resultList = query.getResultList();
        LOGGER.info("StudentCustomRepositoryImpl findAllByStuNo exit with resultList : " + resultList);
        return resultList;
    }

    @Override
    public int sumResultByStuNo(String stuNo) {
        LOGGER.info("StudentCustomRepositoryImpl sumResultByStuNo enter with { stuNo : " + stuNo + "}");
        Student student = studentRepository.findStudentByStuNo(stuNo);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<StudentDto> criteriaQuery = criteriaBuilder.createQuery(StudentDto.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelations", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class),
                relation.get("stuYear").as(String.class),
                relation.get("score").as(BigDecimal.class)
        );

        Predicate predicate = criteriaBuilder.equal(relation.get("stuId"), student.getId());
        criteriaQuery.where(predicate);

        TypedQuery<StudentDto> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<StudentDto> totalList = query.getResultList();
        LOGGER.info("StudentCustomRepositoryImpl sumResultByStuNo exit with size : " + totalList.size());
        return totalList.size();
    }
}
