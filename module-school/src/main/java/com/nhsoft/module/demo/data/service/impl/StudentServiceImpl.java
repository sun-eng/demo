package com.nhsoft.module.demo.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nhsoft.module.demo.data.constant.CommonConstant;
import com.nhsoft.module.demo.data.constant.RedisConstant;
import com.nhsoft.module.demo.data.model.StuTeaSubRelation;
import com.nhsoft.module.demo.data.model.SubjectScore;
import com.nhsoft.module.demo.data.repository.StuTeaSubRelationRepository;
import com.nhsoft.module.demo.data.repository.StudentRepository;
import com.nhsoft.module.demo.data.service.StudentService;
import com.nhsoft.module.demo.data.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StuTeaSubRelationRepository stuTeaSubRelationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<SubjectScore> findByStuNo(String stuNo, Integer offset, Integer limit) {
        LOGGER.info("StudentServiceImpl findAllByStuNo enter with { stuNo : " + stuNo + ", offset : " + offset + ", limit : " + limit + "}");
        List<SubjectScore> subjectScores = studentRepository.findByStuNo(stuNo, offset, limit);
        LOGGER.info("StudentServiceImpl findAllByStuNo exit with subjectScores : " + subjectScores);
        return subjectScores;
    }

    @Override
    public int sumByStuNo(String stuNo) {
        LOGGER.info("StudentServiceImpl sumResultByStuNo enter with { stuNo : " + stuNo + "}");
        int size = studentRepository.sumResultByStuNo(stuNo);
        LOGGER.info("StudentServiceImpl sumResultByStuNo exit with size : " + size);
        return size;
    }

    @Override
    public List<StuTeaSubRelation> findAll() {
        LOGGER.info("StudentServiceImpl findAll enter");
        if (redisUtil.get(RedisConstant.STU_FIND_ALL) != null) {
            String redisResults = (String) redisUtil.get(RedisConstant.STU_FIND_ALL);
            if (StringUtils.isNotBlank(redisResults) && !CommonConstant.EMPTY_REDIS_VALUE.equals(redisResults)) {
                return JSONArray.parseArray(redisResults, StuTeaSubRelation.class);
            }
        }

        List<StuTeaSubRelation> subRelations = stuTeaSubRelationRepository.findAll();
        redisUtil.set(RedisConstant.STU_FIND_ALL, JSON.toJSONString(subRelations));
        return subRelations;
    }

}
