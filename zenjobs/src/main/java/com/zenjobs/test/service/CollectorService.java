package com.zenjobs.test.service;

import java.util.List;
import java.util.Set;

import com.zenjobs.test.domain.Book;
import com.zenjobs.test.domain.Reactor;
import com.zenjobs.test.domain.RecommendationCriteria;

public interface CollectorService {

	Set<Book> recommendedBooks(Reactor reactor, List<RecommendationCriteria> criteria);

}
