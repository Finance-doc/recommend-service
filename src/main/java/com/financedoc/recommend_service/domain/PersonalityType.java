package com.financedoc.recommend_service.domain;

public enum PersonalityType {
    CONSERVATIVE("안정형"),
    NEUTRAL("중립형"),
    AGGRESSIVE("공격형");

    private final String korean;

    PersonalityType(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }

    public String getDescription() {
        return switch (this) {
            case CONSERVATIVE -> "안정적인 수익을 선호하며, 손실 위험을 최소화하는 투자 성향입니다.";
            case NEUTRAL -> "안정과 수익의 균형을 중시하며, 중간 수준의 리스크를 감수할 수 있습니다.";
            case AGGRESSIVE -> "높은 수익을 위해 리스크를 감수하는 적극적인 투자 성향입니다.";
        };
    }
}
