package com.cts.bnym.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;

	@Column(nullable = false)
	private long mutualFundId;

	@Column(nullable = false)
	private double amount;

	@JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	private Date time;

	@ManyToOne
	private Account number;

}
