package com.ni.recommend.service;

import java.util.Collection;

import com.ni.recommend.domain.Book;

public interface RecommendationService {

	Collection<Book> recommendedBooks(Long reactorId);
}
