package com.zenjobs.test.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor

public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK")
	private Long id;

	public Long asin;

	private String title;

	@ManyToOne
	@NotNull
	private BookGener gener;

	@ManyToOne
//	@JsonIgnore
	@NotNull
	private Author author;

	@OneToMany(mappedBy = "book")
	@JsonIgnore
	@ToString.Exclude
	private List<UserReaction> reaction;

	public Book(long asin, String title, Author author, BookGener gener) {
		this.asin = asin;
		this.title = title;
		this.author = author;
		this.gener = gener;
	}

}
