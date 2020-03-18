package com.example.demo.repository;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    /**
     * 根据学科编号查询学科数量
     * @param subNo
     * @return
     */
    int countSubjectBySubNo(String subNo);
}
