package com.cts.bnym.entity;

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
public class Bank {

	@Id
	@Pattern(regexp = "[A-Za-z0-9]*", message = "IFSC code must only be alphanumeric")
	private String ifscCode;

	private int micrCode;

	private String bankName;

	private String city;

}
