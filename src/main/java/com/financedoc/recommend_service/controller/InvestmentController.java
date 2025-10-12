package com.financedoc.recommend_service.controller;

import com.financedoc.recommend_service.dto.PersonalitySurveyRequest;
import com.financedoc.recommend_service.dto.PersonalitySurveyResponse;
import com.financedoc.recommend_service.dto.QuestionResponse;
import com.financedoc.recommend_service.service.PersonalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class InvestmentController {

    private final PersonalityService personalityService;

    // 설문 문항 조회
    @GetMapping("/questions")
    public List<QuestionResponse> getQuestions() {
        return personalityService.getQuestions();
    }
    @PostMapping("/{userId}")
    public PersonalitySurveyResponse savePersonality (
            @PathVariable("userId") Long userId,
            @RequestBody PersonalitySurveyRequest request
            ) {
        return personalityService.calculateAndSave(userId, request);
    }

    @GetMapping("/{userId}")
    public PersonalitySurveyResponse getPersonality(@PathVariable("userId") Long userId) {
        return personalityService.getPersonality(userId);
    }
}
