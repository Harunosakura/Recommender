package com.ni.recommend.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.Reactor;
import com.ni.recommend.domain.RecommendationCriteria;
import com.ni.recommend.exception.RecordNotFoundException;
import com.ni.recommend.repository.BookRepository;
import com.ni.recommend.repository.ReactorRepository;
import com.ni.recommend.repository.RecommendationCriteriaRpository;
import com.ni.recommend.service.CollectorService;
import com.ni.recommend.service.RecommendationService;

import lombok.extern.slf4j.Slf4j;

/**
 * RecommendationServiceImpl is the implementation of
 * {@link RecommendationService} Interface <br>
 * 
 * @author nesrin
 *
 */

@Service
@Slf4j

public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	private RecommendationCriteriaRpository criteriaRep;

	@Autowired
	private BookRepository bookRep;

	@Autowired
	private ReactorRepository reactorRep;

	@Autowired
	private CollectorService collectorService;

	/**
	 * It handle user requests for books based on presence of user <br>
	 * if user doesn't exist it throws {@link RecordNotFoundException} <br>
	 * if user exist and doesn't like or dislike anything, it retrieves random 20
	 * books <br>
	 * if user has criteria, it call {@link CollectorService} to retrieve what is
	 * suitable for user's interests
	 * 
	 * @param reactorId represents the user who is searching for books
	 */

	@Override
	public Collection<Book> recommendedBooks(Long reactorId) {

		// Load user data
		Reactor reactor = reactorRep.findById(reactorId)
				.orElseThrow(() -> new RecordNotFoundException("No User Found With ID", reactorId));

		// load Recommendation criteria
		List<RecommendationCriteria> criteria = criteriaRep.findByReactor(reactor);
		log.info("Reaactor: {}, Criteria {}", reactorId, criteria);
		// if NO criteria --> select randomly 20 books.
		
		if (criteria.isEmpty())
			return  bookRep.findAll(PageRequest.of(1, 20)).getContent();

		return collectorService.recommendedBooks(reactor, criteria);
	}

}
