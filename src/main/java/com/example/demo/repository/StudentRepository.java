package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * 根据学号查询学生数量
     * @param stuNo
     * @return
     */
    int countStudentByStuNo(String stuNo);

    /**
     * 根据学号查询学生成绩
     * @param stuNo
     * @param pageable
     * @return
     */
    @Query(value = "SELECT t1.NAME AS subName,t2.sub_no AS subNo,t2.score AS score,t2.stu_no AS stuNo," +
            "t2.stu_year AS stuYear FROM subject t1 LEFT JOIN relation t2 ON t1.sub_no = t2.sub_no WHERE t2.stu_no = :stuNo",nativeQuery = true)
    Page<Map<String, Object>> findRelationsByStuNo(@Param("stuNo") String stuNo, Pageable pageable);

}
