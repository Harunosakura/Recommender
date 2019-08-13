package com.ni.recommend.service;

import com.ni.recommend.domain.Book;
import com.ni.recommend.domain.Reactor;
import com.ni.recommend.domain.UserReaction;

public interface UserReactionService {

	void removeReaction(UserReaction reaction);

	UserReaction likeBook(Reactor reactor, Book book);

	UserReaction disLikeBook(Reactor reactor, Book book);

}
