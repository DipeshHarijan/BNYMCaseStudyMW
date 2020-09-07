package com.cts.bnym.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.bnym.entity.Account;
import com.cts.bnym.service.BankAccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BankAccountService service;

	@Test
	public void getAllAccountsTest() throws Exception {
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account("8374720281", "AUQ7383AD", "BankOne", 7387327, "ANCIF1000K"));
		when(service.getAllAccounts()).thenReturn(accounts);

		RequestBuilder request = MockMvcRequestBuilders.get("/account/getall").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"[{accountNumber:\"8374720281\", ifscCode:AUQ7383AD,bankName:BankOne,micrCode:7387327,pan:ANCIF1000K}]"))
				.andReturn();

	}

	@Test
	public void getAccountsByPanTest() throws Exception {
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account("8374720281", "AUQ7383AD", "BankOne", 7387327, "ANCIF1000K"));
		when(service.getByPan("ANCIF1000K")).thenReturn(accounts);

		RequestBuilder request = MockMvcRequestBuilders.get("/account/getbypan/{pan}", "ANCIF1000K")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"[{accountNumber:\"8374720281\", ifscCode:AUQ7383AD,bankName:BankOne,micrCode:7387327,pan:ANCIF1000K}]"))
				.andReturn();

	}

	@Test
	public void addAccountTest() throws Exception {
		Account account = new Account("8374720281", "AUQ7383AD", "BankOne", 7387327, "ANCIF1000K");
		when(service.addAccount(Mockito.any(Account.class))).thenReturn(account);

		RequestBuilder request = MockMvcRequestBuilders.post("/account/add").accept(MediaType.APPLICATION_JSON).content(
				"{\"accountNumber\":\"8374720281\", \"ifscCode\":\"AUQ7383AD\",\"bankName\":\"BankOne\",\"micrCode\":7387327,\"pan\":\"ANCIF1000K\"}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void updateAccountTest() throws Exception {

		Account account = new Account("8374720281", "AUQ7383AD", "BankOne", 7387327, "ANCIF1000K");
		when(service.getAccount("8374720281")).thenReturn(account);
		when(service.addAccount(Mockito.any(Account.class))).thenReturn(account);

		RequestBuilder request = MockMvcRequestBuilders.put("/account/update").accept(MediaType.APPLICATION_JSON)
				.content(
						"{\"accountNumber\":\"8374720281\", \"ifscCode\":\"AUQ7383AD\",\"bankName\":\"BankOne\",\"micrCode\":7387327,\"pan\":\"ANCIF1000K\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().is(202)).andReturn();
	}

	@Test
	public void deleteAccountTest() throws Exception {

		Account account = new Account("8374720281", "AUQ7383AD", "BankOne", 7387327, "ANCIF1000K");
		when(service.getAccount("8374720281")).thenReturn(account);

		RequestBuilder request = MockMvcRequestBuilders.delete("/account/delete/{accountNumber}", "8374720281");

		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
}
