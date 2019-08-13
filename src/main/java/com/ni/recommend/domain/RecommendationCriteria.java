package com.ni.recommend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ni.recommend.utils.CriteriaType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class RecommendationCriteria {

	@Id
	@GeneratedValue(generator = "SEQ_REC_CRITERIA", strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JsonIgnore
	private Reactor reactor;

	// set the criteria type and value,
	// author - Timm
	// Category - Sports
	// all minus disliked by current user to reach 20 book
	@Enumerated(EnumType.ORDINAL)
	@Column(length = 1)
	private CriteriaType keyType;
	private Long value;

	public RecommendationCriteria(Reactor reactor, CriteriaType keyType, Long value) {
		this.reactor = reactor;
		this.keyType = keyType;
		this.value = value;

	}

}
