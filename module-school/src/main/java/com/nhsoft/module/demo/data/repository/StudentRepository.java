package com.nhsoft.module.demo.data.repository;


import com.nhsoft.module.demo.data.model.Student;
import com.nhsoft.module.demo.data.model.SubjectScore;

import java.util.List;

public interface StudentRepository {

    /**
     * 根据学号查询学生
     *
     * @param stuNo
     * @return
     */
    Student readByStuNo(String stuNo);

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param stuNo
     * @param offset
     * @param limit
     * @return
     */
     List<SubjectScore> findByStuNo(String stuNo, Integer offset, Integer limit);


    /**
     * 查询学生本人数据的总条数
     * @param stuNo
     * @return
     */
    int sumResultByStuNo(String stuNo);

}
