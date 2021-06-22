package com.example.jandroid.quizapprecycler;

public class Question {
    private String questionText;
    private Boolean response;
    private Boolean leftCheckBoxResponse = false, rightCheckBoxResponse = false;


    public Question() {

    }

    public Question(String questionText, Boolean response) {
        this.questionText = questionText;
        this.response = response;

    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;

    }

    public Boolean getLeftCheckBoxResponse() {
        return leftCheckBoxResponse;
    }

    public void setLeftCheckBoxResponse(Boolean leftCheckBoxResponse) {
        this.leftCheckBoxResponse = leftCheckBoxResponse;
    }

    public Boolean getRightCheckBoxResponse() {
        return rightCheckBoxResponse;
    }

    public void setRightCheckBoxResponse(Boolean rightCheckBoxResponse) {
        this.rightCheckBoxResponse = rightCheckBoxResponse;
    }
}
