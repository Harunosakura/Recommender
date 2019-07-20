package com.zenjobs.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenjobs.test.domain.DisLike;

public interface DisLikeRepository extends JpaRepository<DisLike, Long> {
	

}
