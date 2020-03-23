package com.example.demo.repository.impl;

import com.example.demo.entity.StudentDO;
import com.example.demo.entity.SubjectDO;
import com.example.demo.entity.SubjectScoreDO;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.StudentCustomRepository;
import com.example.demo.repository.StudentRepository;
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

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<SubjectScoreDO> findAllByStuNo(String stuNo, int pageSize, int pageNum) throws DemoException {
        StudentDO studentDO = studentRepository.findStudentByStuNo(stuNo);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectScoreDO> criteriaQuery = criteriaBuilder.createQuery(SubjectScoreDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelationDOs", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class),
                relation.get("stuYear").as(String.class),
                relation.get("score").as(BigDecimal.class)
        );

        Predicate predicate = criteriaBuilder.equal(relation.get("stuId"), studentDO.getId());
        criteriaQuery.where(predicate);

        TypedQuery<SubjectScoreDO> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<SubjectScoreDO> subjectScoreDOs = query.getResultList();
        return subjectScoreDOs;
    }

    @Override
    public int sumResultByStuNo(String stuNo) {
        StudentDO studentDO = studentRepository.findStudentByStuNo(stuNo);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectScoreDO> criteriaQuery = criteriaBuilder.createQuery(SubjectScoreDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelationDOs", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class),
                relation.get("stuYear").as(String.class),
                relation.get("score").as(BigDecimal.class)
        );

        Predicate predicate = criteriaBuilder.equal(relation.get("stuId"), studentDO.getId());
        criteriaQuery.where(predicate);

        TypedQuery<SubjectScoreDO> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<SubjectScoreDO> subjectScoreDOs = query.getResultList();
        return subjectScoreDOs.size();
    }
}
