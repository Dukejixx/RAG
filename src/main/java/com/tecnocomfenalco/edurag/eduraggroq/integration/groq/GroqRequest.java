package com.tecnocomfenalco.edurag.eduraggroq.integration.groq;

//package com.tecnocomfenalco.edurag.integration.groq;

import java.util.List;

public class GroqRequest {

    private String model;
    private List<GroqMessage> messages;
    private Double temperature;

    public GroqRequest() {
    }

    public GroqRequest(String model, List<GroqMessage> messages, Double temperature) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
    }

    public String getModel() {
        return model;
    }

    public List<GroqMessage> getMessages() {
        return messages;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMessages(List<GroqMessage> messages) {
        this.messages = messages;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}