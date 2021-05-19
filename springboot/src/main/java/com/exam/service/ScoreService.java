package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import com.exam.entity.StudentScore;

import java.util.List;

public interface ScoreService {
    int add(Score score);

    int add2(Score score);

    List<Score> findAll();

    IPage<Score> findById(Page<Score> page, Integer studentId);

    IPage<StudentScore> findByType(Page<StudentScore> page, Integer studentId);

    List<Score> findById(Integer studentId);

    List<StudentScore> findBytype(Integer studentId);

    List<Score> findByExamCode(Integer examCode);

    List<StudentScore> findscore();

    int updateScore(Integer score,Integer examCode,Integer studentId);
}
