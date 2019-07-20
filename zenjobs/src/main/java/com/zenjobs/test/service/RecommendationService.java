package com.zenjobs.test.service;

import java.util.List;

import com.zenjobs.test.domain.Book;

public interface RecommendationService {

	List<Book> recommendedBooks(Long reactorId);
}
