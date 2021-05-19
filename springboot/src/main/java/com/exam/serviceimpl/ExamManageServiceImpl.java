package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import com.exam.mapper.ExamManageMapper;
import com.exam.service.ExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamManageServiceImpl implements ExamManageService {
    @Autowired
    private ExamManageMapper examManageMapper;


    @Override
    public List<ExamManage> findAll() {
        return examManageMapper.findall();
    }

    @Override
    public IPage<ExamManage> findAll(Page<ExamManage> page,Integer teacherId) {
        return examManageMapper.findAll(page,teacherId);
    }

    @Override
    public IPage<ExamManage> findBygrade(Page<ExamManage> page,Integer grade,String major,String subject) {
        return examManageMapper.findBygrade(page,grade,major,subject);
    }

    @Override
    public IPage<ExamManage> findBygenre(Page<ExamManage> page,Integer grade,String major,String subject) {
        return examManageMapper.findBygenre(page,grade,major,subject);
    }

    @Override
    public IPage<ExamManage> findBysubject(Page<ExamManage> page,Integer grade,String major) {
        return examManageMapper.findBysubject(page,grade,major);
    }

    @Override
    public IPage<ExamManage> findBysubjectP(Page<ExamManage> page,Integer grade,String major) {
        return examManageMapper.findBysubjectP(page,grade,major);
    }



    @Override
    public List<ExamManage> findBymajor(Integer grade,String major) {
        return examManageMapper.findBymajor(grade,major);
    }

    @Override
    public List<ExamManage> findBysubjects(Integer grade,String major) {
        return examManageMapper.findBysubjects(grade,major);
    }

    @Override
    public List<ExamManage> findBysubjectPs(Integer grade,String major) {
        return examManageMapper.findBysubjectPs(grade,major);
    }

    @Override
    public ExamManage findById(Integer examCode) {
        return examManageMapper.findById(examCode);
    }

    @Override
    public int delete(Integer examCode) {
        return examManageMapper.delete(examCode);
    }

    @Override
    public int update(ExamManage exammanage) {
        return examManageMapper.update(exammanage);
    }

    @Override
    public int add(ExamManage exammanage) {
        return examManageMapper.add(exammanage);
    }

    @Override
    public ExamManage findOnlyPaperId() {
        return examManageMapper.findOnlyPaperId();
    }
}
