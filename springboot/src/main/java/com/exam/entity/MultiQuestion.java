package com.exam.entity;

import lombok.Data;


// 选择题实体
@Data
public class MultiQuestion {
    private Integer questionId;

    private String subject;

    private String question;


    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String rightAnswer;

    private String analysis; //题目解析

    private Integer score;

    private String section;

    private Integer level;






    public void setQuestionId(Integer questionId){
        this.questionId = questionId;
    }

    public Integer getQuestionId(){
        return questionId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }


    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }




}