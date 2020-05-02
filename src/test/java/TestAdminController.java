import com.company.config.WebConfiguration;
import com.company.model.domain.user.User;
import com.company.model.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestAdminController {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webConfiguration;

    @Autowired
    @Qualifier("userIdValidator")
    private Validator userIdValidator;

    @Autowired
    private UserService userServiceMock;

    @Before
    public void SetUp(){
        Mockito.reset(userServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webConfiguration).build();
    }

    @Test
    public void shouldReturnAdminView_whenPassRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.view().name("admin"));
    }

    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequest() throws Exception {
        Optional<List<User>> expectedResult = Optional.of(Arrays.asList(new User()));
        MockHttpSession session = new MockHttpSession();
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/").session(session))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));

    }

    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequestWithNumberPage() throws Exception {
        Optional<List<User>> expectedResult = Optional.of(Arrays.asList(new User()));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersForSetDiscount",new PagedListHolder<>());
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/1").session(session))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));
    }

    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequestWithPrevPage() throws Exception {
        Optional<List<User>> expectedResult = Optional.of(Arrays.asList(new User()));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersForSetDiscount",new PagedListHolder<>());
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/prev").session(session))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));
    }

    @Test
    public void shouldReturnSetDiscountViewAndModelWithExpectedAttributes_whenPassRequestWithNextPage() throws Exception {
        Optional<List<User>> expectedResult = Optional.of(Arrays.asList(new User()));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usersForSetDiscount",new PagedListHolder<>());
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/next").session(session))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userWithUpdateDiscount"));
        Assert.assertNotNull(session.getAttribute("usersForSetDiscount"));
    }

    @Test
    public void shouldReturnErrorView_whenGetEmptyOptionalFromService() throws Exception {
        Optional<List<User>> expectedResult = Optional.ofNullable(null);
        Mockito.when(userServiceMock.getAllUsers())
                .thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/set/discount/"))
                .andExpect(MockMvcResultMatchers.view().name("generic_error"));
    }

}
