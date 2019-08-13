package com.ni.recommend.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.ni.recommend.domain.Author;
import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.BookGener;
import com.ni.recommend.repository.AuthorRepository;
import com.ni.recommend.repository.BookGenerRepository;
import com.ni.recommend.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Class is built to Get the data from DS file and insert into Database
 * 
 * @author nesrin
 *
 */

@Component
@Slf4j
public class DataLoader {

	@Autowired
	private BookGenerRepository bookGenerRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	/**
	 * loadDataFromFile
	 * responsible for loading data from the file and divides the rows creating 
	 * authors, books geners anb finally books  
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void loadDataFromFile() {

		Resource resource = new ClassPathResource("DS");
		List<Book> books = new ArrayList<>();
		log.info("================ @EventListener(ContextRefreshedEvent.class) ================");
		try (BufferedReader br = Files.newBufferedReader(Paths.get(resource.getURI()));
				Stream<String> lines = br.lines()) {
//			Stream<String> lines = br.lines();
			lines.forEach(line -> {
				String[] bookData = line.split(";");

				Long asin = Long.parseLong(bookData[0]);

				// load book gener
				BookGener bc = bookGenerRepository.findByNameIgnoreCase(bookData[3]);
				if (bc == null)
					bc = bookGenerRepository.save(new BookGener(bookData[3]));

				// load author
				Author author = authorRepository.findByNameIgnoreCase(bookData[2]);
				if (author == null)
					author = authorRepository.save(new Author(bookData[2]));

				Book b = new Book(asin, bookData[1], author, bc);
				books.add(b);
			});
			log.info("{}", books);

			// save all books
			bookRepository.saveAll(books);

		} catch (IOException io) {
			log.error("{}", io);
		}

	}
}
