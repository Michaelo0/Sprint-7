package com.example.springbootmvc

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant
import javax.servlet.http.Cookie


@SpringBootTest
@AutoConfigureMockMvc
class SpringbootmvcApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun addNote() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/app/add")
                .param("name", "John")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun deleteNote() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/app/0/delete")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("redirect:/app/list")))
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun editNote() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/app/0/edit")
                .param("name", "John")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("redirect:/app/list")))
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    fun listAll() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/app/list")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("app-page")))
    }
}
