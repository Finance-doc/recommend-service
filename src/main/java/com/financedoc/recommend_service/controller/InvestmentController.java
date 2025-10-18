package com.financedoc.recommend_service.controller;

import com.financedoc.recommend_service.dto.PersonalitySurveyRequest;
import com.financedoc.recommend_service.dto.PersonalitySurveyResponse;
import com.financedoc.recommend_service.dto.QuestionResponse;
import com.financedoc.recommend_service.service.ChatHistoryService;
import com.financedoc.recommend_service.service.PersonalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class InvestmentController {

    private final PersonalityService personalityService;
    private final ChatHistoryService chatHistoryService;


    // 설문 문항 조회
    @GetMapping("/questions")
    public List<QuestionResponse> getQuestions(@RequestHeader(value = "X-User-Id") Long userId) {
        List<QuestionResponse> questions = personalityService.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            String questionText = String.format("Q%d. %s", i + 1, questions.get(i).getQuestion());
            chatHistoryService.saveAiMessage(userId, questionText);
        }

        return questions;
    }
    // 설문 점수 및 투자 성향 저장
    @PostMapping("/survey")
    public PersonalitySurveyResponse savePersonality (
            @RequestHeader(value = "X-User-Id") Long userId,
            @RequestBody PersonalitySurveyRequest request
            ) {

        // 선택한 보기 텍스트를 대화로 저장
        if(request.getSelectedOptions() != null) {
            for (String answerText : request.getSelectedOptions()) {
                chatHistoryService.saveUserMessage(userId, answerText);
            }
        }

        // 점수 계산 및 성향 저장
        PersonalitySurveyResponse resposne = personalityService.calculateAndSave(userId, request);

        chatHistoryService.saveAiMessage(userId, "당신은" + resposne + "투자자 성향입니다.");

        return resposne;
    }


    @GetMapping("/survey")
    public PersonalitySurveyResponse getPersonality(@RequestHeader(value = "X-User-Id") Long userId) {
        return personalityService.getPersonality(userId);
    }
}
