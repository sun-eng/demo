package com.nhsoft.module.demo.data.repository;


import com.nhsoft.module.demo.data.model.SubjectTeacher;
import com.nhsoft.module.demo.data.model.SubjectYear;
import com.nhsoft.module.demo.data.model.Teacher;
import com.nhsoft.module.demo.data.model.TeacherYear;

import java.util.List;

public interface TeacherRepository {

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param offset
     * @param limit
     * @return
     */
    List<SubjectYear> findByYear(Integer offset, Integer limit);

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
     */
    List<SubjectTeacher> findByTea(Integer offset, Integer limit);

    /**
     * 教导主任查询教师-学科成绩数据的总条数
     *
     * @return
     */
    int sumByTea();

    /**
     * 查询教师本人每学年，学科平均成绩,最高成绩,最低成绩
     *
     * @param teaNo
     * @param offset
     * @param limit
     * @return
     */
    List<TeacherYear> findByTeaNo(String teaNo, Integer offset, Integer limit);


    /**
     * 教导主任查询师本人每学年，学科成绩数据的总条数
     *
     * @param teaNo
     * @return
     */
    int sumByTeaNo(String teaNo);

    /**
     * 根据老师编号查找老师
     *
     * @param teaNo
     * @return
     */
    Teacher readByTeaNo(String teaNo);
}
