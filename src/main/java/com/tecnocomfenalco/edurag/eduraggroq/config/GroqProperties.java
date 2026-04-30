package com.tecnocomfenalco.edurag.eduraggroq.config;

//package com.tecnocomfenalco.edurag.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "groq")
public class GroqProperties {

    private String baseUrl;
    private String chatEndpoint;
    private String apiKey;
    private String model;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getChatEndpoint() {
        return chatEndpoint;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getModel() {
        return model;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setChatEndpoint(String chatEndpoint) {
        this.chatEndpoint = chatEndpoint;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setModel(String model) {
        this.model = model;
    }
}