package com.example.demo.service;

import com.example.demo.entity.StuTeaSubRelation;
import com.example.demo.entity.Student;
import com.example.demo.entity.SubjectScore;
import com.example.demo.exception.DemoException;

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
    List<SubjectScore> findAllByStuNo(String stuNo, Integer offset, Integer limit) throws DemoException;


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
    List<StuTeaSubRelation> findAll();

    /**
     * 保存学生
     * @param student
     */
    void saveStudent(Student student);

    /**
     * 更新学生
     * @param student
     */
    void updateStudent(Student student);
}
