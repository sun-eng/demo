package com.nhsoft.module.demo.data.repository;


import com.nhsoft.module.demo.data.model.SubjectTeacher;
import com.nhsoft.module.demo.data.model.SubjectYear;
import com.nhsoft.module.demo.data.model.TeacherYear;

import java.util.List;

public interface TeacherCustomRepository {

    /**
     * 教导主任查询每学年学科平均成绩,最高成绩,最低成绩
     *
     * @param offset
     * @param limit
     * @return
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
