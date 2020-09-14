package com.cts.bnym.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	private Long transactionId;

	@Column(nullable = false)
	private long mutualFundId;

	@Column(nullable = false)
	private double amount;

	@JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime time;

	@ManyToOne
	private Account account;

}
