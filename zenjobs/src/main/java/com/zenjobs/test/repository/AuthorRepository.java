package com.zenjobs.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenjobs.test.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByNameIgnoreCase(String name);

}
