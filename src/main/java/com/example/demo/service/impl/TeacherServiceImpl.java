package com.example.demo.service.impl;

import com.example.demo.dto.StubjectYearDto;
import com.example.demo.dto.SubjectTeacherDto;
import com.example.demo.dto.TeacherYearDto;
import com.example.demo.entity.Teacher;
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

    private static final int INT_ONE = 1;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherCustomRepository teacherCustomRepository;


    @Override
    public List<StubjectYearDto> findScoreByYear(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByYear enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByYear : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.error("TeacherServiceImpl findScoreByYear : 无查询权限");
            throw new DemoException("无查询权限");
        }
        List<StubjectYearDto> list = teacherCustomRepository.findScoreByYear(teaNo, pageSize, pageNum);
        LOGGER.info("TeacherServiceImpl findScoreByYear exit with list : " + list);
        return list;
    }

    @Override
    public int sumResultByYear(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByYear enter with { teaNo : " + teaNo + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByYear : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.error("TeacherServiceImpl findScoreByYear : 无查询权限");
            throw new DemoException("无查询权限");
        }
        int size = teacherCustomRepository.sumResultByYear(teaNo);
        LOGGER.info("TeacherServiceImpl sumResultByYear exit with size : " + size);
        return size;
    }


    @Override
    public List<SubjectTeacherDto> findScoreByTea(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByTea : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.error("TeacherServiceImpl findScoreByTea : 无查询权限");
            throw new DemoException("无查询权限");
        }
        List<SubjectTeacherDto> list = teacherCustomRepository.findScoreByTea(teaNo, pageSize, pageNum);
        LOGGER.info("TeacherServiceImpl findScoreByTea exit with list : " + list);
        return list;
    }

    @Override
    public int sumResultByTea(String teaNo) {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByTea : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        Teacher teacher = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacher.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.error("TeacherServiceImpl findScoreByTea : 无查询权限");
            throw new DemoException("无查询权限");
        }
        int size = teacherCustomRepository.sumResultByTea(teaNo);
        LOGGER.info("TeacherServiceImpl sumResultByTea exit with size : " + size);
        return size;
    }


    @Override
    public List<TeacherYearDto> findScoreByTeaNo(String teaNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo enter with { teaNo : " + teaNo + ",pageSize : " + pageSize + ",pageNum : " + pageNum + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByTeaNo : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        List<TeacherYearDto> list = teacherCustomRepository.findScoreByTeaNo(teaNo, pageSize, pageNum);
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo exit with list : " + list);
        return list;
    }

    @Override
    public int sumResultByTeaNo(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo enter with { teaNo : " + teaNo + "}");
        int num = teacherRepository.countTeacherByTeaNo(teaNo);
        if (num != INT_ONE) {
            LOGGER.error("TeacherServiceImpl findScoreByTeaNo : 老师数据错误");
            throw new DemoException("老师数据错误");
        }
        int size = teacherCustomRepository.sumResultByTeaNo(teaNo);
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo exit with size : " + size);
        return size;
    }
}
