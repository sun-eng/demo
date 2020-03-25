package com.nhsoft.module.demo.data.repository;

import com.nhsoft.module.demo.data.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /**
     * 根据老师编号查找老师数量
     *
     * @param teaNo
     * @return
     */
    int countTeacherByTeaNo(String teaNo);

    /**
     * 根据老师编号查找老师
     *
     * @param teaNo
     * @return
     */
    Teacher findTeacherByTeaNo(String teaNo);

}
