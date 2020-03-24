package com.example.demo.repository.impl;

import com.example.demo.entity.Subject;
import com.example.demo.entity.SubjectTeacher;
import com.example.demo.entity.SubjectYear;
import com.example.demo.entity.TeacherYear;
import com.example.demo.repository.TeacherCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class TeacherCustomRepositoryImpl implements TeacherCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<SubjectYear> findScoreByYear(Integer offset, Integer limit) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectYear> criteriaQuery = criteriaBuilder.createQuery(SubjectYear.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelations", JoinType.LEFT);
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

        TypedQuery<SubjectYear> query = em.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        // 获取分页结果集
        List<SubjectYear> subjectYears = query.getResultList();
        return subjectYears;
    }

    @Override
    public int sumResultByYear() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectYear> criteriaQuery = criteriaBuilder.createQuery(SubjectYear.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelations", JoinType.LEFT);
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

        TypedQuery<SubjectYear> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<SubjectYear> subjectYears = query.getResultList();
        return subjectYears.size();
    }

    @Override
    public List<SubjectTeacher> findScoreByTea(Integer offset, Integer limit) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectTeacher> criteriaQuery = criteriaBuilder.createQuery(SubjectTeacher.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> teacherJoin = root.join("teachers", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelations", JoinType.LEFT);
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

        TypedQuery<SubjectTeacher> query = em.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        // 获取分页结果集
        List<SubjectTeacher> subjectTeachers = query.getResultList();
        return subjectTeachers;
    }

    @Override
    public int sumResultByTea() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectTeacher> criteriaQuery = criteriaBuilder.createQuery(SubjectTeacher.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> teacherJoin = root.join("teachers", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelations", JoinType.LEFT);
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

        TypedQuery<SubjectTeacher> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<SubjectTeacher> subjectTeachers = query.getResultList();
        return subjectTeachers.size();
    }

    @Override
    public List<TeacherYear> findScoreByTeaNo(String teaNo, Integer offset, Integer limit) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeacherYear> criteriaQuery = criteriaBuilder.createQuery(TeacherYear.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> teacherJoin = root.join("teachers", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelations", JoinType.LEFT);
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

        TypedQuery<TeacherYear> query = em.createQuery(criteriaQuery);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        // 获取分页结果集
        List<TeacherYear> teacherYears = query.getResultList();
        return teacherYears;
    }

    @Override
    public int sumResultByTeaNo(String teaNo) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeacherYear> criteriaQuery = criteriaBuilder.createQuery(TeacherYear.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        Join<Object, Object> teacherJoin = root.join("teachers", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelations", JoinType.LEFT);
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

        TypedQuery<TeacherYear> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<TeacherYear> teacherYears = query.getResultList();
        return teacherYears.size();
    }
}
