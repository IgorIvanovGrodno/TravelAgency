package testControllers;

import com.company.config.WebConfiguration;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.validateMockitoUsage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestUserCabinetController {
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
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequest() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Arrays.asList(new Order());
        MockHttpSession session = new MockHttpSession();
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));

    }

    @Test
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequestWithNumberPage() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Arrays.asList(new Order());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersOrders", new PagedListHolder<>());
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));
    }

    @Test
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequestWithPrevPage() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Arrays.asList(new Order());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersOrders", new PagedListHolder<>());
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/prev").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));
    }

    @Test
    public void shouldReturnUserCabinetViewAndSessionWithExpectedAttributes_whenPassRequestWithNextPage() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("login");
        List<Order> expectedResult = Arrays.asList(new Order());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersOrders", new PagedListHolder<>());
        Mockito.when(userServiceMock.getUsersOrders("login"))
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/next").session(session).principal(principal))
                .andExpect(MockMvcResultMatchers.view().name("user_cabinet"));
        Assert.assertNotNull(session.getAttribute("usersOrders"));
    }

}
