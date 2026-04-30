package com.tecnocomfenalco.edurag.eduraggroq.service;
//package com.tecnocomfenalco.edurag.service;

import com.tecnocomfenalco.edurag.eduraggroq.model.DocumentChunk;
import com.tecnocomfenalco.edurag.eduraggroq.util.TextSimilarityUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RetrievalService {

    private final DocumentLoaderService documentLoaderService;

    public RetrievalService(DocumentLoaderService documentLoaderService) {
        this.documentLoaderService = documentLoaderService;
    }

    public List<DocumentChunk> retrieve(String question, int topK) {
        return documentLoaderService.getChunks().stream()
                .map(chunk -> new DocumentChunk(
                        chunk.getId(),
                        chunk.getSource(),
                        chunk.getContent(),
                        TextSimilarityUtil.cosineSimilarity(question, chunk.getContent())
                ))
                .sorted(Comparator.comparingDouble(DocumentChunk::getScore).reversed())
                .limit(topK)
                .toList();
    }
}