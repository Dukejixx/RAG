package com.tecnocomfenalco.edurag.eduraggroq.integration.groq;

//package com.tecnocomfenalco.edurag.integration.groq;

public class GroqMessage {

    private String role;
    private String content;

    public GroqMessage() {
    }

    public GroqMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContent(String content) {
        this.content = content;
    }
}