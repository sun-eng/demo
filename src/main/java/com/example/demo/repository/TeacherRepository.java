package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /**
     * 根据老师编号查找老师数量
     *
     * @param teaNo
     * @return
     */
    int countTeacherByTeaNo(String teaNo);

    /**
     * 根据老师编号查找老师
     *
     * @param teaNo
     * @return
     */
    Teacher findTeacherByTeaNo(String teaNo);

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param pageable
     * @return
     */
    @Query(value = "SELECT t1.NAME AS subName,AVG( t.score ) AS avgScore,MAX( t.score ) AS maxScore," +
            "MIN( t.score ) AS minScore,t.stu_year AS stuYear FROM SUBJECT t1 LEFT JOIN relation t " +
            "ON t1.sub_no = t.sub_no GROUP BY t.sub_no,t.stu_year,t1.NAME",nativeQuery = true)
    Page<Map<String, Object>> findScoreByYear(Pageable pageable);

    /**
     * 教导主任查询教师-学科平均成绩,最高成绩，最低成绩
     *
     * @param pageable
     * @return
     */
    @Query(value = " SELECT AVG( t2.score ) AS avgScore,MAX( t2.score ) AS maxScore,MIN( t2.score ) AS minScore," +
            "t2.tea_no AS teaNo,t1.NAME AS teaName FROM teacher t1 LEFT JOIN relation t2 ON t1.tea_no = t2.tea_no " +
            "where t1.is_admin = '0' GROUP BY t2.sub_no,t2.tea_no,t1.NAME",nativeQuery = true)
    Page<Map<String, Object>> findDetailScoreByTea(Pageable pageable);

    /**
     * 查询教师本人每学年，学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param pageable
     * @return
     */
    @Query(value = " SELECT t1.NAME AS teaName,t2.NAME AS subName,AVG( t3.score ) AS avgScore,MAX( t3.score ) AS maxScore," +
            "MIN( t3.score ) AS minScore,t3.stu_year AS stuYear,t3.sub_no AS subNo,t3.tea_no AS teaNo FROM teacher t1 " +
            "LEFT JOIN SUBJECT t2 ON t1.sub_no = t2.sub_no LEFT JOIN relation t3 ON t2.sub_no = t3.sub_no " +
            "WHERE t1.tea_no = :teaNo AND t1.tea_no = t3.tea_no GROUP BY t3.stu_year,t3.sub_no,t1.NAME,t2.NAME,t3.tea_no",nativeQuery = true)
    Page<Map<String, Object>> findDetailScoreByTea(@Param("teaNo") String teaNo, Pageable pageable);

}
