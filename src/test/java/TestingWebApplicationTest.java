import com.example.Application;
import com.example.controllers.UserController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class TestingWebApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testHome() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isNotFound());
                //.andExpect(content().string(containsString("Hello, World")));
                //.andExpect(status().isNotFound());
    }

    @Test
    public void testAll() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk());
        //.andExpect(content().string(containsString("Hello, World")));
        //.andExpect(status().isNotFound());
    }

    @Test
    public void testDel() throws Exception {
        this.mockMvc.perform(get("/del/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        //.andExpect(content().string(containsString("Hello, World")));
        //.andExpect(status().isNotFound());
    }
}