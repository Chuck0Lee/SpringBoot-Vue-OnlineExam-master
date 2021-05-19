package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;
import com.exam.entity.MutilError;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MultiQuestionService {

    List<MultiQuestion> findByIdAndType(Integer PaperId);

    IPage<MultiQuestion> findAll(Page<MultiQuestion> page);

    MultiQuestion findOnlyQuestionId();

    int add(MultiQuestion multiQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo,String section);

    int add2(List<MultiQuestion> Multilist);

    int deleteMutil(Integer questionId);

    MultiQuestion selectbyId(Integer questionId);

    int update(MultiQuestion multiQuestion);

    int mutilnum(String section,String subject);

    int mutilError(Integer questionId,Integer studentId,String rights);

    IPage<MutilError> mutilErrors(Page<MutilError> page,Integer studentId);

    int deleteError(Integer questionId);


}

