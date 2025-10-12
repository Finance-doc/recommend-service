package com.financedoc.recommend_service.repository;

import com.financedoc.recommend_service.domain.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestmentTypeRepository extends JpaRepository<InvestmentType, Long> {
    Optional<InvestmentType> findByUserId(Long userId);
}
