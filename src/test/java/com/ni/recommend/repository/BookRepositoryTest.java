package com.ni.recommend.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ni.recommend.domain.Book;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testFindBookByAsin_AsinNotExist_returnNull() {
		Book book = bookRepository.findByAsin(1l);
		log.info("testFindBookByAsin_AsinNotExist_returnNull {}", book);
		assertEquals(null, book);
	}

}
