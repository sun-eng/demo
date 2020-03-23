package com.example.demo.repository;

import com.example.demo.entity.SubjectYearDO;
import com.example.demo.entity.SubjectTeacherDO;
import com.example.demo.entity.TeacherYearDO;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface TeacherCustomRepository {

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param pageSize
     * @param pageNum
     * @return
     * @throws DemoException
     */
    List<SubjectYearDO> findScoreByYear(Integer pageSize, Integer pageNum);

    /**
     * 教导主任查询每学年学科成绩数据的总条数
     *
     * @return
     */
    int sumResultByYear();

    /**
     * 教导主任查询教师-学科平均成绩,最高成绩,最低成绩
     *
     * @param pageSize
     * @param pageNum
     * @return
     * @throws DemoException
     */
    List<SubjectTeacherDO> findScoreByTea(Integer pageSize, Integer pageNum);

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
     * @param pageSize
     * @param pageNum
     * @return
     * @throws DemoException
     */
    List<TeacherYearDO> findScoreByTeaNo(String teaNo, Integer pageSize, Integer pageNum);


    /**
     * 教导主任查询师本人每学年，学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumResultByTeaNo(String teaNo);
}
