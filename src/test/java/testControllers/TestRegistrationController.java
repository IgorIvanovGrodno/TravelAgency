package testControllers;

import com.company.config.WebConfiguration;
import com.company.model.service.user.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.validateMockitoUsage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestRegistrationController {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webConfiguration;

    @Autowired
    private UserService userServiceMock;

    @Before
    public void SetUp(){
        Mockito.reset(userServiceMock);
        Mockito.clearInvocations(userServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webConfiguration).build();
    }
    @After
    public void validate() {
        validateMockitoUsage();
    }
    @Test
    public void shouldReturnRegistrationViewAndModelWithExpectedAttributes_whenPassRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("registeredUser"))
                .andExpect(MockMvcResultMatchers.view().name("registration"));
    }

    @Test
    public void shouldReturnAuthorizationView_whenPassRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/registration/user")
                .param("passwordRepeat", "1")
                .param("firstName", "1")
                .param("secondName", "1")
                .param("discount", "0")
                .param("phoneNumber", "+375291111111")
                .param("email", "q@q.q")
                .param("authorization.login", "1")
                .param("authorization.password", "1")
                .param("authorization.role", "")
                .param("authorization.active", "true"))
                .andExpect(MockMvcResultMatchers.view().name("authorization"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("registeredUser"));
    }

    @Test
    public void shouldReturnRegistrationViewAndCallMethodOfService_whenPassIncorrectParamRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/registration/user")
                .param("passwordRepeat", "2")
                .param("firstName", "1")
                .param("secondName", "1")
                .param("discount", "0")
                .param("phoneNumber", "")
                .param("email", "q")
                .param("authorization.login", "1")
                .param("authorization.password", "1")
                .param("authorization.role", "")
                .param("authorization.active", "true"))
                .andExpect(MockMvcResultMatchers.view().name("registration"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("registeredUser"));
    }
}
