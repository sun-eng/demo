package com.nhsoft.module.demo.data.controller;

import com.nhsoft.module.demo.data.constant.CommonConstant;
import com.nhsoft.module.demo.data.dto.SubjectTeacherDTO;
import com.nhsoft.module.demo.data.dto.SubjectYearDTO;
import com.nhsoft.module.demo.data.dto.TeacherYearDTO;
import com.nhsoft.module.demo.data.model.SubjectTeacher;
import com.nhsoft.module.demo.data.model.SubjectYear;
import com.nhsoft.module.demo.data.model.TeacherYear;
import com.nhsoft.module.demo.data.service.TeacherService;
import com.nhsoft.module.demo.data.util.CommonPage;
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
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "教导主任查询每学年学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("nhsoft.demo.tea.findByYear")
    public Response findScoreByYear(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                    @RequestParam(value = "offset", defaultValue = CommonConstant.OFFSET) Integer offset,
                                    @RequestParam(value = "limit", defaultValue = CommonConstant.LIMIT) Integer limit) {
        List<SubjectYear> scoreByYears = teacherService.findByYear(teaNo, offset, limit);
        List<SubjectYearDTO> subjectYearDTOs = new ArrayList<>();
        if (scoreByYears != null && !scoreByYears.isEmpty()) {
            for (SubjectYear subjectYear : scoreByYears) {
                SubjectYearDTO subjectYearDTO = new SubjectYearDTO();
                BeanUtils.copyProperties(subjectYear, subjectYearDTO);
                subjectYearDTOs.add(subjectYearDTO);
            }
        }
        int total = teacherService.sumByYear(teaNo);
        CommonPage<SubjectYearDTO> page = CommonPage.restPage(subjectYearDTOs, offset, limit, total);
        return Response.data(page);
    }

    @ApiOperation(value = "教导主任查询教师-学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("nhsoft.demo.tea.findByTea")
    public Response findScoreByTea(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                   @RequestParam(value = "offset", defaultValue = CommonConstant.OFFSET) Integer offset,
                                   @RequestParam(value = "limit", defaultValue = CommonConstant.LIMIT) Integer limit) {
        List<SubjectTeacher> subjectTeachers = teacherService.findByTea(teaNo, offset, limit);
        List<SubjectTeacherDTO> subjectTeacherDTOs = new ArrayList<>();
        if (subjectTeachers != null && !subjectTeachers.isEmpty()) {
            for (SubjectTeacher subjectYearDO : subjectTeachers) {
                SubjectTeacherDTO subjectYearDTO = new SubjectTeacherDTO();
                BeanUtils.copyProperties(subjectYearDO, subjectYearDTO);
                subjectTeacherDTOs.add(subjectYearDTO);
            }
        }
        int total = teacherService.sumByTea(teaNo);
        CommonPage<SubjectTeacherDTO> page = CommonPage.restPage(subjectTeacherDTOs, offset, limit, total);
        return Response.data(page);
    }

    @ApiOperation(value = "查询教师本人每学年，学科平均成绩,最高成绩,最低成绩")
    @RequestMapping("nhsoft.demo.tea.findByTeaNo")
    public Response findByTeaNo(@ApiParam("老师编号") @RequestParam("teaNo") String teaNo,
                                     @RequestParam(value = "offset", defaultValue = CommonConstant.OFFSET) Integer offset,
                                     @RequestParam(value = "limit", defaultValue = CommonConstant.LIMIT) Integer limit) {
        List<TeacherYear> teacherYears = teacherService.findByTeaNo(teaNo, offset, limit);
        List<TeacherYearDTO> teacherYearDTOs = new ArrayList<>();
        if (teacherYears != null && !teacherYears.isEmpty()) {
            for (TeacherYear teacherYear : teacherYears) {
                TeacherYearDTO teacherYearDTO = new TeacherYearDTO();
                BeanUtils.copyProperties(teacherYear, teacherYearDTO);
                teacherYearDTOs.add(teacherYearDTO);
            }
        }
        int total = teacherService.sumByTeaNo(teaNo);
        CommonPage<TeacherYearDTO> page = CommonPage.restPage(teacherYearDTOs, offset, limit, total);
        return Response.data(page);
    }

}
