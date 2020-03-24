package com.example.demo.controller;

import com.example.demo.dto.SubjectTeacherDTO;
import com.example.demo.dto.SubjectYearDTO;
import com.example.demo.dto.TeacherYearDTO;
import com.example.demo.entity.SubjectTeacher;
import com.example.demo.entity.SubjectYear;
import com.example.demo.entity.TeacherYear;
import com.example.demo.service.TeacherService;
import com.example.demo.util.CommonPage;
import com.nhsoft.provider.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "老师查询")
@RestController
@RequestMapping("/tea")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "教导主任查询每学年学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByYear")
    public Response findScoreByYear(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<SubjectYear> scoreByYears = teacherService.findScoreByYear(teaNo, offset, limit);
        List<SubjectYearDTO> subjectYearDTOs = new ArrayList<>();
        if (scoreByYears != null && !scoreByYears.isEmpty()) {
            for (SubjectYear subjectYear : scoreByYears) {
                SubjectYearDTO subjectYearDTO = new SubjectYearDTO();
                BeanUtils.copyProperties(subjectYear, subjectYearDTO);
                subjectYearDTOs.add(subjectYearDTO);
            }
        }
        int total = teacherService.sumResultByYear(teaNo);
        CommonPage<SubjectYearDTO> page = CommonPage.restPage(subjectYearDTOs, offset, limit, total);
        return Response.data(page);
    }

    @ApiOperation(value = "教导主任查询教师-学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTea")
    public Response findScoreByTea(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                   @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<SubjectTeacher> subjectTeachers = teacherService.findScoreByTea(teaNo, offset, limit);
        List<SubjectTeacherDTO> subjectTeacherDTOs = new ArrayList<>();
        if (subjectTeachers != null && !subjectTeachers.isEmpty()) {
            for (SubjectTeacher subjectYearDO : subjectTeachers) {
                SubjectTeacherDTO subjectYearDTO = new SubjectTeacherDTO();
                BeanUtils.copyProperties(subjectYearDO, subjectYearDTO);
                subjectTeacherDTOs.add(subjectYearDTO);
            }
        }
        int total = teacherService.sumResultByTea(teaNo);
        CommonPage<SubjectTeacherDTO> page = CommonPage.restPage(subjectTeacherDTOs, offset, limit, total);
        return Response.data(page);
    }

    @ApiOperation(value = "查询教师本人每学年，学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("/findScoreByTeaNo")
    public Response findScoreByTeaNo(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                     @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<TeacherYear> teacherYears = teacherService.findScoreByTeaNo(teaNo, offset, limit);
        List<TeacherYearDTO> teacherYearDTOs = new ArrayList<>();
        if (teacherYears != null && !teacherYears.isEmpty()) {
            for (TeacherYear teacherYear : teacherYears) {
                TeacherYearDTO teacherYearDTO = new TeacherYearDTO();
                BeanUtils.copyProperties(teacherYear, teacherYearDTO);
                teacherYearDTOs.add(teacherYearDTO);
            }
        }
        int total = teacherService.sumResultByTeaNo(teaNo);
        CommonPage<TeacherYearDTO> page = CommonPage.restPage(teacherYearDTOs, offset, limit, total);
        return Response.data(page);
    }

}
