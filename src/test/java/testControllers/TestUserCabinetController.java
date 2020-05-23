package testControllers;

import com.company.config.WebConfiguration;
import com.company.controller.UserCabinetController;
import com.company.model.domain.order.Order;
import com.company.model.service.user.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.validateMockitoUsage;

/**
 * This class is integration test class for {@link UserCabinetController}.
 *
 * @author Igor Ivanov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestUserCabinetController
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
     * This test method tests handlers "/user" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequest() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Collections.singletonList(new Order());
        MockHttpSession session = new MockHttpSession();
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));
    }

    /**
     * This test method tests handlers "/user/1" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequestWithNumberPage() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Collections.singletonList(new Order());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersOrders", new PagedListHolder<>());
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));
    }

    /**
     * This test method tests handlers "/user/prev" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequestWithPrevPage() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Collections.singletonList(new Order());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersOrders", new PagedListHolder<>());
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/prev").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));
    }

    /**
     * This test method tests handlers "/user/next" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequestWithNextPage() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Collections.singletonList(new Order());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersOrders", new PagedListHolder<>());
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/next").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));
    }
}
