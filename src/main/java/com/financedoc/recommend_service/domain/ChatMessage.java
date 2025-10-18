package com.financedoc.recommend_service.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    public static ChatMessage of(Long userId, Role role, String content) {
        return ChatMessage.builder()
                .userId(userId)
                .role(role)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
