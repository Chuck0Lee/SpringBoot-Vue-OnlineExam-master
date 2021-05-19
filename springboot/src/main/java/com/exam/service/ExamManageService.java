package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;

import java.util.List;

public interface ExamManageService {

    /**
     * 不分页查询所有考试信息
     */
    List<ExamManage> findAll();
    IPage<ExamManage> findAll(Page<ExamManage> page,Integer teacherId);

    IPage<ExamManage> findBygrade(Page<ExamManage> page,Integer grade,String major,String subject);

    IPage<ExamManage> findBygenre(Page<ExamManage> page,Integer grade,String major,String subject);

    IPage<ExamManage> findBysubject(Page<ExamManage> page,Integer grade,String major);

    IPage<ExamManage> findBysubjectP(Page<ExamManage> page,Integer grade,String major);

    List<ExamManage> findBymajor(Integer grade,String major);

    List<ExamManage> findBysubjects(Integer grade,String major);

    List<ExamManage> findBysubjectPs(Integer grade,String major);

    ExamManage findById(Integer examCode);

    int delete(Integer examCode);

    int update(ExamManage exammanage);

    int add(ExamManage exammanage);

    ExamManage findOnlyPaperId();
}
