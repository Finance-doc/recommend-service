package com.financedoc.recommend_service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponse {
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
}
