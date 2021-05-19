package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillError;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeError;
import com.exam.entity.JudgeQuestion;
import com.exam.mapper.FillQuestionMapper;
import com.exam.service.FillQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillQuestionServiceImpl implements FillQuestionService {

    @Autowired
    private FillQuestionMapper fillQuestionMapper;

    @Override
    public List<FillQuestion> findByIdAndType(Integer paperId) {
        return fillQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public IPage<FillQuestion> findAll(Page<FillQuestion> page) {
        return fillQuestionMapper.findAll(page);
    }

    @Override
    public FillQuestion findOnlyQuestionId() {
        return fillQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(FillQuestion fillQuestion) {
        return fillQuestionMapper.add(fillQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo,String section) {
        return fillQuestionMapper.findBySubject(subject,pageNo,section);
    }

    @Override
    public int AddFill(List<FillQuestion> Filllist) {
        return fillQuestionMapper.AddFill(Filllist);
    }

    @Override
    public int deleteFill(Integer questionId){
        return fillQuestionMapper.deleteFill(questionId);
    }

    @Override
    public FillQuestion selectbyId(Integer questionId){
        return fillQuestionMapper.selectbyId(questionId);
    }

    @Override
    public int update(FillQuestion fillQuestion){
        return fillQuestionMapper.update(fillQuestion);
    }

    @Override
    public int fillnum(String section,String subject){
        return fillQuestionMapper.fillnum(section,subject);
    }

    @Override
    public int fillError(Integer questionId,Integer studentId){
        return fillQuestionMapper.fillError(questionId,studentId);
    }
    @Override
    public IPage<FillError> fillErrors(Page<FillError> page, Integer studentId){
        return fillQuestionMapper.fillErrors(page,studentId);
    }

    @Override
    public int deleteError(Integer questionId){
        return fillQuestionMapper.deleteError(questionId);
    }

}

