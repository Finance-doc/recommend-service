package com.financedoc.recommend_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class PersonalitySurveyRequest {
    private List<Integer> answers;
}
