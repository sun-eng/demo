package com.example.demo.repository.impl;

import com.example.demo.dto.StubjectYearDto;
import com.example.demo.dto.SubjectTeacherDto;
import com.example.demo.dto.TeacherYearDto;
import com.example.demo.entity.Subject;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.TeacherCustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class TeacherCustomRepositoryImpl implements TeacherCustomRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherCustomRepositoryImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<StubjectYearDto> findScoreByYear(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherCustomRepositoryImpl findScoreByYear enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<StubjectYearDto> criteriaQuery = criteriaBuilder.createQuery(StubjectYearDto.class);
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

        TypedQuery<StubjectYearDto> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<StubjectYearDto> resultList = query.getResultList();
        LOGGER.info("TeacherCustomRepositoryImpl findScoreByYear exit with resultList : " + resultList);
        return resultList;
    }

    @Override
    public int sumResultByYear(String teaNo) {
        LOGGER.info("TeacherCustomRepositoryImpl sumResultByYear enter with { teaNo : " + teaNo +  "}");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<StubjectYearDto> criteriaQuery = criteriaBuilder.createQuery(StubjectYearDto.class);
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

        TypedQuery<StubjectYearDto> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<StubjectYearDto> totalList = query.getResultList();
        LOGGER.info("TeacherCustomRepositoryImpl sumResultByYear exit with size : " + totalList.size());
        return totalList.size();
    }

    @Override
    public List<SubjectTeacherDto> findScoreByTea(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherCustomRepositoryImpl findScoreByTea enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectTeacherDto> criteriaQuery = criteriaBuilder.createQuery(SubjectTeacherDto.class);
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

        TypedQuery<SubjectTeacherDto> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<SubjectTeacherDto> resultList = query.getResultList();
        LOGGER.info("TeacherCustomRepositoryImpl findScoreByTea exit with resultList : " + resultList);
        return resultList;
    }

    @Override
    public int sumResultByTea(String teaNo) {
        LOGGER.info("TeacherCustomRepositoryImpl sumResultByTea enter with { teaNo : " + teaNo + "}");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SubjectTeacherDto> criteriaQuery = criteriaBuilder.createQuery(SubjectTeacherDto.class);
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

        TypedQuery<SubjectTeacherDto> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<SubjectTeacherDto> totalList = query.getResultList();
        LOGGER.info("TeacherCustomRepositoryImpl sumResultByTea exit with size : " + totalList.size());
        return totalList.size();
    }

    @Override
    public List<TeacherYearDto> findScoreByTeaNo(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherCustomRepositoryImpl findScoreByTeaNo enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeacherYearDto> criteriaQuery = criteriaBuilder.createQuery(TeacherYearDto.class);
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

        TypedQuery<TeacherYearDto> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        // 获取分页结果集
        List<TeacherYearDto> resultList = query.getResultList();
        LOGGER.info("TeacherCustomRepositoryImpl findScoreByTeaNo exit with resultList : " + resultList);
        return resultList;
    }

    @Override
    public int sumResultByTeaNo(String teaNo) {
        LOGGER.info("TeacherCustomRepositoryImpl sumResultByTeaNo enter with { teaNo : " + teaNo +  "}");
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<TeacherYearDto> criteriaQuery = criteriaBuilder.createQuery(TeacherYearDto.class);
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

        TypedQuery<TeacherYearDto> query = em.createQuery(criteriaQuery);
        // 获取总结果集
        List<TeacherYearDto> totalList = query.getResultList();
        LOGGER.info("TeacherCustomRepositoryImpl sumResultByTeaNo exit with size : " + totalList.size());
        return totalList.size();
    }
}
