package com.cts.bnym.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
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

import com.cts.bnym.entity.Customer;
import com.cts.bnym.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;

	@Test
	public void getAllCustomersTest() throws Exception {
		List<Customer> customer = new ArrayList<>();
		customer.add(new Customer("Tenali", "Raman", "ANCIF2000K", "8978ABC", "tenali@gmail.com", "8978987678",
				LocalDate.of(1524, 4, 23)));
		when(service.getAllCustomers()).thenReturn(customer);

		RequestBuilder request = MockMvcRequestBuilders.get("/customer/getall").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"[{\"firstName\":\"Tenali\",\"lastName\":\"Raman\",\"pan\":\"ANCIF2000K\",\"password\":\"8978ABC\",\"email\":\"tenali@gmail.com\",\"phone\":\"8978987678\",\"dob\":\"04/23/1524\"}]"))
				.andReturn();

	}

	@Test
	public void getCustomerByPanTest() throws Exception {

		when(service.getCustomer("ANCIF2000K")).thenReturn(new Customer("Tenali", "Raman", "ANCIF2000K", "8978ABC",
				"tenali@gmail.com", "8978987678", LocalDate.of(1524, 4, 23)));

		RequestBuilder request = MockMvcRequestBuilders.get("/customer/get/{pan}", "ANCIF2000K")
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"{\"firstName\":\"Tenali\",\"lastName\":\"Raman\",\"pan\":\"ANCIF2000K\",\"password\":\"8978ABC\",\"email\":\"tenali@gmail.com\",\"phone\":\"8978987678\",\"dob\":\"04/23/1524\"}"))
				.andReturn();

	}

	@Test
	public void addCustomerTest() throws Exception {
		Customer customer = new Customer("Tenali", "Raman", "ANCIF2000K", "8978ABC", "tenali@gmail.com", "8978987678",
				LocalDate.of(1524, 4, 23));

		when(service.addCustomer(Mockito.any(Customer.class))).thenReturn(customer);

		RequestBuilder request = MockMvcRequestBuilders.post("/customer/add").accept(MediaType.APPLICATION_JSON)
				.content(
						"{\"firstName\":\"Tenali\",\"lastName\":\"Raman\",\"pan\":\"ANCIF2000K\",\"password\":\"8978ABC\",\"email\":\"tenali@gmail.com\",\"phone\":\"8978987678\",\"dob\":\"04/23/1524\"}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}
