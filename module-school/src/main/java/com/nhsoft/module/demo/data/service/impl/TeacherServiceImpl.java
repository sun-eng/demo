package com.nhsoft.module.demo.data.service.impl;

import com.nhsoft.module.demo.data.exception.DemoError;
import com.nhsoft.module.demo.data.exception.DemoException;
import com.nhsoft.module.demo.data.model.SubjectTeacher;
import com.nhsoft.module.demo.data.model.SubjectYear;
import com.nhsoft.module.demo.data.model.Teacher;
import com.nhsoft.module.demo.data.model.TeacherYear;
import com.nhsoft.module.demo.data.repository.TeacherRepository;
import com.nhsoft.module.demo.data.service.TeacherService;
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


    @Override
    public List<SubjectYear> findByYear(String teaNo, Integer offset, Integer limit) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByYear enter with { teaNo : " + teaNo + ", offset : " + offset + ", limit : " + limit + "}");
        Teacher teacher = teacherRepository.readByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByYear : 无查询权限");
            throw new DemoException(DemoError.AUTHORITY_FORBIDDEN_ERROR);
        }
        List<SubjectYear> subjectYears = teacherRepository.findByYear(offset, limit);
        LOGGER.info("TeacherServiceImpl findScoreByYear exit with subjectYears : " + subjectYears);
        return subjectYears;
    }

    @Override
    public int sumByYear(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByYear enter with { teaNo : " + teaNo + "}");
        Teacher teacher = teacherRepository.readByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByYear: 无查询权限");
            throw new DemoException(DemoError.AUTHORITY_FORBIDDEN_ERROR);
        }
        int size = teacherRepository.sumResultByYear();
        LOGGER.info("TeacherServiceImpl sumResultByYear exit with size : " + size);
        return size;
    }


    @Override
    public List<SubjectTeacher> findByTea(String teaNo, Integer offset, Integer limit) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + ", offset : " + offset + ", limit : " + limit + "}");
        Teacher teacher = teacherRepository.readByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByTea: 无查询权限");
            throw new DemoException(DemoError.AUTHORITY_FORBIDDEN_ERROR);
        }
        List<SubjectTeacher> subjectTeachers = teacherRepository.findByTea(offset, limit);
        LOGGER.info("TeacherServiceImpl findScoreByTea exit with subjectTeachers : " + subjectTeachers);
        return subjectTeachers;
    }

    @Override
    public int sumByTea(String teaNo) {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + "}");
        Teacher teacher = teacherRepository.readByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByTea: 无查询权限");
            throw new DemoException(DemoError.AUTHORITY_FORBIDDEN_ERROR);
        }
        int size = teacherRepository.sumByTea();
        LOGGER.info("TeacherServiceImpl sumResultByTea exit with size : " + size);
        return size;
    }


    @Override
    public List<TeacherYear> findByTeaNo(String teaNo, Integer offset, Integer limit) {
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo enter with { teaNo : " + teaNo + ", offset : " + offset + ", limit : " + limit + "}");
        List<TeacherYear> teacherYears = teacherRepository.findByTeaNo(teaNo, offset, limit);
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo exit with teacherYears : " + teacherYears);
        return teacherYears;
    }

    @Override
    public int sumByTeaNo(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo enter with { teaNo : " + teaNo + "}");
        int size = teacherRepository.sumByTeaNo(teaNo);
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo exit with size : " + size);
        return size;
    }
}
