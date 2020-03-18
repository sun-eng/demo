package com.example.demo.service;

import com.example.demo.entity.Relation;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.RelationRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentService {

    private static final int INT_ONE = 1;

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    RedisUtil redisUtil;

    public int sumStudentByStuNo(String stuNo){
        return studentRepository.countStudentByStuNo(stuNo);
    }

    public Page<Map<String, Object>> findAllByStuNo(String stuNo, Pageable pageable) throws DemoException {
        int num = studentRepository.countStudentByStuNo(stuNo);
        if(num != INT_ONE){
            throw new DemoException("用户信息错误");
        }
        return studentRepository.findRelationsByStuNo(stuNo, pageable);
    }

    public List<Relation> findAll() {
        Object obj = redisUtil.get("demo_stuFindAll");
        if(obj != null){
            return (List<Relation>)obj;
        }
        return relationRepository.findAll();
    }
}
