package com.ni.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ni.recommend.domain.BookGener;

public interface BookGenerRepository extends JpaRepository<BookGener, Long> {

	BookGener findByNameIgnoreCase(String name);

}
