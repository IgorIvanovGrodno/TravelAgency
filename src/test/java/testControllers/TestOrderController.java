package testControllers;

import com.company.config.WebConfiguration;
import com.company.controller.OrderController;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.service.facade.FacadeOrder;
import com.company.model.service.facade.FacadeTourPackage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

import static org.mockito.Mockito.validateMockitoUsage;

/**
 * This class is integration test class for {@link OrderController}.
 *
 * @author Igor Ivanov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestOrderController
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
     * This field is mock for tour package's facade.
     */
    @Autowired
    private FacadeTourPackage facadeTourPackageMock;

    /**
     * This field is mock for order's facade.
     */
    @Autowired
    private FacadeOrder facadeOrderMock;

    /**
     * This method executes before each method, resets tour package's facade and order's facade mocks, builds and assigns mockMVC.
     */
    @Before
    public void SetUp()
    {
        Mockito.reset(facadeTourPackageMock);
        Mockito.reset(facadeOrderMock);
        Mockito.clearInvocations(facadeOrderMock);
        Mockito.clearInvocations(facadeTourPackageMock);
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
     * This test method tests handlers "/user/order" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnOrderViewAndModelExpectedAttributes_whenPassRequest() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        TourPackage tourPackage = new TourPackage();
        tourPackage.setId(1L);
        Mockito.when(facadeTourPackageMock.getTotalPrice(Mockito.anyInt(), Mockito.any())).thenReturn(1.0);
        Mockito.when(facadeTourPackageMock.getTourPackage(Mockito.any())).thenReturn(Optional.of(tourPackage));
        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/order").session(session).principal(principal)
                .param("id", "1")
                .param("name", "")
                .param("type.name", "type")
                .param("foodSystem.name", "food")
                .param("transport.name", "transport")
                .param("days", "1")
                .param("price", "1")
                .param("statusHot", "true"))
                .andExpect(MockMvcResultMatchers.view().name("order"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalPrice"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("order"));

        Assert.assertNotNull(session.getAttribute("tourPackageForOrder"));
    }

    /**
     * This test method tests handlers "/user/order" request when pass request with null identifier of tour package.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnIndexViewAndModelExpectedAttributes_whenPassRequestWithNullIdTourPackage() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(facadeTourPackageMock.getTotalPrice(Mockito.anyInt(), Mockito.any())).thenReturn(1.0);
        Mockito.when(facadeTourPackageMock.getTourPackage(Mockito.any())).thenReturn(Optional.of(new TourPackage()));
        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/order").session(session).principal(principal)
                .param("id", "")
                .param("name", "")
                .param("type", "")
                .param("foodSystem", "")
                .param("transport", "")
                .param("days", "")
                .param("price", "")
                .param("statusHot", ""))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    /**
     * This test method tests handlers "/user/order/pay" request.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldRedirectToUserCabinetView_whenPassRequest() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackageForOrder", new TourPackage());
        mockMvc.perform(MockMvcRequestBuilders.get("/user/order/pay").session(session).principal(principal)
                .param("id", "1")
                .param("numberCard", "1234")
                .param("totalCost", "1"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user"));
    }

    /**
     * This test method tests handlers "/user/order/pay" request when pass request with incorrect number card.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnOrderViewAndModelExpectedAttributes_whenPassRequestWithIncorrectNumberCard() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackageForOrder", new TourPackage());
        mockMvc.perform(MockMvcRequestBuilders.get("/user/order/pay").session(session).principal(principal)
                .param("id", "")
                .param("numberCard", "")
                .param("totalCost", ""))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tourPackageForOrder"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalPrice"))
                .andExpect(MockMvcResultMatchers.view().name("order"));
    }
}
