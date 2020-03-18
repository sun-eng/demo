package com.example.demo.controller;


import com.example.demo.entity.Relation;
import com.example.demo.exception.DemoException;
import com.example.demo.service.StudentService;
import com.nhsoft.provider.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "学生查询")
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    StudentService studentService;

    @ApiOperation(value = "学生查询成绩")
    @RequestMapping("/find")
    public Response<Page<Map<String, Object>>> find(@ApiParam("学号") @RequestParam("stuNo") String stuNo,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws DemoException {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Map<String, Object>> page = studentService.findAllByStuNo(stuNo, pageable);
        return Response.data(page);
    }

    @RequestMapping("/findAll")
    public Response<List<Relation>> findAll() {
        List<Relation> page = studentService.findAll();
        return Response.data(page);
    }
}
