package com.nn.vehicles.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nn.vehicles.security.UserPrincipalDetailsService;
import com.nn.vehicles.service.QualificationService;
import com.nn.vehicles.service.RepairShopService;
import com.nn.vehicles.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
class AdminControllerTest {
	
	@MockBean
	private UserService userService;
	@MockBean
	private QualificationService qualificationService;
	@MockBean
	private RepairShopService repairShopService;
	@MockBean 
	UserPrincipalDetailsService userPrincipalDetailsService;
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	void testShowAdminPage() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
			    		    .get("/admin"))
			    		    .andExpect(status().is(302));

		}
	
}
