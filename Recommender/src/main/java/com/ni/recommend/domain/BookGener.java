package com.ni.recommend.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookGener {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENER")
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "gener")
	@JsonIgnore
	@ToString.Exclude

	private List<Book> books;

	public BookGener(String name) {
		this.name = name;
	}

}
