package com.example.demo.repository.impl;

import com.example.demo.entity.SubjectDO;
import com.example.demo.entity.SubjectTeacherDO;
import com.example.demo.entity.SubjectYearDO;
import com.example.demo.entity.TeacherYearDO;
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
    public List<SubjectYearDO> findScoreByYear(Integer pageSize, Integer pageNum) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectYearDO> criteriaQuery = criteriaBuilder.createQuery(SubjectYearDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelationDOs", JoinType.LEFT);
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

        TypedQuery<SubjectYearDO> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<SubjectYearDO> subjectYearDOs = query.getResultList();
        return subjectYearDOs;
    }

    @Override
    public int sumResultByYear() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectYearDO> criteriaQuery = criteriaBuilder.createQuery(SubjectYearDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> relation = root.join("stuTeaSubRelationDOs", JoinType.LEFT);
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

        TypedQuery<SubjectYearDO> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<SubjectYearDO> subjectYearDOs = query.getResultList();
        return subjectYearDOs.size();
    }

    @Override
    public List<SubjectTeacherDO> findScoreByTea(Integer pageSize, Integer pageNum) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectTeacherDO> criteriaQuery = criteriaBuilder.createQuery(SubjectTeacherDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> teacherJoin = root.join("teacherDOs", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelationDOs", JoinType.LEFT);
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

        TypedQuery<SubjectTeacherDO> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<SubjectTeacherDO> subjectTeacherDOs = query.getResultList();
        return subjectTeacherDOs;
    }

    @Override
    public int sumResultByTea() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectTeacherDO> criteriaQuery = criteriaBuilder.createQuery(SubjectTeacherDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> teacherJoin = root.join("teacherDOs", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelationDOs", JoinType.LEFT);
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

        TypedQuery<SubjectTeacherDO> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<SubjectTeacherDO> subjectTeacherDOs = query.getResultList();
        return subjectTeacherDOs.size();
    }

    @Override
    public List<TeacherYearDO> findScoreByTeaNo(String teaNo, Integer pageSize, Integer pageNum) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeacherYearDO> criteriaQuery = criteriaBuilder.createQuery(TeacherYearDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> teacherJoin = root.join("teacherDOs", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelationDOs", JoinType.LEFT);
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

        TypedQuery<TeacherYearDO> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<TeacherYearDO> teacherYearDOs = query.getResultList();
        return teacherYearDOs;
    }

    @Override
    public int sumResultByTeaNo(String teaNo) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeacherYearDO> criteriaQuery = criteriaBuilder.createQuery(TeacherYearDO.class);
        Root<SubjectDO> root = criteriaQuery.from(SubjectDO.class);
        Join<Object, Object> teacherJoin = root.join("teacherDOs", JoinType.LEFT);
        Join<Object, Object> relation = teacherJoin.join("stuTeaSubRelationDOs", JoinType.LEFT);
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

        TypedQuery<TeacherYearDO> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<TeacherYearDO> teacherYearDOs = query.getResultList();
        return teacherYearDOs.size();
    }
}
