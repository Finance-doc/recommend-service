package com.financedoc.recommend_service.service;

import com.financedoc.recommend_service.domain.ChatMessage;
import com.financedoc.recommend_service.domain.Role;
import com.financedoc.recommend_service.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

    private final ChatMessageRepository chatMessageRepository;

    public void saveUserMessage(Long userId, String message) {
        chatMessageRepository.save(ChatMessage.of(userId, Role.USER, message));
    }

    public void saveAiMessage(Long userId, String message) {
        chatMessageRepository.save(ChatMessage.of(userId, Role.AI, message));
    }

    public List<ChatMessage> getChatHistory(Long userId) {
        return chatMessageRepository.findAllByUserIdOrderByCreatedAtAsc(userId);
    }
}
