package com.example.demo.service.impl;

import com.example.demo.entity.SubjectTeacher;
import com.example.demo.entity.SubjectYear;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherYear;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.TeacherCustomRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private static final String STR_ONE = "1";

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherCustomRepository teacherCustomRepository;


    @Override
    public List<SubjectYear> findScoreByYear(String teaNo, Integer offset, Integer limit) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByYear enter with { teaNo : " + teaNo + ", offset : " + offset + ", limit : " + limit + "}");
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByYear : 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        List<SubjectYear> subjectYears = teacherCustomRepository.findScoreByYear(offset, limit);
        LOGGER.info("TeacherServiceImpl findScoreByYear exit with subjectYears : " + subjectYears);
        return subjectYears;
    }

    @Override
    public int sumResultByYear(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByYear enter with { teaNo : " + teaNo + "}");
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByYear: 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        int size = teacherCustomRepository.sumResultByYear();
        LOGGER.info("TeacherServiceImpl sumResultByYear exit with size : " + size);
        return size;
    }


    @Override
    public List<SubjectTeacher> findScoreByTea(String teaNo, Integer offset, Integer limit) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + ", offset : " + offset + ", limit : " + limit + "}");
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByTea: 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        List<SubjectTeacher> subjectTeachers = teacherCustomRepository.findScoreByTea(offset, limit);
        LOGGER.info("TeacherServiceImpl findScoreByTea exit with subjectTeachers : " + subjectTeachers);
        return subjectTeachers;
    }

    @Override
    public int sumResultByTea(String teaNo) {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + "}");
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByTea: 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        int size = teacherCustomRepository.sumResultByTea();
        LOGGER.info("TeacherServiceImpl sumResultByTea exit with size : " + size);
        return size;
    }


    @Override
    public List<TeacherYear> findScoreByTeaNo(String teaNo, Integer offset, Integer limit) {
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo enter with { teaNo : " + teaNo + ", offset : " + offset + ", limit : " + limit + "}");
        List<TeacherYear> teacherYears = teacherCustomRepository.findScoreByTeaNo(teaNo, offset, limit);
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo exit with teacherYears : " + teacherYears);
        return teacherYears;
    }

    @Override
    public int sumResultByTeaNo(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo enter with { teaNo : " + teaNo + "}");
        int size = teacherCustomRepository.sumResultByTeaNo(teaNo);
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo exit with size : " + size);
        return size;
    }
}
