package com.ni.recommend.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
public class Reactor {

	@Id
	@GeneratedValue(generator = "SEQ_REACTOR")
	@SequenceGenerator(sequenceName = "SEQ_REACTOR", name = "SEQ_REACTOR", initialValue = 6, allocationSize = 1)
	private Long id;

	@NotBlank
	private String name;

	@OneToMany(mappedBy = "reactor")
	@ToString.Exclude
	private List<UserReaction> reaction;

	@OneToMany(mappedBy = "reactor")
	@ToString.Exclude
	private List<RecommendationCriteria> criteria;

}
