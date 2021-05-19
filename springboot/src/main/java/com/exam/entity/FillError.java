package com.exam.entity;

import lombok.Data;

/**
 * @Classname FillError
 * @description: TODO
 * @author: liqichang
 * @time: 2021/5/7 17:28
 */

@Data
public class FillError {

    private Integer questionId;

    private String rights;

    private String question;

    private String answer;
}
