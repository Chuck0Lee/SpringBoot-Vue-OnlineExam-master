package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.answerQuestion;
import com.exam.entity.examAnswer;
import com.exam.mapper.AnswerQuestionMapper;
import com.exam.service.answerQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Classname AnswerQuestionServicelmpl
 * @description: TODO
 * @author: liqichang
 * @time: 2021/5/10 10:38
 */

@Service
public class AnswerQuestionServicelmpl implements answerQuestionService {
    @Autowired
    private AnswerQuestionMapper answerQuestionMapper;

    @Override
    public List<answerQuestion> findByIdAndType(Integer paperId) {
        return answerQuestionMapper.findByIdAndType(paperId);
    }
    @Override
    public int add(Integer examCode,Integer questionId,Integer studentId,String topic4Answer){
        return answerQuestionMapper.add(examCode,questionId,studentId,topic4Answer);
    }

    @Override
    public IPage<examAnswer> findAll(Page page,Integer examCode){
        return answerQuestionMapper.findAll(page,examCode);
    }

    @Override
    public int update(Integer score){
        return answerQuestionMapper.update(score);
    }

    @Override
    public int deleteAnswer(Integer answerId){
        return answerQuestionMapper.deleteAnswer(answerId);
    }

    @Override
    public int add2(answerQuestion answerQuestion){
        return answerQuestionMapper.add2(answerQuestion);
    }

    @Override
    public answerQuestion findOnlyQuestionId() {
        return answerQuestionMapper.findOnlyQuestionId();
    }
}
