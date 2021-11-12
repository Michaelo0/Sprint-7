package com.example.demo

import com.example.demo.controller.Controller
//import com.example.demo.persistance.Entity
//import com.example.demo.persistance.EntityRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

//@SpringBootTest
@WebMvcTest(Controller::class)
class DemoApplicationTests {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun `Get hello should return content successfully with status 200 OK`() {
		val result = mockMvc.perform(MockMvcRequestBuilders.get("/privet"))

		result.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.content().string("privet"))
	}

}
