package com.tecnocomfenalco.edurag.eduraggroq.service;
//package com.tecnocomfenalco.edurag.service;

import com.tecnocomfenalco.edurag.eduraggroq.dto.RagResponseDto;
import com.tecnocomfenalco.edurag.eduraggroq.model.DocumentChunk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagService {

    private static final Logger log = LoggerFactory.getLogger(RagService.class);

    private final RetrievalService retrievalService;
    private final GroqService groqService;

    public RagService(RetrievalService retrievalService, GroqService groqService) {
        this.retrievalService = retrievalService;
        this.groqService = groqService;
    }

    public RagResponseDto ask(String question) {
        List<DocumentChunk> chunks = retrievalService.retrieve(question, 3);

        String context = buildContext(chunks);

        String answer;
        try {
            answer = groqService.generateAnswer(question, context);
        } catch (Exception e) {
            log.error("Error al llamar a Groq: {} - {}", e.getClass().getSimpleName(), e.getMessage(), e);
            answer = "No fue posible consultar Groq. Error: " + e.getMessage() + "\n\nContexto recuperado localmente:\n\n" + context;
        }

        List<String> sources = chunks.stream()
                .map(DocumentChunk::getSource)
                .distinct()
                .toList();

        List<String> retrievedChunks = chunks.stream()
                .map(chunk -> "[%s | score %.3f]\n%s".formatted(
                        chunk.getSource(),
                        chunk.getScore(),
                        chunk.getContent()
                ))
                .toList();

        return new RagResponseDto(answer, sources, retrievedChunks, true);
    }

    private String buildContext(List<DocumentChunk> chunks) {
        StringBuilder sb = new StringBuilder();

        for (DocumentChunk chunk : chunks) {
            sb.append("Fuente: ").append(chunk.getSource()).append("\n");
            sb.append("Fragmento: ").append(chunk.getContent()).append("\n\n");
        }

        return sb.toString();
    }
}