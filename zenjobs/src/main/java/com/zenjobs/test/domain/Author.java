package com.zenjobs.test.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
public class Author {

	@Id
	@GeneratedValue(generator = "SEQ_AUTHOR")
	@SequenceGenerator(sequenceName = "SEQ_AUTHOR", name = "SEQ_AUTHOR", initialValue = 1, allocationSize = 1)
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "author")
	@JsonIgnore
	@ToString.Exclude

	private List<Book> books;

	public Author(String name) {
		this.name = name;
	}

}
