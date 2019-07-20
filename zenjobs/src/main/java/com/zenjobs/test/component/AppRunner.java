package com.zenjobs.test.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.zenjobs.test.repository.BookRepository;
import com.zenjobs.test.repository.ReactorRepository;
import com.zenjobs.test.service.UserReactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	private UserReactionService reactionService;

	@Autowired
	private BookRepository bookRep;

	@Autowired
	private ReactorRepository reactorRep;

	@Override
	public void run(String... args) throws Exception {
		log.info("\n=====================Start adding Some test data ============");
		// user 1 likes
		reactionService.likeBook(reactorRep.findById(1l).get(), bookRep.findById(1l).get());
		reactionService.likeBook(reactorRep.findById(1l).get(), bookRep.findById(2l).get());
		reactionService.likeBook(reactorRep.findById(1l).get(), bookRep.findById(3l).get());
		reactionService.likeBook(reactorRep.findById(2l).get(), bookRep.findById(4l).get());
		reactionService.likeBook(reactorRep.findById(2l).get(), bookRep.findById(5l).get());
		reactionService.likeBook(reactorRep.findById(3l).get(), bookRep.findById(6l).get());
		reactionService.likeBook(reactorRep.findById(3l).get(), bookRep.findById(7l).get());

		// user 1 dislikes
		reactionService.disLikeBook(reactorRep.findById(1l).get(), bookRep.findById(8l).get());
		reactionService.disLikeBook(reactorRep.findById(2l).get(), bookRep.findById(9l).get());
		reactionService.disLikeBook(reactorRep.findById(2l).get(), bookRep.findById(10l).get());
		reactionService.disLikeBook(reactorRep.findById(2l).get(), bookRep.findById(11l).get());
		reactionService.disLikeBook(reactorRep.findById(2l).get(), bookRep.findById(12l).get());
		reactionService.disLikeBook(reactorRep.findById(4l).get(), bookRep.findById(13l).get());
		reactionService.disLikeBook(reactorRep.findById(5l).get(), bookRep.findById(14l).get());

//		long countOfLike = rep.countByBook_Author(book.get().getAuthor());

//		log.info("countOfLike: {}", countOfLike);

//		reactionService.disLikeBook(reactor.get(), book.get());

	}

}
