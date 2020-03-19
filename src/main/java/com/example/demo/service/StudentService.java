package com.example.demo.service;

import com.example.demo.entity.QStudent;
import com.example.demo.entity.Relation;
import com.example.demo.exception.DemoException;
import com.example.demo.util.CommonPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface StudentService {

    /**
     * 根据学号查询学生数量
     * @param stuNo
     * @return
     */
    public int sumStudentByStuNo(String stuNo);

    /**
     * 学生可以查询本人每学年各学科成绩
     * @param stuNo
     * @param pageSize
     * @param pageNum
     * @return
     */
    public CommonPage<QStudent> findAllByStuNo(String stuNo, int pageSize, int pageNum) throws DemoException;

    /**
     * 查询所有学生
     * @return
     */
    public List<Relation> findAll();
}
