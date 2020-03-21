package com.example.demo.service.impl;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.StuTeaSubRelation;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.StuTeaSubRelationRepository;
import com.example.demo.repository.StudentCustomRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.RedisUtil;
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

    private static final int INT_ONE = 1;

    @Autowired
    StuTeaSubRelationRepository stuTeaSubRelationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCustomRepository studentCustomRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<StudentDto> findAllByStuNo(String stuNo, int pageSize, int pageNum) throws DemoException {
        LOGGER.info("StudentServiceImpl findAllByStuNo enter with { stuNo : " + stuNo + ",pageSize : " + pageSize + ", pageNum : " + pageNum + "}");
        int num = studentRepository.countStudentByStuNo(stuNo);
        if (num != INT_ONE) {
            LOGGER.error("StudentServiceImpl findAllByStuNo : 用户信息错误");
            throw new DemoException("用户信息错误");
        }
        List<StudentDto> list = studentCustomRepository.findAllByStuNo(stuNo, pageSize, pageNum);
        LOGGER.info("StudentServiceImpl findAllByStuNo exit with list : " + list);
        return list;
    }

    @Override
    public int sumResultByStuNo(String stuNo) {
        LOGGER.info("StudentServiceImpl sumResultByStuNo enter with { stuNo : " + stuNo +  "}");
        int num = studentRepository.countStudentByStuNo(stuNo);
        if (num != INT_ONE) {
            LOGGER.error("StudentServiceImpl sumResultByStuNo : 用户信息错误");
            throw new DemoException("用户信息错误");
        }
        int size = studentCustomRepository.sumResultByStuNo(stuNo);
        LOGGER.info("StudentServiceImpl sumResultByStuNo exit with size : " + size);
        return size;
    }

    @Override
    public List<StuTeaSubRelation> findAll() {
        LOGGER.info("StudentServiceImpl findAll enter");
        Object obj = redisUtil.get("demo_stuFindAll");
        LOGGER.info("StudentServiceImpl findAll 读取redis数据为：" + obj);
        if (obj != null) {
            return (List<StuTeaSubRelation>) obj;
        } else {
            List<StuTeaSubRelation> all = stuTeaSubRelationRepository.findAll();
            redisUtil.set("demo_stuFindAll", all, 60 * 5);
            LOGGER.info("StudentServiceImpl findAll 从数据库重新获取值");
            return all;
        }
    }
}
