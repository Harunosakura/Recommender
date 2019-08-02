package com.ni.recommend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ni.recommend.domain.Author;
import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.BookGener;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByAsin(Long asin);

	List<Book> findByAuthor(Author author);

	List<Book> findByGener(BookGener gener);

	Page<Book> findAll(Pageable pageable);

	@Query(value = " SELECT b.id from "
			+ "	book b, user_reaction ur, reactor r "
			+ "	where b.id = ur.book_id "
			+ "	and r.id = ur.reactor_id "
			+ "	and  reaction_type = 0 "
			+ "	and ur.reactor_id =?1 ", nativeQuery = true)
	List<Long> findByReactor_Id(Long reactorId);

	List<Book> findAllByGenerAndIdNotIn(BookGener bookGener, List<Long> dislikedBooks);

}
