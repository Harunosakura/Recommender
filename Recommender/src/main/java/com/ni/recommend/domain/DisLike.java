package com.ni.recommend.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "0")
@ToString(callSuper = true)
public class DisLike extends UserReaction {

	public DisLike(Reactor reactor, Book book) {
		super(reactor, book);
	}

}
