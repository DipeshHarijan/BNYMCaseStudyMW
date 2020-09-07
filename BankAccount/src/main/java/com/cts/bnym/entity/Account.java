package com.cts.bnym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

	@Id
	@Pattern(regexp = "[1-9]{1}[0-9]{9}", message = "Account number must be of 10 digits and should not begin with 0")
	private String accountNumber;

	@Pattern(regexp = "[A-Za-z0-9]*", message = "IFSC code must only be alphanumeric")
	@Column(nullable = false)
	private String ifscCode;

	@Pattern(regexp = "[A-Za-z]*", message = "Bank name can only contain alphabets")
	@Column(nullable = false)
	private String bankName;

	@Column(nullable = false)
	private long micrCode;

	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]", message = "Pan should be of 10 characters with format BNGPS8372Q")
	@Column(nullable = false)
	private String pan;
}
