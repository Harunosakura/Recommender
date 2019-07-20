package com.zenjobs.test.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zenjobs.test.domain.Book;
import com.zenjobs.test.domain.Reactor;
import com.zenjobs.test.domain.RecommendationCriteria;
import com.zenjobs.test.exception.RecordNotFoundException;
import com.zenjobs.test.repository.BookRepository;
import com.zenjobs.test.repository.ReactorRepository;
import com.zenjobs.test.repository.RecommendationCriteriaRpository;
import com.zenjobs.test.service.CollectorService;
import com.zenjobs.test.service.RecommendationService;

/**
 * RecommendationServiceImpl is the implementation of
 * {@link RecommendationService} Interface <br>
 * 
 * @author nesrin
 *
 */

@Service
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
	public List<Book> recommendedBooks(Long reactorId) {

		// Load user data
		Optional<Reactor> reactor = reactorRep.findById(reactorId);

		// if NOT reactor OR NO criteria --> select randomly 20 books.
		if (reactor.isEmpty())
			throw new RecordNotFoundException("No User Found With ID", reactorId);

		// load Recommendation criteria
		List<RecommendationCriteria> criteria = criteriaRep.findByReactor(reactor.get());

		// if NO criteria --> select randomly 20 books.
		if (criteria.isEmpty())
			return bookRep.findAll(PageRequest.of(1, 20)).getContent();

		return collectorService.recommendedBooks(reactor.get(), criteria)
				.stream().collect(Collectors.toList());
	}

}
