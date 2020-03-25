package com.nhsoft.module.demo.data.repository.impl;

import com.nhsoft.module.demo.data.model.Student;
import com.nhsoft.module.demo.data.model.Subject;
import com.nhsoft.module.demo.data.model.SubjectScore;
import com.nhsoft.module.demo.data.repository.StudentCustomRepository;
import com.nhsoft.module.demo.data.repository.StudentRepository;
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
    public List<SubjectScore> findAllByStuNo(String stuNo, Integer offset, Integer limit) {
        Student student = studentRepository.findStudentByStuNo(stuNo);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectScore> criteriaQuery = criteriaBuilder.createQuery(SubjectScore.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelations", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class),
                relation.get("stuYear").as(String.class),
                relation.get("score").as(BigDecimal.class)
        );

        Predicate predicate = criteriaBuilder.equal(relation.get("stuId"), student.getId());
        criteriaQuery.where(predicate);

        TypedQuery<SubjectScore> query = em.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        // 获取分页结果集
        List<SubjectScore> subjectScores = query.getResultList();
        return subjectScores;
    }

    @Override
    public int sumResultByStuNo(String stuNo) {
        Student student = studentRepository.findStudentByStuNo(stuNo);
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectScore> criteriaQuery = criteriaBuilder.createQuery(SubjectScore.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelations", JoinType.LEFT);
        criteriaQuery.multiselect(
                root.get("name").as(String.class),
                relation.get("stuYear").as(String.class),
                relation.get("score").as(BigDecimal.class)
        );

        Predicate predicate = criteriaBuilder.equal(relation.get("stuId"), student.getId());
        criteriaQuery.where(predicate);

        TypedQuery<SubjectScore> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<SubjectScore> subjectScores = query.getResultList();
        return subjectScores.size();
    }
}
