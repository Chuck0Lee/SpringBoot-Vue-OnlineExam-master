package com.exam.entity;

import lombok.Data;

/**
 * @Classname MutilError
 * @description: TODO
 * @author: liqichang
 * @time: 2021/5/7 16:12
 */

@Data
public class MutilError {
    private Integer questionId;

//    private Integer studentId;

    private String rights;

    private String question;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String rightAnswer;
}
