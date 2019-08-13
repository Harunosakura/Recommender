package com.ni.recommend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ni.recommend.domain.Reactor;
import com.ni.recommend.domain.RecommendationCriteria;
import com.ni.recommend.utils.CriteriaType;

public interface RecommendationCriteriaRpository extends JpaRepository<RecommendationCriteria, Long> {

	RecommendationCriteria findByReactorAndKeyTypeAndValue(Reactor reactor, CriteriaType author, Long id);

	List<RecommendationCriteria> findByReactor(Reactor reactor);

}
