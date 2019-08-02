package com.ni.recommend.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "1")
@ToString(callSuper = true)
public class Like extends UserReaction {

	public Like(Reactor reactor, Book book) {
		super(reactor, book);
	}
}
