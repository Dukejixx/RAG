package com.tecnocomfenalco.edurag.eduraggroq.controller;

//package com.tecnocomfenalco.edurag.controller;

import com.tecnocomfenalco.edurag.eduraggroq.dto.RagResponseDto;
import com.tecnocomfenalco.edurag.eduraggroq.dto.RagRequestDto;
import com.tecnocomfenalco.edurag.eduraggroq.service.RagService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @GetMapping("/")
    public String index() {
        return "rag";
    }

    @PostMapping("/api/rag/ask")
    @ResponseBody
    public ResponseEntity<RagResponseDto> ask(@RequestBody RagRequestDto request) {
        if (request == null || request.getQuestion() == null || request.getQuestion().isBlank()) {
            return ResponseEntity.badRequest().body(
                    new RagResponseDto(
                            "La pregunta no puede estar vacía.",
                            List.of(),
                            List.of(),
                            false
                    )
            );
        }

        return ResponseEntity.ok(ragService.ask(request.getQuestion()));
    }
}