package testControllers;

import com.company.config.WebConfiguration;
import com.company.controller.ControllerAdvisor;
import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.service.facade.FacadeTourPackage;
import org.junit.After;
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
 * This class is integration test class for {@link ControllerAdvisor}.
 *
 * @author Igor Ivanov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestControllerAdvisor
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
     * This method executes before each method, resets tour package's facade mock, builds and assigns mockMVC.
     */
    @Before
    public void SetUp()
    {
        Mockito.reset(facadeTourPackageMock);
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
     * This test method tests handler ServiceException.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnGenericErrorViewAndExpectedModelAttribute_whenThrowServiceException() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        TourPackage tourPackage = new TourPackage();
        tourPackage.setId(1L);
        Mockito.when(facadeTourPackageMock.getTotalPrice(Mockito.anyInt(), Mockito.any())).thenThrow(new ServiceException("message"));
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
                .andExpect(MockMvcResultMatchers.view().name("generic_error"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
    }

    /**
     * This test method tests handler ControllerException.
     *
     * @throws Exception when mockMVC throws Exception.
     */
    @Test
    public void shouldReturnGenericErrorViewAndExpectedModelAttribute_whenThrowControllerException() throws Exception
    {
        Principal principal = Mockito.mock(Principal.class);
        TourPackage tourPackage = new TourPackage();
        tourPackage.setId(1L);
        Mockito.when(facadeTourPackageMock.getTotalPrice(Mockito.anyInt(), Mockito.any())).thenReturn(1.0);
        Mockito.when(facadeTourPackageMock.getTourPackage(Mockito.any())).thenReturn(Optional.empty());
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
                .andExpect(MockMvcResultMatchers.view().name("generic_error"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("message"));
    }
}
