package com.financedoc.recommend_service.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class InvestmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // User 서비스의 PK (연결 ID만 저장)

    @Enumerated(EnumType.STRING)
    private PersonalityType personality;

    @Column(nullable = false)
    private int totalScore = 0; // 설문 총점
    public InvestmentType(Long userId, PersonalityType personality, int totalScore) {
        this.userId = userId;
        this.personality = personality;
        this.totalScore = totalScore;
    }

    public void update(PersonalityType type, int totalScore) {
        this.personality = type;
        this.totalScore = totalScore;
    }
}
