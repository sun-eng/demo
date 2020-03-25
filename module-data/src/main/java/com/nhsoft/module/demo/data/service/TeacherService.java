package com.nhsoft.module.demo.data.service;


import com.nhsoft.module.demo.data.exception.DemoException;
import com.nhsoft.module.demo.data.model.SubjectTeacher;
import com.nhsoft.module.demo.data.model.SubjectYear;
import com.nhsoft.module.demo.data.model.TeacherYear;

import java.util.List;

public interface TeacherService {

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param offset
     * @param limit
     * @return
     * @throws DemoException
     */
    List<SubjectYear> findScoreByYear(String teaNo, Integer offset, Integer limit) throws DemoException;

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
     * @param offset
     * @param limit
     * @return
     * @throws DemoException
     */
    List<SubjectTeacher> findScoreByTea(String teaNo, Integer offset, Integer limit) throws DemoException;

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
     * @param offset
     * @param limit
     * @return
     * @throws DemoException
     */
    List<TeacherYear> findScoreByTeaNo(String teaNo, Integer offset, Integer limit) throws DemoException;

    /**
     * 教导主任查询师本人每学年，学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumResultByTeaNo(String teaNo);

}
