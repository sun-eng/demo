package com.example.demo.service;

import com.example.demo.dto.SubjectYearDTO;
import com.example.demo.dto.SubjectTeacherDTO;
import com.example.demo.dto.TeacherYearDTO;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface TeacherService {

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param pageSize
     * @param pageNum
     * @return
     * @throws DemoException
     */
    List<SubjectYearDTO> findScoreByYear(String teaNo, Integer pageSize, Integer pageNum) throws DemoException;

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
    List<SubjectTeacherDTO> findScoreByTea(String teaNo, Integer pageSize, Integer pageNum) throws DemoException;

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
    List<TeacherYearDTO> findScoreByTeaNo(String teaNo, Integer pageSize, Integer pageNum) throws DemoException;

    /**
     * 教导主任查询师本人每学年，学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumResultByTeaNo(String teaNo);

}
