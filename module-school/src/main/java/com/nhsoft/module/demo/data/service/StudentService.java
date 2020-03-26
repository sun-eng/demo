package com.nhsoft.module.demo.data.service;


import com.nhsoft.module.demo.data.model.StuTeaSubRelation;
import com.nhsoft.module.demo.data.model.SubjectScore;

import java.util.List;

public interface StudentService {

    /**
     * 学生可以查询本人每学年各学科成绩
     *
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
    int sumByStuNo(String stuNo);

    /**
     * 查询所有学生
     *
     * @return
     */
    List<StuTeaSubRelation> findAll();


}
