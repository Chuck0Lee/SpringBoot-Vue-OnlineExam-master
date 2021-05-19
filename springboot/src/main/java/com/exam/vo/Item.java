package com.exam.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//题目模型
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String subject;

    private Integer paperId;

    private Integer changeNumber;

    private Integer fillNumber;

    private Integer judgeNumber;

    private String section;

    public Integer getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(Integer changeNumber) {
        this.changeNumber = changeNumber;
    }

    public Integer getFillNumber() {
        return fillNumber;
    }

    public void setFillNumber(Integer fillNumber) {
        this.fillNumber = fillNumber;
    }

    public Integer getJudgeNumber() {
        return judgeNumber;
    }

    public void setJudgeNumber(Integer judgeNumber) {
        this.judgeNumber = judgeNumber;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSection(String section){
        this.section = section;
    }

    public String getSection(){
        return section;
    }

//    public Integer getChangeNumber() {
//        return changeNumber;
//    }
//
//    public Integer getFillNumber() {
//        return fillNumber;
//    }
//
//    public Integer getJudgeNumber() {
//        return judgeNumber;
//    }
//
//    public Integer getPaperId() {
//        return paperId;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
}
