package com.financedoc.recommend_service.service;

import com.financedoc.recommend_service.dto.AIRecommendRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiRecommendService {
    @Value("${OPENAI_API_KEY}")
    private String apikey;

    public String generateReport(String personality, AIRecommendRequest request) {
        String prompt = String.format("""
                    사용자의 투자 성향: %s
                    월 소득: %s
                    저축률: %s
                    최근 관심사: %s
                                    
                    위 정보를 기반으로 이 사용자의 상황에 맞는 금융상품 유형 2~3개를 제안하고,
                    각 상품이 왜 적합한지 간단히 설명해줘.
                    추천은 자연스러운 문장으로 리포트 형태로 작성해.
                """,
                personality, request.getIncome(), request.getSavingRate(), request.getInterest()
        );

        OpenAiService service = new OpenAiService(apikey);

        ChatCompletionRequest chatRequest = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(
                        new ChatMessage("system", "너는 금융 전문가이자 투자 컨설턴트야. 사용자에게 친절하게 설명해."),
                        new ChatMessage("user", prompt)
                ))
                .maxTokens(700)
                .temperature(0.7)
                .build();

        ChatCompletionResult result = service.createChatCompletion(chatRequest);
        return result.getChoices().get(0).getMessage().getContent();
    }
}
