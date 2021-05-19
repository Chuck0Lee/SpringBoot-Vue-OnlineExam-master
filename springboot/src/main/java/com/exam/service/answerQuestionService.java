package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.answerQuestion;
import com.exam.entity.examAnswer;

import java.util.List;

/**
 * @Classname answerQuestionService
 * @Description TODO
 * @Author liqichang
 * @Date 2021/5/10 10:37
 */
public interface answerQuestionService {
    List<answerQuestion> findByIdAndType(Integer paperId);

    int add(Integer examCode,Integer questionId,Integer studentId,String topic4Answer);

    IPage<examAnswer> findAll(Page page, Integer examCode);

    int update(Integer score);

    int deleteAnswer(Integer answerId);

    int add2(answerQuestion answerQuestion);

    answerQuestion findOnlyQuestionId();
}
