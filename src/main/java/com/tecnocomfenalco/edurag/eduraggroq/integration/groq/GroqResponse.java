package com.tecnocomfenalco.edurag.eduraggroq.integration.groq;

//package com.tecnocomfenalco.edurag.integration.groq;

import java.util.List;

public class GroqResponse {

    private List<Choice> choices;

    public GroqResponse() {
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        private Message message;

        public Choice() {
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }

    public static class Message {
        private String role;
        private String content;

        public Message() {
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
}