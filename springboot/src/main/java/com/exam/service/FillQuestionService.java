package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillError;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeError;
import com.exam.entity.JudgeQuestion;

import java.util.List;

public interface FillQuestionService {

    List<FillQuestion> findByIdAndType(Integer paperId);

    IPage<FillQuestion> findAll(Page<FillQuestion> page);

    FillQuestion findOnlyQuestionId();

    int add(FillQuestion fillQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo,String section);

    int AddFill(List<FillQuestion> Filllist);

    int deleteFill(Integer questionId);

    FillQuestion selectbyId(Integer questionId);

    int update(FillQuestion fillQuestion);

    int fillnum(String section,String subject);

    int fillError(Integer questionId,Integer studentId);

    IPage<FillError> fillErrors(Page<FillError> page, Integer studentId);

    int deleteError(Integer questionId);
}
