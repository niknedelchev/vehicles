package com.nn.vehicles.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nn.vehicles.security.UserPrincipalDetailsService;
import com.nn.vehicles.service.CustomerService;
import com.nn.vehicles.service.RepairShopService;
import com.nn.vehicles.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
	
	@MockBean
	private CustomerService customerService;
	@MockBean
	private RepairShopService repairShopService;
	@MockBean
	private UserPrincipalDetailsService userPrincipalDetailsService;
	
	@MockBean
	private UserService userService;
	

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testShowCustomerProfilePage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
    		    .get("/customer"))
    		    .andExpect(status().is(302));

	}

	

	
}
