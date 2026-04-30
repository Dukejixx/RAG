package com.tecnocomfenalco.edurag.eduraggroq.dto;

//package com.tecnocomfenalco.edurag.dto;

public class RagRequestDto {

    private String question;

    public RagRequestDto() {
    }

    public RagRequestDto(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}