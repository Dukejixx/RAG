package com.tecnocomfenalco.edurag.eduraggroq.dto;

//package com.tecnocomfenalco.edurag.dto;

import java.util.List;

public class RagResponseDto {

    private String answer;
    private List<String> sources;
    private List<String> retrievedChunks;
    private boolean success;

    public RagResponseDto() {
    }

    public RagResponseDto(String answer, List<String> sources, List<String> retrievedChunks, boolean success) {
        this.answer = answer;
        this.sources = sources;
        this.retrievedChunks = retrievedChunks;
        this.success = success;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getSources() {
        return sources;
    }

    public List<String> getRetrievedChunks() {
        return retrievedChunks;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public void setRetrievedChunks(List<String> retrievedChunks) {
        this.retrievedChunks = retrievedChunks;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}