package testControllers;

import com.company.config.WebConfiguration;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.service.facade.FacadeTourPackage;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.validateMockitoUsage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestIndexController {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webConfiguration;

    @Autowired
    private FacadeTourPackage facadeTourPackageMock;

    @Before
    public void SetUp(){
        Mockito.reset(facadeTourPackageMock);
        Mockito.clearInvocations(facadeTourPackageMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webConfiguration).build();
    }
    @After
    public void validate() {
        validateMockitoUsage();
    }
    @Test
    public void shouldReturnIndexViewAndModelWithExpectedAttributes_whenPassRequest() throws Exception {
        List<TourPackage> expectedListTourPackages = Arrays.asList(new TourPackage());
        Optional<List<TypeTourPackage>> expectedTypes = Optional.of(Arrays.asList(new TypeTourPackage()));
        Optional<List<Transport>> expectedTransports = Optional.of(Arrays.asList(new Transport()));
        Optional<List<FoodSystem>> expectedFoodSystems = Optional.of(Arrays.asList(new FoodSystem()));

        MockHttpSession session = new MockHttpSession();
        Mockito.when(facadeTourPackageMock.getTourPackages())
                .thenReturn(expectedListTourPackages);
        Mockito.when(facadeTourPackageMock.getTypesOfTours())
                .thenReturn(expectedTypes);
        Mockito.when(facadeTourPackageMock.getTransportsOfTours())
                .thenReturn(expectedTransports);
        Mockito.when(facadeTourPackageMock.getFoodSystemsOfTours())
                .thenReturn(expectedFoodSystems);

        mockMvc.perform(MockMvcRequestBuilders.get("/").session(session))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tourPackageForOrder"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("selectsParameters"));

        Assert.assertNotNull(session.getAttribute("tourPackages"));
        Assert.assertNotNull(session.getAttribute("types"));
        Assert.assertNotNull(session.getAttribute("transports"));
        Assert.assertNotNull(session.getAttribute("foodSystemList"));
    }

    @Test
    public void shouldReturnIndexViewAndModelWithExpectedAttributes_whenPassRequestWithNumberPage() throws Exception {
        List<TourPackage> expectedListTourPackages = Arrays.asList(new TourPackage());
        List<TypeTourPackage> types =Arrays.asList(new TypeTourPackage());
        List<Transport> transports =Arrays.asList(new Transport());
        List<FoodSystem> foodSystems =Arrays.asList(new FoodSystem());

        Optional<List<TypeTourPackage>> expectedTypes = Optional.of(types);
        Optional<List<Transport>> expectedTransports = Optional.of(transports);
        Optional<List<FoodSystem>> expectedFoodSystems = Optional.of(foodSystems);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackages",new PagedListHolder<>());
        session.setAttribute("types", types);
        session.setAttribute("transports",transports);
        session.setAttribute("foodSystemList",foodSystems);

        Mockito.when(facadeTourPackageMock.getTourPackages())
                .thenReturn(expectedListTourPackages);
        Mockito.when(facadeTourPackageMock.getTypesOfTours())
                .thenReturn(expectedTypes);
        Mockito.when(facadeTourPackageMock.getTransportsOfTours())
                .thenReturn(expectedTransports);
        Mockito.when(facadeTourPackageMock.getFoodSystemsOfTours())
                .thenReturn(expectedFoodSystems);

        mockMvc.perform(MockMvcRequestBuilders.get("/1").session(session))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tourPackageForOrder"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("selectsParameters"));

        Assert.assertNotNull(session.getAttribute("tourPackages"));
        Assert.assertNotNull(session.getAttribute("types"));
        Assert.assertNotNull(session.getAttribute("transports"));
        Assert.assertNotNull(session.getAttribute("foodSystemList"));
    }


    @Test
    public void shouldReturnIndexViewAndModelWithExpectedAttributes_whenPassRequestWithPrevPage() throws Exception {
        List<TourPackage> expectedListTourPackages = Arrays.asList(new TourPackage());
        List<TypeTourPackage> types =Arrays.asList(new TypeTourPackage());
        List<Transport> transports =Arrays.asList(new Transport());
        List<FoodSystem> foodSystems =Arrays.asList(new FoodSystem());

        Optional<List<TypeTourPackage>> expectedTypes = Optional.of(types);
        Optional<List<Transport>> expectedTransports = Optional.of(transports);
        Optional<List<FoodSystem>> expectedFoodSystems = Optional.of(foodSystems);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackages",new PagedListHolder<>());
        session.setAttribute("types", types);
        session.setAttribute("transports",transports);
        session.setAttribute("foodSystemList",foodSystems);

        Mockito.when(facadeTourPackageMock.getTourPackages())
                .thenReturn(expectedListTourPackages);
        Mockito.when(facadeTourPackageMock.getTypesOfTours())
                .thenReturn(expectedTypes);
        Mockito.when(facadeTourPackageMock.getTransportsOfTours())
                .thenReturn(expectedTransports);
        Mockito.when(facadeTourPackageMock.getFoodSystemsOfTours())
                .thenReturn(expectedFoodSystems);

        mockMvc.perform(MockMvcRequestBuilders.get("/prev").session(session))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tourPackageForOrder"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("selectsParameters"));

        Assert.assertNotNull(session.getAttribute("tourPackages"));
        Assert.assertNotNull(session.getAttribute("types"));
        Assert.assertNotNull(session.getAttribute("transports"));
        Assert.assertNotNull(session.getAttribute("foodSystemList"));
    }

    @Test
    public void shouldReturnIndexViewAndModelWithExpectedAttributes_whenPassRequestWithNextPage() throws Exception {
        List<TourPackage> expectedListTourPackages = Arrays.asList(new TourPackage());
        List<TypeTourPackage> types =Arrays.asList(new TypeTourPackage());
        List<Transport> transports =Arrays.asList(new Transport());
        List<FoodSystem> foodSystems =Arrays.asList(new FoodSystem());

        Optional<List<TypeTourPackage>> expectedTypes = Optional.of(types);
        Optional<List<Transport>> expectedTransports = Optional.of(transports);
        Optional<List<FoodSystem>> expectedFoodSystems = Optional.of(foodSystems);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackages",new PagedListHolder<>());
        session.setAttribute("types", types);
        session.setAttribute("transports",transports);
        session.setAttribute("foodSystemList",foodSystems);

        Mockito.when(facadeTourPackageMock.getTourPackages())
                .thenReturn(expectedListTourPackages);
        Mockito.when(facadeTourPackageMock.getTypesOfTours())
                .thenReturn(expectedTypes);
        Mockito.when(facadeTourPackageMock.getTransportsOfTours())
                .thenReturn(expectedTransports);
        Mockito.when(facadeTourPackageMock.getFoodSystemsOfTours())
                .thenReturn(expectedFoodSystems);

        mockMvc.perform(MockMvcRequestBuilders.get("/next").session(session))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("tourPackageForOrder"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("selectsParameters"));

        Assert.assertNotNull(session.getAttribute("tourPackages"));
        Assert.assertNotNull(session.getAttribute("types"));
        Assert.assertNotNull(session.getAttribute("transports"));
        Assert.assertNotNull(session.getAttribute("foodSystemList"));
    }

    @Test
    public void shouldReturnGenericErrorViewAndModelWithExpectedAttributes_whenGetNullValuesFromFacadeRequest() throws Exception {
        List<TourPackage> expectedListTourPackages = Arrays.asList(new TourPackage());
        Optional<List<TypeTourPackage>> expectedTypes = Optional.ofNullable(null);
        Optional<List<Transport>> expectedTransports = Optional.ofNullable(null);
        Optional<List<FoodSystem>> expectedFoodSystems = Optional.ofNullable(null);

        MockHttpSession session = new MockHttpSession();
        Mockito.when(facadeTourPackageMock.getTourPackages())
                .thenReturn(expectedListTourPackages);
        Mockito.when(facadeTourPackageMock.getTypesOfTours())
                .thenReturn(expectedTypes);
        Mockito.when(facadeTourPackageMock.getTransportsOfTours())
                .thenReturn(expectedTransports);
        Mockito.when(facadeTourPackageMock.getFoodSystemsOfTours())
                .thenReturn(expectedFoodSystems);

        mockMvc.perform(MockMvcRequestBuilders.get("/").session(session))
                .andExpect(MockMvcResultMatchers.view().name("generic_error"));

    }

    @Test
    public void shouldReturnAuthorizationView_whenPassRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/authorization"))
                .andExpect(MockMvcResultMatchers.view().name("authorization"));
    }

    @Test
    public void shouldReturnIndexViewAndCallMethodOfFacade_whenPassRequest() throws Exception {
        List<TourPackage> expectedListTourPackages = Arrays.asList(new TourPackage());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackages",new PagedListHolder<>());

        Mockito.when(facadeTourPackageMock.getSelectedTourPackages(Mockito.any()))
                .thenReturn(expectedListTourPackages);
        Mockito.verify(facadeTourPackageMock).getSelectedTourPackages(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/select").session(session)
                .param("idOfFoodSystem", "1")
                .param("idOfTransport", "1")
                .param("idOfType", "1")
                .param("statusHot", "true")
                .param("minDay","")
                .param("maxDay", "")
                .param("minPrice", "")
                .param("maxPrice", ""))
                .andExpect(MockMvcResultMatchers.view().name("index"));

    }

    @Test
    public void shouldReturnIndexViewAndNoCallMethodOfFacade_whenPassRequest() throws Exception {
        List<TourPackage> expectedListTourPackages = Arrays.asList(new TourPackage());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackages",new PagedListHolder<>());

        Mockito.when(facadeTourPackageMock.getSelectedTourPackages(Mockito.any()))
                .thenReturn(expectedListTourPackages);
        Mockito.verify(facadeTourPackageMock, Mockito.never()).getSelectedTourPackages(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.get("/select").session(session)
                .param("idOfFoodSystem", "1")
                .param("idOfTransport", "1")
                .param("idOfType", "1")
                .param("statusHot", "false")
                .param("minDay","3")
                .param("maxDay", "1")
                .param("minPrice", "")
                .param("maxPrice", ""))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}
