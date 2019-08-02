package com.ni.recommend.service;

import java.util.List;
import java.util.Set;

import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.Reactor;
import com.ni.recommend.domain.RecommendationCriteria;

public interface CollectorService {

	Set<Book> recommendedBooks(Reactor reactor, List<RecommendationCriteria> criteria);

}
