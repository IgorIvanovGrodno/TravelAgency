package testControllers;

import com.company.config.WebConfiguration;
import com.company.controller.RegistrationController;
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

/**
 * This class is integration test class for {@link RegistrationController}.
 *
 * @author Igor Ivanov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestRegistrationController
{
    /**
     * This field is main entry point for server-side Spring MVC test support.
     */
    private MockMvc mockMvc;

    /**
     * This field is configuration for a web application.
     */
    @Autowired
    private WebApplicationContext webConfiguration;

    /**
     * This field is user's service.
     */
    @Autowired
    private UserService userServiceMock;

    /**
     * This method executes before each method, resets user's service mock, builds and assigns mockMVC.
     */
    @Before
    public void SetUp()
    {
        Mockito.reset(userServiceMock);
        Mockito.clearInvocations(userServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webConfiguration).build();
    }

    /**
     * This method execute after each method and call validateMockitoUsage().
     */
    @After
    public void validate()
    {
        validateMockitoUsage();
    }

    /**
     * This test method tests handlers "/registration" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnRegistrationViewAndModelWithExpectedAttributes_whenPassRequest() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("registeredUser"))
                .andExpect(MockMvcResultMatchers.view().name("registration"));
    }

    /**
     * This test method tests handlers "/registration/user" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnAuthorizationView_whenPassRequest() throws Exception
    {
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

    /**
     * This test method tests handlers "/registration/user" request when pass incorrect param request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnRegistrationViewAndCallMethodOfService_whenPassIncorrectParamRequest() throws Exception
    {
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
