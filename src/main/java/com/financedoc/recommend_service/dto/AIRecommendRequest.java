package com.financedoc.recommend_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AIRecommendRequest {
    private String income;
    private String savingRate;
    private String interest;
}
