package com.cts.bnym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MutualFund {

	@Id
	private long fundId;

	@Column(nullable = false)
	private String mutualFundName;

}