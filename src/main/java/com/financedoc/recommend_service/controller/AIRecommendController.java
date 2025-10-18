package com.financedoc.recommend_service.controller;

import com.financedoc.recommend_service.dto.AIRecommendRequest;
import com.financedoc.recommend_service.dto.PersonalitySurveyResponse;
import com.financedoc.recommend_service.service.AiRecommendService;
import com.financedoc.recommend_service.service.ChatHistoryService;
import com.financedoc.recommend_service.service.PersonalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class AIRecommendController {

    private final AiRecommendService aiRecommendService;
    private final PersonalityService personalityService;
    private final ChatHistoryService chatHistoryService;

    @PostMapping("/ai-report")
    public ResponseEntity<String> generateReport(
            @RequestHeader(value = "X-User-Id") Long userId,
            @RequestBody AIRecommendRequest request
            ) {
        chatHistoryService.saveUserMessage(userId,
                String.format("소득: %s, 저축률: %s, 관심사: %s", request.getIncome(), request.getSavingRate(), request.getInterest()));


        PersonalitySurveyResponse personalitySurveyResponse = personalityService.getPersonality(userId);
        String personalityType = personalitySurveyResponse.getPersonalityType();

        String report = aiRecommendService.generateReport(personalityType, request);

        chatHistoryService.saveAiMessage(userId, report);

        return ResponseEntity.ok(report);
    }
}
