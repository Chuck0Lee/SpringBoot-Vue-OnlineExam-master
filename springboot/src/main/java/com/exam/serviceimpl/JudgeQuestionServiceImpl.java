package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeError;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.entity.MutilError;
import com.exam.mapper.JudgeQuestionMapper;
import com.exam.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeQuestionServiceImpl implements JudgeQuestionService {

    @Autowired
    private JudgeQuestionMapper judgeQuestionMapper;

    @Override
    public List<JudgeQuestion> findByIdAndType(Integer paperId) {
        return judgeQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public IPage<JudgeQuestion> findAll(Page<JudgeQuestion> page) {
        return judgeQuestionMapper.findAll(page);
    }

    @Override
    public JudgeQuestion findOnlyQuestionId() {
        return judgeQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(JudgeQuestion judgeQuestion) {
        return judgeQuestionMapper.add(judgeQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo,String section) {
        return judgeQuestionMapper.findBySubject(subject,pageNo,section);
    }

    @Override
    public int AddJudeg(List<JudgeQuestion> Judgelist) {
        return judgeQuestionMapper.AddJudge(Judgelist);
    }

    @Override
    public int deleteJudge(Integer questionId){
        return judgeQuestionMapper.deleteJudge(questionId);
    }

    @Override
    public JudgeQuestion selectbyId(Integer questionId){
        return judgeQuestionMapper.selectbyId(questionId);
    }

    @Override
    public int update(JudgeQuestion judgeQuestion){
        return judgeQuestionMapper.update(judgeQuestion);
    }

    @Override
    public int judgenum(String section,String subject){
        return judgeQuestionMapper.judgenum(section,subject);
    }

    @Override
    public int judgeError(Integer questionId,Integer studentId,String rights){
        return judgeQuestionMapper.judgeError(questionId,studentId,rights);
    }

    @Override
    public IPage<JudgeError> judgeErrors(Page<JudgeError> page, Integer studentId){
        return judgeQuestionMapper.judgeErrors(page,studentId);
    }

    @Override
    public int deleteError(Integer questionId){
        return judgeQuestionMapper.deleteError(questionId);
    }
}
