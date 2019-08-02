package com.ni.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ni.recommend.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByNameIgnoreCase(String name);

}
