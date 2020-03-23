package com.example.demo.service;

import com.example.demo.dto.SubjectScoreDTO;
import com.example.demo.entity.StuTeaSubRelationDO;
import com.example.demo.exception.DemoException;

import java.util.List;

public interface StudentService {

    /**
     * 学生可以查询本人每学年各学科成绩
     *
     * @param stuNo
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SubjectScoreDTO> findAllByStuNo(String stuNo, Integer pageSize, Integer pageNum) throws DemoException;


    /**
     * 查询学生本人数据的总条数
     * @param stuNo
     * @return
     */
    int sumResultByStuNo(String stuNo);

    /**
     * 查询所有学生
     *
     * @return
     */
    List<StuTeaSubRelationDO> findAll();
}
