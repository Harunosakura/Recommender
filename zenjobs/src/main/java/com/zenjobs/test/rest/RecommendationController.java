package com.zenjobs.test.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zenjobs.test.domain.Book;
import com.zenjobs.test.service.RecommendationService;

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
	public List<Book> retrieveRecommendationList(@PathVariable Long reactorId) {

		List<Book> recommendedbooks = recService.recommendedBooks(reactorId);

		return recommendedbooks;
	}
}
