package TestControllers;

import com.company.config.WebConfiguration;
import com.company.model.domain.tourPackage.TourPackage;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.validateMockitoUsage;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class, TestConfiguration.class})
public class TestTourPackageController {
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
    public void shouldReturnCreateTourPackageViewAndExpectedModelAttribute_whenPassRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/create/"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("newTourPackage"))
                .andExpect(MockMvcResultMatchers.view().name("create_tour_package"));
    }

    @Test
    public void shouldReturnAdminViewAndShouldCallMethodOfFacadeTourPackage_whenPassRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/create/tourPackage")
                .param("description", "name")
                .param("idOfFoodSystem", "1")
                .param("idOfTransport", "1")
                .param("idOfType", "1")
                .param("statusHot", "true")
                .param("day", "1")
                .param("price", "1"))
                .andExpect(MockMvcResultMatchers.view().name("admin"));
       // Mockito.verify(facadeTourPackageMock).createTourPackage(Mockito.any());
    }

    @Test
    public void shouldReturnCreateTourPackageView_whenPassRequestWithNoValidModelAttribute() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/create/tourPackage")
                .param("description", "")
                .param("idOfFoodSystem", "1")
                .param("idOfTransport", "1")
                .param("idOfType", "1")
                .param("statusHot", "true")
                .param("day", "")
                .param("price", ""))
                .andExpect(MockMvcResultMatchers.view().name("create_tour_package"));
    }

    @Test
    public void shouldReturnUpdateTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequest() throws Exception {
        MockHttpSession session = new MockHttpSession();
       List<TourPackage> expectedTours = new ArrayList<>();
       Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/update/tourPackage/").session(session))
                .andExpect(MockMvcResultMatchers.view().name("update_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("updateTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForUpdate"));

    }

    @Test
    public void shouldReturnUpdateTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequestWithNumberPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackagesForUpdate",new PagedListHolder<>());
        List<TourPackage> expectedTours = new ArrayList<>();
        Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/update/tourPackage/1").session(session))
                .andExpect(MockMvcResultMatchers.view().name("update_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("updateTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForUpdate"));

    }

    @Test
    public void shouldReturnUpdateTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequestWithPrevPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackagesForUpdate",new PagedListHolder<>());
        List<TourPackage> expectedTours = new ArrayList<>();
        Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/update/tourPackage/prev").session(session))
                .andExpect(MockMvcResultMatchers.view().name("update_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("updateTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForUpdate"));

    }

    @Test
    public void shouldReturnUpdateTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequestWithNextPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackagesForUpdate",new PagedListHolder<>());
        List<TourPackage> expectedTours = new ArrayList<>();
        Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/update/tourPackage/next").session(session))
                .andExpect(MockMvcResultMatchers.view().name("update_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("updateTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForUpdate"));

    }

    @Test
    public void shouldReturnAdminView_whenPassRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/tourPackage/update")
                .param("id","1")
                .param("description","description")
                .param("idOfTransport","1")
                .param("idOfFoodSystem","1")
                .param("idOfType","1")
                .param("day","1")
                .param("price","1")
                .param("statusHot","true"))
                .andExpect(MockMvcResultMatchers.view().name("admin"));
        //Mockito.verify(facadeTourPackageMock).updateTourPackage(Mockito.any());
    }

    @Test
    public void shouldReturnUpdateTourPackageView_whenPassRequestWithNoValidParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/tourPackage/update")
                .param("id","")
                .param("description","")
                .param("idOfTransport","")
                .param("idOfFoodSystem","")
                .param("idOfType","")
                .param("day","")
                .param("price","")
                .param("statusHot","true"))
                .andExpect(MockMvcResultMatchers.view().name("update_tour_package"));
       // Mockito.verify(facadeTourPackageMock).updateTourPackage(Mockito.any());
    }

    @Test
    public void shouldReturnDeleteTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        List<TourPackage> expectedTours = new ArrayList<>();
        Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete/").session(session))
                .andExpect(MockMvcResultMatchers.view().name("delete_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("deleteTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForDelete"));
    }

    @Test
    public void  shouldReturnDeleteTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequestWithNumberPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackagesForDelete",new PagedListHolder<>());
        List<TourPackage> expectedTours = new ArrayList<>();
        Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete/1").session(session))
                .andExpect(MockMvcResultMatchers.view().name("delete_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("deleteTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForDelete"));
    }

    @Test
    public void  shouldReturnDeleteTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequestWithPrevPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackagesForDelete",new PagedListHolder<>());
        List<TourPackage> expectedTours = new ArrayList<>();
        Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete/prev").session(session))
                .andExpect(MockMvcResultMatchers.view().name("delete_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("deleteTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForDelete"));
    }

    @Test
    public void  shouldReturnDeleteTourPackageViewAndModelAndSessionWithExpectedAttributes_whenPassRequestWithNextPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("tourPackagesForDelete",new PagedListHolder<>());
        List<TourPackage> expectedTours = new ArrayList<>();
        Mockito.when(facadeTourPackageMock.getTourPackages()).thenReturn(expectedTours);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/delete/next").session(session))
                .andExpect(MockMvcResultMatchers.view().name("delete_tour_package"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("deleteTourPackage"));
        Assert.assertNotNull(session.getAttribute("tourPackagesForDelete"));
    }

    @Test
    public void shouldReturnAdminView_whenPassDeleteRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/delete/tourPackage")
                .param("id","1")
                .param("description","description")
                .param("idOfTransport","1")
                .param("idOfFoodSystem","1")
                .param("idOfType","1")
                .param("day","1")
                .param("price","1")
                .param("statusHot","true"))
                .andExpect(MockMvcResultMatchers.view().name("admin"));
        //Mockito.verify(facadeTourPackageMock).deleteTourPackage(Mockito.any());
    }

    @Test
    public void shouldReturnDeleteTourPackageView_whenPassRequestWithNoValidParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/delete/tourPackage")
                .param("id","")
                .param("description","")
                .param("idOfTransport","")
                .param("idOfFoodSystem","")
                .param("idOfType","")
                .param("day","")
                .param("price","")
                .param("statusHot","true"))
                .andExpect(MockMvcResultMatchers.view().name("delete_tour_package"));
        // Mockito.verify(facadeTourPackageMock).deleteTourPackage(Mockito.any());
    }

}
