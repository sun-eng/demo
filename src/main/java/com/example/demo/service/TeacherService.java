package com.example.demo.service;

import com.example.demo.entity.QStubjectYear;
import com.example.demo.entity.QSubjectTeacher;
import com.example.demo.entity.QTeacherYear;
import com.example.demo.exception.DemoException;
import com.example.demo.util.CommonPage;

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
    public CommonPage<QStubjectYear> findScoreByYear(String teaNo, int pageSize, int pageNum) throws DemoException;

    /**
     * 教导主任查询教师-学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param pageNum
     * @param pageSize
     * @return
     * @throws DemoException
     */
    public CommonPage<QSubjectTeacher> findScoreByTea(String teaNo, int pageSize, int pageNum) throws DemoException;

    /**
     * 查询教师本人每学年，学科平均成绩,最高成绩,最低成绩
     * @param teaNo
     * @param pageSize
     * @param pageNum
     * @return
     * @throws DemoException
     */
    public CommonPage<QTeacherYear> findScoreByTeaNo(String teaNo, int pageSize, int pageNum) throws DemoException;

}
