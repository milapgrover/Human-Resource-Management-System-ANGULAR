package com.example.hrms.controller;

import com.example.hrms.dto.ChatRequest;
import com.example.hrms.dto.ChatResponse;
import com.example.hrms.service.LLMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final LLMService llmService;

    @PostMapping
    public ChatResponse chat(
            @RequestBody ChatRequest request) {

        return llmService.askQuestion(
                request.getMessage());
    }
}