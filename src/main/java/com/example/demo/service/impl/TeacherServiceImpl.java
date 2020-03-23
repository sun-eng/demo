package com.example.demo.service.impl;

import com.example.demo.dto.SubjectTeacherDTO;
import com.example.demo.dto.SubjectYearDTO;
import com.example.demo.dto.TeacherYearDTO;
import com.example.demo.entity.SubjectTeacherDO;
import com.example.demo.entity.SubjectYearDO;
import com.example.demo.entity.TeacherDO;
import com.example.demo.entity.TeacherYearDO;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.TeacherCustomRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<SubjectYearDTO> findScoreByYear(String teaNo, Integer pageSize, Integer pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByYear enter with { teaNo : " + teaNo + ", pageSize : " + pageSize + ", pageNum : " + pageNum + "}");
        TeacherDO teacherDO = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacherDO.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByYear : 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        List<SubjectYearDO> subjectYearDOs = teacherCustomRepository.findScoreByYear(pageSize, pageNum);
        List<SubjectYearDTO> subjectYearDTOs = new ArrayList<>();
        if (subjectYearDOs != null && !subjectYearDOs.isEmpty()) {
            for (SubjectYearDO subjectYearDO : subjectYearDOs) {
                SubjectYearDTO subjectYearDTO = new SubjectYearDTO();
                BeanUtils.copyProperties(subjectYearDO, subjectYearDTO);
                subjectYearDTOs.add(subjectYearDTO);
            }
        }
        LOGGER.info("TeacherServiceImpl findScoreByYear exit with subjectYearDTOs : " + subjectYearDTOs);
        return subjectYearDTOs;
    }

    @Override
    public int sumResultByYear(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByYear enter with { teaNo : " + teaNo + "}");
        TeacherDO teacherDO = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacherDO.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByYear: 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        int size = teacherCustomRepository.sumResultByYear();
        LOGGER.info("TeacherServiceImpl sumResultByYear exit with size : " + size);
        return size;
    }


    @Override
    public List<SubjectTeacherDTO> findScoreByTea(String teaNo, Integer pageSize, Integer pageNum) throws DemoException {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + ", pageSize : " + pageSize + ", pageNum : " + pageNum + "}");
        TeacherDO teacherDO = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacherDO.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByTea: 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        List<SubjectTeacherDO> subjectTeacherDOs = teacherCustomRepository.findScoreByTea(pageSize, pageNum);
        List<SubjectTeacherDTO> subjectTeacherDTOs = new ArrayList<>();
        if (subjectTeacherDOs != null && !subjectTeacherDOs.isEmpty()) {
            for (SubjectTeacherDO subjectYearDO : subjectTeacherDOs) {
                SubjectTeacherDTO subjectYearDTO = new SubjectTeacherDTO();
                BeanUtils.copyProperties(subjectYearDO, subjectYearDTO);
                subjectTeacherDTOs.add(subjectYearDTO);
            }
        }
        LOGGER.info("TeacherServiceImpl findScoreByTea exit with subjectTeacherDTOs : " + subjectTeacherDTOs);
        return subjectTeacherDTOs;
    }

    @Override
    public int sumResultByTea(String teaNo) {
        LOGGER.info("TeacherServiceImpl findScoreByTea enter with { teaNo : " + teaNo + "}");
        TeacherDO teacherDO = teacherRepository.findTeacherByTeaNo(teaNo);
        String isAdmin = teacherDO.getIsAdmin();
        if (!isAdmin.equals(STR_ONE)) {
            LOGGER.info("TeacherServiceImpl findScoreByTea: 无查询权限");
            throw new DemoException(403, "无查询权限");
        }
        int size = teacherCustomRepository.sumResultByTea();
        LOGGER.info("TeacherServiceImpl sumResultByTea exit with size : " + size);
        return size;
    }


    @Override
    public List<TeacherYearDTO> findScoreByTeaNo(String teaNo, Integer pageSize, Integer pageNum) {
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo enter with { teaNo : " + teaNo + ", pageSize : " + pageSize + ", pageNum : " + pageNum + "}");
        List<TeacherYearDO> teacherYearDOs = teacherCustomRepository.findScoreByTeaNo(teaNo, pageSize, pageNum);
        List<TeacherYearDTO> teacherYearDTOs = new ArrayList<>();
        if (teacherYearDOs != null && !teacherYearDOs.isEmpty()) {
            for (TeacherYearDO teacherYearDO : teacherYearDOs) {
                TeacherYearDTO teacherYearDTO = new TeacherYearDTO();
                BeanUtils.copyProperties(teacherYearDO, teacherYearDTO);
                teacherYearDTOs.add(teacherYearDTO);
            }
        }
        LOGGER.info("TeacherServiceImpl findScoreByTeaNo exit with teacherYearDTOs : " + teacherYearDTOs);
        return teacherYearDTOs;
    }

    @Override
    public int sumResultByTeaNo(String teaNo) {
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo enter with { teaNo : " + teaNo + "}");
        int size = teacherCustomRepository.sumResultByTeaNo(teaNo);
        LOGGER.info("TeacherServiceImpl sumResultByTeaNo exit with size : " + size);
        return size;
    }
}
