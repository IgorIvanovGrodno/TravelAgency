package TestControllers;

import com.company.config.WebConfiguration;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestOrderController {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webConfiguration;

    @Autowired
    private FacadeTourPackage facadeTourPackageMock;

    @Autowired
    private FacadeOrder facadeOrderMock;

    @Before
    public void SetUp(){
        Mockito.reset(facadeTourPackageMock);
        Mockito.reset(facadeOrderMock);
        Mockito.clearInvocations(facadeOrderMock);
        Mockito.clearInvocations(facadeTourPackageMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webConfiguration).build();
    }
    @After
    public void validate() {
        validateMockitoUsage();
    }
    @Test
    public  void shouldReturnOrderViewAndModelExpectedAttributes_whenPassRequest() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        TourPackage tourPackage = new TourPackage();
        tourPackage.setId(1L);
        Mockito.when(facadeTourPackageMock.getTotalPrice(Mockito.anyInt(),Mockito.any())).thenReturn(1.0);
        Mockito.when(facadeTourPackageMock.getTourPackage(Mockito.any())).thenReturn(Optional.of(tourPackage));
        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/order").session(session).principal(principal)
                .param("id","1")
                .param("name","")
                .param("type.name","type")
                .param("foodSystem.name","food")
                .param("transport.name","transport")
                .param("days","1")
                .param("price","1")
                .param("statusHot","true"))
                .andExpect(MockMvcResultMatchers.view().name("order"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalPrice"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("order"));

        Assert.assertNotNull(session.getAttribute("tourPackageForOrder"));
    }

    @Test
    public  void shouldReturnIndexViewAndModelExpectedAttributes_whenPassRequestWitnNullIdTourPackage() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(facadeTourPackageMock.getTotalPrice(Mockito.anyInt(),Mockito.any())).thenReturn(1.0);
        Mockito.when(facadeTourPackageMock.getTourPackage(Mockito.any())).thenReturn(Optional.of(new TourPackage()));
        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(MockMvcRequestBuilders.get("/user/order").session(session).principal(principal)
                .param("id","")
                .param("name","")
                .param("type","")
                .param("foodSystem","")
                .param("transport","")
                .param("days","")
                .param("price","")
                .param("statusHot",""))
                .andExpect(MockMvcResultMatchers.view().name("index"));

    }

    @Test
    public  void shouldRedirectToUserCabinetViewAndCallMethodOfFacadeOrder_whenPassRequest() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.verify(facadeOrderMock).makePayment(Mockito.any(), Mockito.any(), Mockito.anyString());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackageForOrder", new TourPackage());
        mockMvc.perform(MockMvcRequestBuilders.get("/user/order/pay").session(session).principal(principal)
                .param("id","1")
                .param("numberCard","1234")
                .param("totalCost","1"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user"));
    }

    @Test
    public  void shouldReturnOrderViewAndModelExpectedAttributes_whenPassRequestWithIncorrectNumberCard() throws Exception {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.verify(facadeOrderMock, Mockito.never()).makePayment(Mockito.any(), Mockito.any(), Mockito.anyString());
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackageForOrder", new TourPackage());
        mockMvc.perform(MockMvcRequestBuilders.get("/user/order/pay").session(session).principal(principal)
                .param("id","")
                .param("numberCard","")
                .param("totalCost",""))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tourPackageForOrder"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("totalPrice"))
                .andExpect(MockMvcResultMatchers.view().name("order"));
    }
}
