package com.ni.recommend.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ni.recommend.domain.Author;
import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.BookGener;
import com.ni.recommend.exception.RecordNotFoundException;
import com.ni.recommend.service.RecommendationService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@WebMvcTest
@Slf4j
public class RecommendationControllerTest {


	@MockBean
	private RecommendationService recService;
	
	@Autowired
	private MockMvc mockMvc;


	List<Book> books;

	@Before
	public void localSetup() {
		Book b = new Book(1l, 2182718l, "Improve Your Bowls", new BookGener(1l, "Sports & Outdoors", null),
				new Author(1l, "Tony Allcock", null), null);
		books = new ArrayList<Book>();
		books.add(b);
	}

	@Test
	public void testRetrieveRecommendationList_ReactorExist_CriteriaExist() throws Exception {
		log.info("book in test {}", books);
		given(recService.recommendedBooks(anyLong())).willReturn(books);
		mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
		
		verify(recService, times(1)).recommendedBooks(1l);

	}
	
	@Test
	public void testRetrieveRecommendationList_ReactorNotExist_CriteriaExist() throws Exception {

		when(recService.recommendedBooks(anyLong())).thenThrow(new RecordNotFoundException("No User Found With ID", 1));
		mockMvc.perform(get("/books/1")).andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.message", is("No User Found With ID: "+1)));
		
		verify(recService, times(1)).recommendedBooks(1l);

	}
}
