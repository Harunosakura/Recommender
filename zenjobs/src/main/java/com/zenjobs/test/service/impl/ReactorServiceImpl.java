package com.zenjobs.test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenjobs.test.domain.Reactor;
import com.zenjobs.test.repository.ReactorRepository;
import com.zenjobs.test.service.ReactorService;

@Service
public class ReactorServiceImpl implements ReactorService {

	@Autowired
	private ReactorRepository reactorRep;

	@Override
	public Optional<Reactor> findById(Long id) {

		Optional<Reactor> reactor = reactorRep.findById(id);

		return reactor;
	}

}
