package com.example.demo.repository;

import com.example.demo.entity.StudentDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentDO, Long>{

    /**
     * 根据学号查询学生数量
     *
     * @param stuNo
     * @return
     */
    int countStudentByStuNo(String stuNo);

    /**
     * 根据学号查询学生
     *
     * @param stuNo
     * @return
     */
    StudentDO findStudentByStuNo(String stuNo);

}
