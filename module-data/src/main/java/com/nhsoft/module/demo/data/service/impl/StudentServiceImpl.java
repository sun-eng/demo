package com.nhsoft.module.demo.data.service.impl;

import com.nhsoft.module.demo.data.exception.DemoException;
import com.nhsoft.module.demo.data.model.StuTeaSubRelation;
import com.nhsoft.module.demo.data.model.Student;
import com.nhsoft.module.demo.data.model.SubjectScore;
import com.nhsoft.module.demo.data.repository.StuTeaSubRelationRepository;
import com.nhsoft.module.demo.data.repository.StudentCustomRepository;
import com.nhsoft.module.demo.data.repository.StudentRepository;
import com.nhsoft.module.demo.data.service.StudentService;
import com.nhsoft.module.demo.data.util.CopyUtil;
import com.nhsoft.module.demo.data.util.RedisUtil;
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
    private StuTeaSubRelationRepository stuTeaSubRelationRepository;

    @Autowired
    private StudentCustomRepository studentCustomRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<SubjectScore> findAllByStuNo(String stuNo, Integer offset, Integer limit) throws DemoException {
        LOGGER.info("StudentServiceImpl findAllByStuNo enter with { stuNo : " + stuNo + ", offset : " + offset + ", limit : " + limit + "}");
        List<SubjectScore> subjectScores = studentCustomRepository.findAllByStuNo(stuNo, offset, limit);
        LOGGER.info("StudentServiceImpl findAllByStuNo exit with subjectScores : " + subjectScores);
        return subjectScores;
    }

    @Override
    public int sumResultByStuNo(String stuNo) {
        LOGGER.info("StudentServiceImpl sumResultByStuNo enter with { stuNo : " + stuNo + "}");
        int size = studentCustomRepository.sumResultByStuNo(stuNo);
        LOGGER.info("StudentServiceImpl sumResultByStuNo exit with size : " + size);
        return size;
    }

    @Override
    public List<StuTeaSubRelation> findAll() {
        LOGGER.info("StudentServiceImpl findAll enter");
        List<StuTeaSubRelation> stuTeaSubRelations = new ArrayList<>();
        if (redisUtil.lGet(STU_FIND_ALL, 0, -1) != null) {
            List<Object> redisResults = redisUtil.lGet(STU_FIND_ALL, 0, -1);
            LOGGER.info("StudentServiceImpl findAll redis取到的值为{ redisResults :" + redisResults + "}");
            if (redisResults != null && !redisResults.isEmpty()) {
                for (Object obj : redisResults) {
                    StuTeaSubRelation stuTeaSubRelation = (StuTeaSubRelation) obj;
                    stuTeaSubRelations.add(stuTeaSubRelation);
                }
                return stuTeaSubRelations;
            }
        }

        stuTeaSubRelations = stuTeaSubRelationRepository.findAll();
        LOGGER.info("StudentServiceImpl findAll 从数据库重新获取值{ stuTeaSubRelations :" + stuTeaSubRelations + "}");
        if (stuTeaSubRelations != null && !stuTeaSubRelations.isEmpty()) {
            for (StuTeaSubRelation stuTeaSubRelation : stuTeaSubRelations) {
                redisUtil.lSet(STU_FIND_ALL, stuTeaSubRelation, 60 * 5);
            }
        }
        return stuTeaSubRelations;
    }

    @Override
    public void saveStudent(Student student) {
        LOGGER.info("StudentServiceImpl saveStudent enter with { student : " + student + "}");
        studentRepository.save(student);
        // 测试事务
        //throw new DemoException(500,"出错了");
    }

    @Override
    public void updateStudent(Student student) {
        LOGGER.info("StudentServiceImpl updateStudent enter with { student : " + student + "}");
        Student student1 = studentRepository.findStudentByStuNo(student.getStuNo());
        String[] nullPropertyNames = CopyUtil.getNullPropertyNames(student);
        BeanUtils.copyProperties(student,student1,nullPropertyNames);
        studentRepository.save(student1);
    }
}
