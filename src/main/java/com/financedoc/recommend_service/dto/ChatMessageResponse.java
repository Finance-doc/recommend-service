package com.financedoc.recommend_service.dto;

import com.financedoc.recommend_service.domain.ChatMessage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatMessageResponse {
    private String role;
    private String content;
    private LocalDateTime createdAt;

    public static ChatMessageResponse from(ChatMessage chatMessage) {
        return ChatMessageResponse.builder()
                .role(chatMessage.getRole().name())
                .content(chatMessage.getContent())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }
}
