package com.financedoc.recommend_service.service;

import com.financedoc.recommend_service.domain.InvestmentType;
import com.financedoc.recommend_service.domain.PersonalityType;
import com.financedoc.recommend_service.dto.PersonalitySurveyRequest;
import com.financedoc.recommend_service.dto.PersonalitySurveyResponse;
import com.financedoc.recommend_service.dto.QuestionResponse;
import com.financedoc.recommend_service.repository.InvestmentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalityService {

    private final InvestmentTypeRepository investmentTypeRepository;

    // 질문 리스트 제공
    public List<QuestionResponse> getQuestions() {
        return List.of(
                QuestionResponse.builder()
                        .id(1)
                        .question("투자 시 원금 손실 가능성이 있다면 어떻게 하시겠습니까?")
                        .option1("절대 감수하지 않는다.")
                        .option2("약간 감수할 수 있다.")
                        .option3("절반 정도는 감수할 수 있다.")
                        .option4("대부분 감수할 수 있다.")
                        .option5("손실을 감수하더라도 고수익을 추구한다")
                        .build(),
                QuestionResponse.builder()
                        .id(2)
                        .question("당신의 투자 목적은 무엇입니까?")
                        .option1("원금 보전")
                        .option2("안정적인 이자 수익")
                        .option3("수익과 안정의 균형")
                        .option4("성장 중심의 자산 확대")
                        .option5("공격적 자산 증대")
                        .build(),
                QuestionResponse.builder()
                        .id(3)
                        .question("예상치 못한 손실이 발생했을 때의 대응은?")
                        .option1("즉시 전액 인출")
                        .option2("일부 조정 후 보유")
                        .option3("추가 손실 방지를 위해 지켜본다")
                        .option4("장기 관점에서 유지")
                        .option5("더 매수한다")
                        .build(),
                QuestionResponse.builder()
                        .id(4)
                        .question("투자 경험 수준은?")
                        .option1("없음")
                        .option2("예금/적금 수준")
                        .option3("펀드/채권 투자 경험 있음")
                        .option4("주식 투자 경험 있음")
                        .option5("파생상품 등 고위험 투자 경험 있음")
                        .build(),
                QuestionResponse.builder()
                        .id(5)
                        .question("투자 기간 계획은?")
                        .option1("1년 미만")
                        .option2("1~3년")
                        .option3("3~5년")
                        .option4("5~10년")
                        .option5("10년 이상")
                        .build()
        );
    }

    // 설문 점수 계산 및 저장
    @Transactional
    public PersonalitySurveyResponse calculateAndSave(Long userId, PersonalitySurveyRequest request) {
        int totalScore = request.getAnswers().stream().mapToInt(Integer::intValue).sum();
        PersonalityType type = classify(totalScore);

        InvestmentType investmentType = investmentTypeRepository.findByUserId(userId)
                .map(it -> {
                    it.update(type, totalScore);
                    return it;
                })
                .orElse(new InvestmentType(userId, type, totalScore));

        investmentTypeRepository.save(investmentType);

        return PersonalitySurveyResponse.builder()
                .personalityType(type.getKorean())
                .totalScore(totalScore)
                .description(type.getDescription())
                .build();
    }

    private PersonalityType classify(int score) {
        if (score <= 12) return PersonalityType.CONSERVATIVE;
        else if (score <= 18) return PersonalityType.NEUTRAL;
        else return PersonalityType.AGGRESSIVE;
    }

    // 기존 결과 조회

    public PersonalitySurveyResponse getPersonality(Long userId) {
        InvestmentType investmentType = investmentTypeRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 성향이 존재하지 않습니다."));

        PersonalityType type = investmentType.getPersonality();
        return PersonalitySurveyResponse.builder()
                .personalityType(type.getKorean())
                .totalScore(investmentType.getTotalScore())
                .description(type.getDescription())
                .build();
    }
}
