package com.example.demo.service;

import com.example.demo.entity.Relation;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.RelationRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class RelationService {

    private static final int INT_ONE = 1;

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    StudentRepository studentRepository;

    public Page<Map<String, Object>> findAllByStuNo(String stuNo, Pageable pageable) throws DemoException {
        int num = studentRepository.countStudentByStuNo(stuNo);
        if(num != INT_ONE){
            throw new DemoException("用户信息错误");
        }
        return studentRepository.findRelationsByStuNo(stuNo, pageable);
    }

    public Page<Relation> findAll(PageRequest pageRequest) {
        return relationRepository.findAll(pageRequest);
    }
}
