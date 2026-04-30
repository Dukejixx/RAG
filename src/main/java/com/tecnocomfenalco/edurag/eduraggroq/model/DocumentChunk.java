package com.tecnocomfenalco.edurag.eduraggroq.model;

//package com.tecnocomfenalco.edurag.model;

public class DocumentChunk {

    private String id;
    private String source;
    private String content;
    private double score;

    public DocumentChunk() {
    }

    public DocumentChunk(String id, String source, String content) {
        this.id = id;
        this.source = source;
        this.content = content;
    }

    public DocumentChunk(String id, String source, String content, double score) {
        this.id = id;
        this.source = source;
        this.content = content;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getContent() {
        return content;
    }

    public double getScore() {
        return score;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setScore(double score) {
        this.score = score;
    }
}