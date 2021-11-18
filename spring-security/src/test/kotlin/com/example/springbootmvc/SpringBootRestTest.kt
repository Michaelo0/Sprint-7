package com.example.springbootmvc

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class SpringBootRestTest {
    @LocalServerPort
    private var port: Int = 0

    private fun url(s: String): String {
        return "http://localhost:${port}/${s}"
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    val person = """{"name" : "John"}"""

    @Test
    @WithMockUser(username = "api", password = "api", roles = ["API"])
    fun getListOfRecords() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(url("api/list"))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @WithMockUser(username = "api", password = "api", roles = ["API"])
    fun getRecord() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(url("api/0/view"))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @WithMockUser(username = "api", password = "api", roles = ["API"])
    fun editRecord() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/0/edit")
                .content(person)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @WithMockUser(username = "api", password = "api", roles = ["API"])
    fun addRecord() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/add")
                .content(person)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @WithMockUser(username = "api", password = "api", roles = ["API"])
    fun deleteRecord() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/0/delete")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}