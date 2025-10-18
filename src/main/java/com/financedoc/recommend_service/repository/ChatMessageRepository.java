package com.financedoc.recommend_service.repository;

import com.financedoc.recommend_service.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByUserIdOrderByCreatedAtAsc(Long userId);
}
