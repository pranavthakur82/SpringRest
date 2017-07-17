package net.viralpatel.spring.test.controller;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.viralpatel.spring.controller.CustomerRestController;
import net.viralpatel.spring.dao.CustomerDAO;

public class CustomerRestControllerUnitTest {
	private MockMvc mockMvc;
	
	@Mock
	private CustomerDAO customerDAO;
	
	@InjectMocks
	private CustomerRestController customerRestController;
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerRestController)
                .build();
    }
}
