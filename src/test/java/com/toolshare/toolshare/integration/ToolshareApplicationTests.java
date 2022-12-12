package com.toolshare.toolshare.integration;

import com.toolshare.toolshare.controller.*;
import com.toolshare.toolshare.repository.ParticipantRepository;
import com.toolshare.toolshare.security.CustomUserDetailsService;
import com.toolshare.toolshare.security.jwt.JwtProviderImpl;
import com.toolshare.toolshare.service.participantservice.ParticipantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Integration Testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class ToolshareApplicationTests {

	//Testing the beans for all the controllers
	@Autowired
	private ParticipantController participantController;

	@Autowired
	private ItemController itemController;

	@Autowired
	private LoanController loanController;

	@Autowired
	private UserController userController;

	@Autowired
	private FileController fileController;

	@Autowired
	private AuthenticationController authenticationController;

	//Testing functionalities from different ports than 8080
	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(itemController);
		assertNotNull(loanController);
		assertNotNull(userController);
		assertNotNull(fileController);
		assertNotNull(participantController);
		assertNotNull(authenticationController);
		assertNotNull(mockMvc);
	}

	@Test
	public void testGetAllParticipants() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:" + port + "/api/participant/participants");
		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetAllShareItems() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:" + port + "/api/items/items");
		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	public void testGetAllUsers() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:" + port + "/api/user");
		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful());

	}
}
