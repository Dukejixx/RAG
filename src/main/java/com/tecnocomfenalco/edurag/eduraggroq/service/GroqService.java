package com.tecnocomfenalco.edurag.eduraggroq.service;

//package com.tecnocomfenalco.edurag.service;

import com.tecnocomfenalco.edurag.eduraggroq.integration.groq.GroqClient;
import org.springframework.stereotype.Service;

@Service
public class GroqService {

    private final GroqClient groqClient;

    public GroqService(GroqClient groqClient) {
        this.groqClient = groqClient;
    }

    public String generateAnswer(String question, String context) {
        String systemPrompt = """
                Eres un asistente académico basado en RAG.
                Responde en español.
                Usa exclusivamente el contexto proporcionado.
                Si la respuesta no está en el contexto, indica que no hay información suficiente.
                No inventes datos.
                Sé claro, breve y preciso.
                """;

        String userPrompt = """
                Contexto recuperado:
                %s

                Pregunta del usuario:
                %s

                Respuesta:
                """.formatted(context, question);

        return groqClient.chat(systemPrompt, userPrompt);
    }
}