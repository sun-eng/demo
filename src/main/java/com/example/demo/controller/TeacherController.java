package com.example.demo.controller;

import com.example.demo.exception.DemoException;
import com.example.demo.service.TeacherService;
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

import java.util.Map;

@Api(tags = "老师查询")
@RestController
@RequestMapping("/tea")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "教导主任查询每学年学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByYear")
    public Response<Page<Map<String, Object>>> findScoreByYear(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws DemoException {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Map<String, Object>> page = teacherService.findScoreByYear(teaNo, pageable);
        return Response.data(page);
    }

    @ApiOperation(value = "教导主任查询教师-学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTea")
    public Response<Page<Map<String, Object>>> findScoreByTea(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws DemoException {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Map<String, Object>> page = teacherService.findScoreByTea(teaNo, pageable);
        return Response.data(page);
    }

    @ApiOperation(value = "查询教师本人每学年，学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTeaNo")
    public Response<Page<Map<String, Object>>> findScoreByTeaNo(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws DemoException {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Map<String, Object>> page = teacherService.findScoreByTeaNo(teaNo, pageable);
        return Response.data(page);
    }

}
