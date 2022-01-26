package com.nn.vehicles.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nn.vehicles.security.UserPrincipalDetailsService;
import com.nn.vehicles.service.BrandService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BrandController.class)
class BrandControllerTest {

	@MockBean
	private BrandService brandService;
	@MockBean
	private UserPrincipalDetailsService userPrincipalDetailsService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test() throws Exception {
	     mockMvc.perform(MockMvcRequestBuilders
	    .get("/brands"))
	    .andExpect(status().is(302));


	
	}

}
