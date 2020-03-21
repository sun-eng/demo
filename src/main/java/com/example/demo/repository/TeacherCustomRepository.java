package com.example.demo.repository;

import com.example.demo.dto.StubjectYearDto;
import com.example.demo.dto.SubjectTeacherDto;
import com.example.demo.dto.TeacherYearDto;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface TeacherCustomRepository {

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param pageSize
     * @param pageNum
     * @return
     * @throws DemoException
     */
    List<StubjectYearDto> findScoreByYear(String teaNo, int pageSize, int pageNum) throws DemoException;

    /**
     * 教导主任查询每学年学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumResultByYear(String teaNo);

    /**
     * 教导主任查询教师-学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param pageNum
     * @param pageSize
     * @return
     * @throws DemoException
     */
    List<SubjectTeacherDto> findScoreByTea(String teaNo, int pageSize, int pageNum) throws DemoException;

    /**
     * 教导主任查询教师-学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumResultByTea(String teaNo);

    /**
     * 查询教师本人每学年，学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param pageSize
     * @param pageNum
     * @return
     * @throws DemoException
     */
    List<TeacherYearDto> findScoreByTeaNo(String teaNo, int pageSize, int pageNum) throws DemoException;


    /**
     * 教导主任查询师本人每学年，学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumResultByTeaNo(String teaNo);
}
