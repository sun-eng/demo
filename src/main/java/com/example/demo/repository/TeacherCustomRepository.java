package com.example.demo.repository;

import com.example.demo.entity.SubjectYear;
import com.example.demo.entity.SubjectTeacher;
import com.example.demo.entity.TeacherYear;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface TeacherCustomRepository {

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param offset
     * @param limit
     * @return
     * @throws DemoException
     */
    List<SubjectYear> findScoreByYear(Integer offset, Integer limit);

    /**
     * 教导主任查询每学年学科成绩数据的总条数
     *
     * @return
     */
    int sumResultByYear();

    /**
     * 教导主任查询教师-学科平均成绩,最高成绩,最低成绩
     *
     * @param offset
     * @param limit
     * @return
     * @throws DemoException
     */
    List<SubjectTeacher> findScoreByTea(Integer offset, Integer limit);

    /**
     * 教导主任查询教师-学科成绩数据的总条数
     *
     * @return
     */
    int sumResultByTea();

    /**
     * 查询教师本人每学年，学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param offset
     * @param limit
     * @return
     * @throws DemoException
     */
    List<TeacherYear> findScoreByTeaNo(String teaNo, Integer offset, Integer limit);


    /**
     * 教导主任查询师本人每学年，学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumResultByTeaNo(String teaNo);
}
