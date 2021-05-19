package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.PaperManage;

import java.util.List;

public interface PaperService {

    List<PaperManage> findAll();

    List<PaperManage> findById(Integer paperId);

    int add(PaperManage paperManage);

    int addRightNum(Integer paperId,Integer questionId);

    int addRightNum2(Integer paperId,Integer questionId);

    int addRightNum3(Integer paperId,Integer questionId);

    int addNum(Integer paperId);

    IPage rightNum(Page page,Integer paperId);
}
