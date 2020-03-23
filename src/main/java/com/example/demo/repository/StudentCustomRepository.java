package com.example.demo.repository;

import com.example.demo.entity.SubjectScoreDO;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface StudentCustomRepository {

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param stuNo
     * @param pageSize
     * @param pageNum
     * @return
     */
     List<SubjectScoreDO> findAllByStuNo(String stuNo, int pageSize, int pageNum) throws DemoException;


    /**
     * 查询学生本人数据的总条数
     * @param stuNo
     * @return
     */
    int sumResultByStuNo(String stuNo);
}
