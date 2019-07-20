package com.zenjobs.test.service;

import com.zenjobs.test.domain.Book;
import com.zenjobs.test.domain.Reactor;
import com.zenjobs.test.domain.UserReaction;

public interface UserReactionService {

	void removeReaction(UserReaction reaction);

	UserReaction likeBook(Reactor reactor, Book book);

	UserReaction disLikeBook(Reactor reactor, Book book);

}
