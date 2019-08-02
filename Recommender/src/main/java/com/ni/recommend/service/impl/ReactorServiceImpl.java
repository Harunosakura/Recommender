package com.ni.recommend.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ni.recommend.domain.Reactor;
import com.ni.recommend.repository.ReactorRepository;
import com.ni.recommend.service.ReactorService;

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
