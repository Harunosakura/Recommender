package com.ni.recommend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.DisLike;
import com.ni.recommend.domain.Like;
import com.ni.recommend.domain.Reactor;
import com.ni.recommend.domain.RecommendationCriteria;
import com.ni.recommend.domain.UserReaction;
import com.ni.recommend.repository.RecommendationCriteriaRpository;
import com.ni.recommend.repository.UserReactionRepository;
import com.ni.recommend.service.UserReactionService;
import com.ni.recommend.utils.CriteriaType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserReactionServiceImpl implements UserReactionService {

	@Autowired
	private UserReactionRepository userReactionRepository;

	@Autowired
	private RecommendationCriteriaRpository criteriaRepository;

	@Override
	public UserReaction likeBook(Reactor reactor, Book book) {
		Like like = new Like(reactor, book);
		userReactionRepository.save(like);
		log.info("User {} likes Book {}", reactor, book);

		// Extract Recommendation Criteria like author, and book gener.
		List<RecommendationCriteria> extractedCriteria = new ArrayList<>();
		extractedCriteria.add(new RecommendationCriteria(reactor, CriteriaType.AUTHOR, book.getAuthor().getId()));
		extractedCriteria.add(new RecommendationCriteria(reactor, CriteriaType.BOOK_GENER, book.getGener().getId()));
		log.info("Extracted Criteria : {}", extractedCriteria);

		// Save criteria attached to the reactor
		criteriaRepository.saveAll(extractedCriteria);

		return like;
	}

	@Override
	public UserReaction disLikeBook(Reactor reactor, Book book) {

		// Delete related criteria if present and only liked once
		deleteGenerCriterion(reactor, book);
		deleteAuthorCriterion(reactor, book);

		// Delete Like if present
		Like like = userReactionRepository.findByReactorAndBook(reactor, book);
		if (like != null)
			userReactionRepository.delete(like);
		log.info("Like deleted {} ", like);
		// Add Dislike
		DisLike disLike = new DisLike(reactor, book);
		userReactionRepository.save(disLike);
		log.info("User {} disLiked Book : {} ", reactor, book);

		return disLike;
	}

	@Override
	public void removeReaction(UserReaction reaction) {

		// Delete related criteria if present and only liked once
		deleteGenerCriterion(reaction.getReactor(), reaction.getBook());
		deleteAuthorCriterion(reaction.getReactor(), reaction.getBook());

		userReactionRepository.delete(reaction);
	}

	private void deleteAuthorCriterion(Reactor reactor, Book book) {

		// Delete Criterion of author
		long countOfAuthorLike = userReactionRepository.countByReactorAndBook_Author(reactor, book.getAuthor());

		if (countOfAuthorLike == 1) {
			log.info("Author criteria to be deleted : {} , {}", CriteriaType.AUTHOR, book.getAuthor().getId());
			deleteCriterion(reactor, CriteriaType.AUTHOR, book.getAuthor().getId());
		}

	}

	private void deleteGenerCriterion(Reactor reactor, Book book) {
		// Delete Criterion of Gener
		long countOfGenerLike = userReactionRepository.countByReactorAndBook_Gener(reactor, book.getGener());
		if (countOfGenerLike == 1) {
			log.info("Gener criteria to be deleted : {} , {}", CriteriaType.BOOK_GENER, book.getGener().getId());
			deleteCriterion(reactor, CriteriaType.BOOK_GENER, book.getGener().getId());

		}
	}

	private void deleteCriterion(Reactor reactor, CriteriaType type, Long id) {
		RecommendationCriteria criterion = criteriaRepository.findByReactorAndKeyTypeAndValue(reactor, type, id);
		criteriaRepository.delete(criterion);
		if (criterion != null)
			criteriaRepository.delete(criterion);
	}

}
