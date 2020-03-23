package com.example.demo.repository;

import com.example.demo.entity.TeacherDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherDO, Long>{

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
    TeacherDO findTeacherByTeaNo(String teaNo);

}
