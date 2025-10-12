package com.financedoc.recommend_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class PersonalitySurveyResponse {
    private String personalityType;
    private int totalScore;
    private String description;
}
