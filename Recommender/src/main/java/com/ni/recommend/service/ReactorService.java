package com.ni.recommend.service;

import java.util.Optional;

import com.ni.recommend.domain.Reactor;

public interface ReactorService {
	Optional<Reactor> findById(Long id);
}
