package com.ni.recommend.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "reaction_type", discriminatorType = DiscriminatorType.INTEGER)

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "reactor_id", "book_id" }) })

public class UserReaction {

	@Id
	@GeneratedValue(generator = "SEQ_USER_REACT")
	@SequenceGenerator(sequenceName = "SEQ_USER_REACT", name = "SEQ_USER_REACT", initialValue = 6, allocationSize = 1)

	private Long id;

	@ManyToOne
	@JsonIgnore
	private Reactor reactor;

	@ManyToOne
	private Book book;

	public UserReaction(Reactor reactor, Book book) {
		this.reactor = reactor;
		this.book = book;
	}
}
