package com.exam.entity;

import lombok.Data;

/**
 * @Classname examAnswer
 * @description: TODO
 * @author: liqichang
 * @time: 2021/5/10 13:44
 */

@Data
public class examAnswer {

    private Integer answerId;

    private Integer examCode;

    private Integer questionId;

    private String question;

    private Integer studentId;

    private String answer;

    private String answers;

    private Integer score;

    private String studentName;
}
