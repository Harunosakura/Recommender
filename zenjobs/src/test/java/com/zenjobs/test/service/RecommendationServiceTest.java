package com.zenjobs.test.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.zenjobs.test.domain.Author;
import com.zenjobs.test.domain.Book;
import com.zenjobs.test.domain.BookGener;
import com.zenjobs.test.domain.Reactor;
import com.zenjobs.test.domain.RecommendationCriteria;
import com.zenjobs.test.exception.RecordNotFoundException;
import com.zenjobs.test.repository.BookRepository;
import com.zenjobs.test.repository.ReactorRepository;
import com.zenjobs.test.repository.RecommendationCriteriaRpository;
import com.zenjobs.test.utils.CriteriaType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class RecommendationServiceTest {

	@Autowired
	RecommendationService recommendationService;

	@MockBean
	private RecommendationCriteriaRpository criteriaRepMock;

	@MockBean
	private BookRepository bookRepMock;

	@MockBean
	private ReactorRepository reactorRepMock;

	@MockBean
	private CollectorService collectorServiceMock;

	List<Book> books;
	List<RecommendationCriteria> criteriaList;
	Reactor reactor;
	RecommendationCriteria criterion1, criterion2; 
			
	@Before
	public void setup() {

		// Build Criteria List
		criteriaList = new ArrayList<>();

		// Build Book List
		Book b = new Book(1l, 2182718l, "Improve Your Bowls", new BookGener(1l, "Sports & Outdoors", null),
				new Author(1l, "Tony Allcock", null), null);
		books = new ArrayList<Book>();
		books.add(b);

		// Biuld Reactor object
		reactor = new Reactor();
		reactor.setId(1l);
		reactor.setName("Thomas");
		reactor.setCriteria(criteriaList);

		// Build Criterion 1
		criterion1 = new RecommendationCriteria(reactor, CriteriaType.AUTHOR, 1l);
		criterion1.setId(100l);

		// Build Criterion 2
		criterion2 = new RecommendationCriteria(reactor, CriteriaType.BOOK_GENER, 2l);
		criterion1.setId(200l);

	}

	@Test
	public void testRetrieveRecommendedBooks_ReactorNotExist_throwsRecordNotFoundException() {

		when(reactorRepMock.findById(any())).thenThrow(new RecordNotFoundException("No User Found With ID", 1));

		assertThatExceptionOfType(RecordNotFoundException.class)
				.isThrownBy(() -> recommendationService.recommendedBooks(1l)).withMessage("No User Found With ID: 1");

		verify(reactorRepMock, times(1)).findById(1l);
	}

	@Test
	public void testRetrieveRecommendedBooks_ReactorExistCriteriaEmpty_returnBooksList() {
		Page<Book> foundbooks = new PageImpl<Book>(books);

		when(reactorRepMock.findById(1l)).thenReturn(Optional.of(reactor));
		when(criteriaRepMock.findByReactor(reactor)).thenReturn(criteriaList);
		when(bookRepMock.findAll(any(Pageable.class))).thenReturn(foundbooks);
		when(collectorServiceMock.recommendedBooks(any(), any())).thenReturn(null);

		List<Book> bs = recommendationService.recommendedBooks(1l);

		assertEquals(books, bs);

		verify(bookRepMock, times(1)).findAll(any(Pageable.class));
		verify(criteriaRepMock, times(1)).findByReactor(reactor);
		verify(reactorRepMock, times(1)).findById(1l);
		verify(collectorServiceMock, times(0)).recommendedBooks(any(), any());

	}

	@Test
	public void testRetrieveRecommendedBooks_ReactorExistCriteriaExist_returnBooksList() {

		Page<Book> foundbooks = new PageImpl<Book>(books);
		criteriaList= Arrays.asList(criterion1, criterion2);

		when(reactorRepMock.findById(1l)).thenReturn(Optional.of(reactor));
		when(criteriaRepMock.findByReactor(reactor)).thenReturn(criteriaList);
		when(bookRepMock.findAll(any(Pageable.class))).thenReturn(foundbooks);
		when(collectorServiceMock.recommendedBooks(any(), any())).thenReturn(new HashSet<Book>(books));

		List<Book> bs = recommendationService.recommendedBooks(1l);

		assertEquals(books, bs);

		verify(bookRepMock, times(0)).findAll(any(Pageable.class));
		verify(criteriaRepMock, times(1)).findByReactor(reactor);
		verify(reactorRepMock, times(1)).findById(1l);
		verify(collectorServiceMock, times(1)).recommendedBooks(any(), any());

	}

}
