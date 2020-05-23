package testControllers;

import com.company.config.WebConfiguration;
import com.company.controller.AdminController;
import com.company.model.domain.user.User;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.validateMockitoUsage;

/**
 * This class is integration test class for {@link AdminController}.
 *
 * @author Igor Ivanov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestAdminController
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
     * This field is mock for user's service.
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
     * This test method tests handlers "/admin" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnAdminView_whenPassRequest() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.view().name("admin"));
    }

    /**
     * This test method tests handlers ""/admin/set/discount/"" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequest() throws Exception
    {
        Optional<List<User>> expectedResult = Optional.of(Collections.singletonList(new User()));
        MockHttpSession session = new MockHttpSession();
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/").session(session))
                .andExpect(MockMvcResultMatchers.view().name("set_discount"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));
    }

    /**
     * This test method tests handlers "/admin/set/discount/1" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequestWithNumberPage() throws Exception
    {
        Optional<List<User>> expectedResult = Optional.of(Collections.singletonList(new User()));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersForSetDiscount", new PagedListHolder<>());
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/1").session(session))
                .andExpect(MockMvcResultMatchers.view().name("set_discount"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));
    }

    /**
     * This test method tests handlers "/admin/set/discount/prev" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequestWithPrevPage() throws Exception
    {
        Optional<List<User>> expectedResult = Optional.of(Collections.singletonList(new User()));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersForSetDiscount", new PagedListHolder<>());
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/prev").session(session))
                .andExpect(MockMvcResultMatchers.view().name("set_discount"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));
    }

    /**
     * This test method tests handlers "/admin/set/discount/next" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequestWithNextPage() throws Exception
    {
        Optional<List<User>> expectedResult = Optional.of(Collections.singletonList(new User()));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersForSetDiscount", new PagedListHolder<>());
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/next").session(session))
                .andExpect(MockMvcResultMatchers.view().name("set_discount"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));
    }

    /**
     * This test method tests handlers "/admin/set/discount/" request when get empty optional from service.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnErrorView_whenGetEmptyOptionalFromService() throws Exception
    {
        Optional<List<User>> expectedResult = Optional.empty();
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/"))
                .andExpect(MockMvcResultMatchers.view().name("generic_error"));
    }

    /**
     * This test method tests handlers "/admin/set/discount/set" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnAdminViewAndCallServiceMethod_whenPassRequest() throws Exception
    {
        User user = new User();
        user.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/set/discount/set")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.view().name("admin"));
    }

    /**
     * This test method tests handlers "/admin/set/discount/set" request when pass request with empty identifier Of User.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnSetDiscountView_whenPassRequestWithEmptyIdOfUser() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/set/discount/set")
                .param("id", ""))
                .andExpect(MockMvcResultMatchers.view().name("set_discount"));
    }

}
