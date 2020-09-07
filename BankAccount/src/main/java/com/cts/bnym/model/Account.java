package com.cts.bnym.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
	
	private String accountNumber;
	private String ifscCode;
	private String bankName;
	private long micrCode;
	private String pan;

}
