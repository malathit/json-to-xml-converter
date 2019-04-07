package com.risksense;

import com.risksense.controllers.JsonToXmlController;
import com.risksense.converters.ConverterFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JsonToXmlController.class)
public class JsonToXmlConverterApplicationTests {

	@Autowired
	private MockMvc mvc;

	@SpyBean
	private ConverterFactory converterFactory;

	@Test
	public void testValidJsonObject() {
		File file = null;
		String input;
		String expectedOutput;
		try {
			file = new File(
				this.getClass().getClassLoader().getResource("example.json").getPath());
			input = Files.readAllLines(file.toPath()).stream().reduce((x, y) -> x + y).get();
			file = new File(
					this.getClass().getClassLoader().getResource("example.xml").getPath());
			expectedOutput = Files.readAllLines(file.toPath()).stream().reduce((x, y) -> x + y).get();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("test input file not found" + e.getMessage());
			return;
		}
		try {
			mvc.perform(MockMvcRequestBuilders.post("/api/v1/json2xml")
				.contentType(MediaType.TEXT_PLAIN_VALUE)
				.content(input))
				.andExpect(status().isOk())
				.andExpect(content().xml(expectedOutput))
				.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed : " + e.getMessage());
		}
	}

	@Test
	public void testInvalidJsonInput() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/api/v1/json2xml")
					.contentType(MediaType.TEXT_PLAIN_VALUE)
					.content("5"))
					.andExpect(status().isBadRequest());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed : " + e.getMessage());
		}
	}
}
