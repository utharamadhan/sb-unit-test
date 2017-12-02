package com.rpramadhan.sbunittest.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpramadhan.sbunittest.main.SBUnitTestMain;
import com.rpramadhan.sbunittest.model.Book;
import com.rpramadhan.sbunittest.model.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SBUnitTestMain.class)
public class GetBookTest {
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void findByIdSuccess() throws Exception {
		Long _testId = 1L;
		mockMvc.perform(MockMvcRequestBuilders.get("/"+_testId))
			.andExpect(new ResultMatcher() {
				@Override
				public void match(MvcResult result) throws Exception {
					Response response = mapper.readValue(result.getResponse().getContentAsString(), Response.class);
					Assert.assertEquals("00", response.getResponseCode());
					Assert.assertEquals("success", response.getResponseDesc());
					Book book = mapper.readValue(mapper.writeValueAsString(response.getResult()), Book.class);
					Assert.assertEquals("How To Win Friends And Influence People", book.getTitle());
					Assert.assertEquals("Dale Carnegie", book.getAuthor());
				}
			});
	}
	
	@Test
	public void findByIdNotFound() throws Exception {
		Long _testId = 10L;
		mockMvc.perform(MockMvcRequestBuilders.get("/"+_testId))
			.andExpect(new ResultMatcher() {
				@Override
				public void match(MvcResult result) throws Exception {
					Response response = mapper.readValue(result.getResponse().getContentAsString(), Response.class);
					Assert.assertEquals("04", response.getResponseCode());
					Assert.assertEquals("not found", response.getResponseDesc());
				}
			});
	}
	
}
