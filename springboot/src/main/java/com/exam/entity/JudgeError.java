package com.exam.entity;

import lombok.Data;

/**
 * @Classname JudgeError
 * @description: TODO
 * @author: liqichang
 * @time: 2021/5/7 17:22
 */

@Data
public class JudgeError {

    private Integer questionId;

    private String rights;

    private String question;

    private String answer;

}
