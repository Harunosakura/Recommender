package com.zenjobs.test.service;

import java.util.Optional;

import com.zenjobs.test.domain.Reactor;

public interface ReactorService {
	Optional<Reactor> findById(Long id);
}
