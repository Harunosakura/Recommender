package com.ni.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ni.recommend.domain.DisLike;

public interface DisLikeRepository extends JpaRepository<DisLike, Long> {
	

}
