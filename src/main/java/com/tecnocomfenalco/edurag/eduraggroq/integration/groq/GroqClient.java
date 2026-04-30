package com.tecnocomfenalco.edurag.eduraggroq.integration.groq;

//package com.tecnocomfenalco.edurag.integration.groq;

import com.tecnocomfenalco.edurag.eduraggroq.config.GroqProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GroqClient {

    private final GroqProperties groqProperties;
    private final RestTemplate restTemplate;

    public GroqClient(GroqProperties groqProperties) {
        this.groqProperties = groqProperties;
        this.restTemplate = new RestTemplate();
    }

    public String chat(String systemPrompt, String userPrompt) {
        if (groqProperties.getApiKey() == null || groqProperties.getApiKey().isBlank()) {
            throw new IllegalStateException("La variable GROQ_API_KEY no está configurada.");
        }

        String url = groqProperties.getBaseUrl() + groqProperties.getChatEndpoint();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(groqProperties.getApiKey());

        GroqRequest request = new GroqRequest(
                groqProperties.getModel(),
                List.of(
                        new GroqMessage("system", systemPrompt),
                        new GroqMessage("user", userPrompt)
                ),
                0.1
        );

        HttpEntity<GroqRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<GroqResponse> response = restTemplate.postForEntity(
                url,
                entity,
                GroqResponse.class
        );

        GroqResponse body = response.getBody();

        if (body == null || body.getChoices() == null || body.getChoices().isEmpty()) {
            throw new IllegalStateException("Groq no retornó contenido.");
        }

        GroqResponse.Choice choice = body.getChoices().get(0);

        if (choice.getMessage() == null || choice.getMessage().getContent() == null) {
            throw new IllegalStateException("Groq no retornó mensaje válido.");
        }

        return choice.getMessage().getContent().trim();
    }
}