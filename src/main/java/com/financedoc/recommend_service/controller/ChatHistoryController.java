package com.financedoc.recommend_service.controller;

import com.financedoc.recommend_service.domain.ChatMessage;
import com.financedoc.recommend_service.dto.ChatMessageResponse;
import com.financedoc.recommend_service.service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatHistoryService chatHistoryService;

    @GetMapping("/chat-history")
    public ResponseEntity<List<ChatMessageResponse>> getChatHistory(
            @RequestHeader(value= "X-User-Id") Long userId
    ) {
        List<ChatMessage> messages = chatHistoryService.getChatHistory(userId);

        List<ChatMessageResponse> response = messages.stream()
                .map(ChatMessageResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
