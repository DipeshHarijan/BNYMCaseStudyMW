package com.cts.bnym.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Customer {

	private String firstName;
	private String lastName;
	private String pan;
	private String password;
	private String email;
	private String phone;
	private LocalDate dob;

}
