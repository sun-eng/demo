package com.example.demo.service.impl;

import com.example.demo.dto.SubjectScoreDTO;
import com.example.demo.entity.StuTeaSubRelationDO;
import com.example.demo.entity.SubjectScoreDO;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.StuTeaSubRelationRepository;
import com.example.demo.repository.StudentCustomRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.RedisUtil;
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
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private static final String STU_FIND_ALL = "demo_stuFindAll";

    @Autowired
    StuTeaSubRelationRepository stuTeaSubRelationRepository;

    @Autowired
    private StudentCustomRepository studentCustomRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<SubjectScoreDTO> findAllByStuNo(String stuNo, Integer pageSize, Integer pageNum) throws DemoException {
        LOGGER.info("StudentServiceImpl findAllByStuNo enter with { stuNo : " + stuNo + ", pageSize : " + pageSize + ", pageNum : " + pageNum + "}");
        List<SubjectScoreDO> subjectScoreDOs = studentCustomRepository.findAllByStuNo(stuNo, pageSize, pageNum);
        List<SubjectScoreDTO> subjectScoreDTOs = new ArrayList<>();
        if (subjectScoreDOs != null && !subjectScoreDOs.isEmpty()) {
            for (SubjectScoreDO subjectScoreDO : subjectScoreDOs) {
                SubjectScoreDTO subjectScoreDTO = new SubjectScoreDTO();
                BeanUtils.copyProperties(subjectScoreDO, subjectScoreDTO);
                subjectScoreDTOs.add(subjectScoreDTO);
            }
        }
        LOGGER.info("StudentServiceImpl findAllByStuNo exit with subjectScoreDTOs : " + subjectScoreDTOs);
        return subjectScoreDTOs;
    }

    @Override
    public int sumResultByStuNo(String stuNo) {
        LOGGER.info("StudentServiceImpl sumResultByStuNo enter with { stuNo : " + stuNo + "}");
        int size = studentCustomRepository.sumResultByStuNo(stuNo);
        LOGGER.info("StudentServiceImpl sumResultByStuNo exit with size : " + size);
        return size;
    }

    @Override
    public List<StuTeaSubRelationDO> findAll() {
        LOGGER.info("StudentServiceImpl findAll enter");
        List<StuTeaSubRelationDO> stuTeaSubRelationDOs = new ArrayList<>();
        if (redisUtil.lGet(STU_FIND_ALL, 0, -1) != null) {
            List<Object> redisResults = redisUtil.lGet(STU_FIND_ALL, 0, -1);
            LOGGER.info("StudentServiceImpl findAll redis取到的值为{ redisResults :" + redisResults + "}");
            if (redisResults != null && !redisResults.isEmpty()) {
                for (Object obj : redisResults) {
                    StuTeaSubRelationDO stuTeaSubRelationDO = (StuTeaSubRelationDO) obj;
                    stuTeaSubRelationDOs.add(stuTeaSubRelationDO);
                }
                return stuTeaSubRelationDOs;
            }
        }

        stuTeaSubRelationDOs = stuTeaSubRelationRepository.findAll();
        LOGGER.info("StudentServiceImpl findAll 从数据库重新获取值{ stuTeaSubRelationDOs :" + stuTeaSubRelationDOs + "}");
        if (stuTeaSubRelationDOs != null && !stuTeaSubRelationDOs.isEmpty()) {
            for (StuTeaSubRelationDO stuTeaSubRelationDO : stuTeaSubRelationDOs) {
                redisUtil.lSet(STU_FIND_ALL, stuTeaSubRelationDO, 60 * 5);
            }
        }
        return stuTeaSubRelationDOs;
    }
}
