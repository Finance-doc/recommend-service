package com.financedoc.recommend_service.controller;

import com.financedoc.recommend_service.dto.AIRecommendRequest;
import com.financedoc.recommend_service.dto.PersonalitySurveyResponse;
import com.financedoc.recommend_service.service.AiRecommendService;
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

    @PostMapping("/{userId}/ai-report")
    public ResponseEntity<String> generateReport(
            @PathVariable("userId") Long userId,
            @RequestBody AIRecommendRequest request
            ) {
        PersonalitySurveyResponse personalitySurveyResponse = personalityService.getPersonality(userId);
        String personalityType = personalitySurveyResponse.getPersonalityType();

        String report = aiRecommendService.generateReport(personalityType, request);
        return ResponseEntity.ok(report);
    }
}
