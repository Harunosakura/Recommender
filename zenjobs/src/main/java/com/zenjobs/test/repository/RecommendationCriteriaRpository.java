package com.zenjobs.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenjobs.test.domain.Reactor;
import com.zenjobs.test.domain.RecommendationCriteria;
import com.zenjobs.test.utils.CriteriaType;

public interface RecommendationCriteriaRpository extends JpaRepository<RecommendationCriteria, Long> {

	RecommendationCriteria findByReactorAndKeyTypeAndValue(Reactor reactor, CriteriaType author, Long id);

	List<RecommendationCriteria> findByReactor(Reactor reactor);

}
