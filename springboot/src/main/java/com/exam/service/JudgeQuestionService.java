package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.JudgeError;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.entity.MutilError;

import java.util.List;

public interface JudgeQuestionService {

    List<JudgeQuestion> findByIdAndType(Integer paperId);

    IPage<JudgeQuestion> findAll(Page<JudgeQuestion> page);

    JudgeQuestion findOnlyQuestionId();

    int add(JudgeQuestion judgeQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo,String section);

    int AddJudeg(List<JudgeQuestion> Judgelist);

    int deleteJudge(Integer questionId);

    JudgeQuestion selectbyId(Integer questionId);

    int update(JudgeQuestion judgeQuestion);

    int judgenum(String section,String subject);

    int judgeError(Integer questionId,Integer studentId,String rights);

    IPage<JudgeError> judgeErrors(Page<JudgeError> page, Integer studentId);

    int deleteError(Integer questionId);
}
