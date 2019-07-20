package com.zenjobs.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenjobs.test.domain.BookGener;

public interface BookGenerRepository extends JpaRepository<BookGener, Long> {

	BookGener findByNameIgnoreCase(String name);

}
