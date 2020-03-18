package com.example.demo.service;

import com.example.demo.entity.Teacher;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.RelationRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class TeacherService {

    private static final String STR_ONE = "1";

    private static final int INT_ONE = 1;

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public Page<Map<String, Object>> findScoreByYear(String teaNo, Pageable pageable) throws DemoException {
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            throw new DemoException("无查询权限");
        }
        Page<Map<String, Object>> page = teacherRepository.findScoreByYear(pageable);
        return page;
    }


    public Page<Map<String, Object>> findScoreByTea(String teaNo, Pageable pageable) throws DemoException {
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            throw new DemoException("无查询权限");
        }
        Page<Map<String, Object>> page = teacherRepository.findDetailScoreByTea(pageable);
        return page;
    }


    public Page<Map<String, Object>> findScoreByTeaNo(String teaNo, Pageable pageable) throws DemoException {
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            throw new DemoException("老师数据错误");
        }
        Page<Map<String, Object>> page = teacherRepository.findDetailScoreByTea(teaNo, pageable);
        return page;
    }

}
