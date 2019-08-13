package com.ni.recommend.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ni.recommend.domain.Book;
import com.ni.recommend.service.RecommendationService;

/**
 * RecommendationController 
 * responsible for calling the service layer retrieving maximum 20 records of recommended book
 * based on reactor Id<br>
 *
 * @author nesrin
 *
 */


@RestController
public class RecommendationController {

	@Autowired
	private RecommendationService recService;

	/**
	 * Here is the call for URI carrying reactor id
	 * @param reactorId represents the user who is searching for books
	 * @return recommendedbooks which is a list of maximum 20 books
	 */
	@GetMapping("/books/{reactorId}")
	public Collection<Book> retrieveRecommendationList(@PathVariable Long reactorId) {

		Collection<Book> recommendedbooks = recService.recommendedBooks(reactorId);

		return recommendedbooks;
	}
}
