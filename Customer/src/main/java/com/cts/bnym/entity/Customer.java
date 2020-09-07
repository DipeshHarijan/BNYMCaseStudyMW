package com.cts.bnym.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Id
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]", message = "Pan should be of 10 characters with format BNGPS8372Q")
	private String pan;

	@Column(nullable = false)
	private String password;

	@Email
	@Column(nullable = false)
	private String email;

	@Pattern(regexp = "[1-9]{1}[0-9]{9}", message = "mobile number must be of 10 digits and should not begin with 0")
	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	public Customer(String firstName, String lastName,
			@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]", message = "Pan should be of 10 characters with format BNGPS8372Q") String pan,
			String password, @Email String email,
			@Pattern(regexp = "[1-9]{1}[0-9]{9}", message = "mobile number must be of 10 digits and should not begin with 0") String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.pan = pan;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

}
