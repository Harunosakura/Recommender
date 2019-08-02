package com.ni.recommend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ni.recommend.domain.Author;
import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.BookGener;
import com.ni.recommend.domain.Like;
import com.ni.recommend.domain.Reactor;
import com.ni.recommend.domain.UserReaction;

public interface UserReactionRepository extends JpaRepository<UserReaction, Long> {

	public List<UserReaction> findAllByReactor(Reactor reactor);

	long countByReactorAndBook_Author(Reactor rector, Author author);

	long countByReactorAndBook_Gener(Reactor rector, BookGener gener);

	Like findByReactorAndBook(Reactor reactor, Book book);

}
