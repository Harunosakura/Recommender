package com.ni.recommend.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.BookGener;
import com.ni.recommend.domain.Reactor;
import com.ni.recommend.domain.RecommendationCriteria;
import com.ni.recommend.repository.BookGenerRepository;
import com.ni.recommend.repository.BookRepository;
import com.ni.recommend.service.CollectorService;
import com.ni.recommend.utils.CriteriaType;

import lombok.extern.slf4j.Slf4j;

/**
 * Class represent implementation of {@link CollectorService} <br>
 * responsible for collecting maximum 20 books based on likes and dislikes of
 * the user
 * 
 * @author nesrin
 *
 */
@Service
@Slf4j
public class CollectorServiceImpl implements CollectorService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookGenerRepository generReposiroty;

	/**
	 * Method recommendedBooks is responsible for collected recommendation criteria
	 * and find books suitable to the user
	 * 
	 * @param reactor  represents the user requesting the books
	 * @param criteria represents a collection of what user like
	 */

	@Override
	public Set<Book> recommendedBooks(Reactor reactor, List<RecommendationCriteria> criteria) {

		Set<Book> books = new HashSet<>();

		criteria.forEach(cr -> {
			if (cr.getKeyType() == CriteriaType.BOOK_GENER) {

				// find lovely geners
				Optional<BookGener> bg = generReposiroty.findById(cr.getValue());

				// check reactor dosn't dislike
				List<Long> dislikedBooks = bookRepository.findByReactor_Id(reactor.getId());
				log.info("disliked books {}", dislikedBooks);
				if (dislikedBooks.isEmpty()) {
					books.addAll(bookRepository.findByGener(bg.get()));
					log.info("Empty {}", dislikedBooks);
				} else {
					books.addAll(bookRepository.findAllByGenerAndIdNotIn(bg.get(), dislikedBooks));
					log.info("liked {}", bookRepository.findAllByGenerAndIdNotIn(bg.get(), dislikedBooks));

				}
			}
		});

		int booksCount = books.size();

		if (booksCount > 20)
			return new HashSet<Book>(new ArrayList<Book>(books).subList(0, 20));

		return books;
	}

}
