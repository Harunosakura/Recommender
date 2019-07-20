package com.zenjobs.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenjobs.test.domain.Author;
import com.zenjobs.test.domain.Book;
import com.zenjobs.test.domain.BookGener;
import com.zenjobs.test.domain.Like;
import com.zenjobs.test.domain.Reactor;
import com.zenjobs.test.domain.UserReaction;

public interface UserReactionRepository extends JpaRepository<UserReaction, Long> {

	public List<UserReaction> findAllByReactor(Reactor reactor);

	long countByReactorAndBook_Author(Reactor rector, Author author);

	long countByReactorAndBook_Gener(Reactor rector, BookGener gener);

	Like findByReactorAndBook(Reactor reactor, Book book);

}
