package com.exam.entity;/**
 * @Classname ErrorQuestion
 * @Description TODO
 * @Author liqichang
 * @Date 2021/5/7 10:26
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorQuestion {
    private Integer questionId;

    private Integer studentId;

    private String rights;
}
