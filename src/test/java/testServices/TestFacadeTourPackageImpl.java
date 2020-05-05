package testServices;

import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.service.facade.FacadeTourPackage;
import com.company.model.service.facade.FacadeTourPackageImpl;
import com.company.model.service.tourPackage.TourPackageService;
import com.company.model.service.tourPackage.foodSystem.FoodSystemService;
import com.company.model.service.tourPackage.transport.TransportService;
import com.company.model.service.tourPackage.typeTourPackage.TypeTourPackageService;
import com.company.model.service.user.UserService;
import com.company.utils.ModelTourPackage;
import com.company.utils.ParametersSelectedForTourPackages;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestFacadeTourPackageImpl {
    private static FoodSystemService foodSystemServiceMock;
    private static TransportService transportServiceMock;
    private static TypeTourPackageService typeTourPackageServiceMock;
    private static TourPackageService tourPackageServiceMock;
    private static UserService userServiceMock;
    private static FacadeTourPackage facadeTourPackage;

    @BeforeClass
    public static void setUp() {
        foodSystemServiceMock= Mockito.mock(FoodSystemService.class);
        userServiceMock=Mockito.mock(UserService.class);
        transportServiceMock = Mockito.mock(TransportService.class);
        tourPackageServiceMock = Mockito.mock(TourPackageService.class);
        typeTourPackageServiceMock = Mockito.mock(TypeTourPackageService.class);
        facadeTourPackage = new FacadeTourPackageImpl(foodSystemServiceMock, transportServiceMock, typeTourPackageServiceMock, tourPackageServiceMock,  userServiceMock);
    }

    @Before
    public void set(){
        Mockito.reset(foodSystemServiceMock);
        Mockito.reset(userServiceMock);
        Mockito.reset(transportServiceMock);
        Mockito.reset(typeTourPackageServiceMock);
        Mockito.reset(tourPackageServiceMock);
    }

    @Test
    public void shouldReturnOptionalOfListTypesOfTour_whenCallGetTypesOfTours(){
        List<TypeTourPackage> expectedList = new ArrayList<>();
        Mockito.when(typeTourPackageServiceMock.getAllTypes()).thenReturn(Optional.ofNullable(expectedList));
        Assert.assertEquals(Optional.ofNullable(expectedList), facadeTourPackage.getTypesOfTours());
    }

    @Test
    public void shouldReturnOptionalOfListTransports_whenCallGetAllTransports(){
        List<Transport> expectedList = new ArrayList<>();
        Mockito.when(transportServiceMock.getAllTransports()).thenReturn(Optional.ofNullable(expectedList));
        Assert.assertEquals(Optional.ofNullable(expectedList), facadeTourPackage.getTransportsOfTours());
    }

    @Test
    public void shouldReturnOptionalOfListFoodSystems_whenCallGetAllFoodSystems(){
        List<FoodSystem> expectedList = new ArrayList<>();
        Mockito.when(foodSystemServiceMock.getAllFoodSystems()).thenReturn(Optional.ofNullable(expectedList));
        Assert.assertEquals(Optional.ofNullable(expectedList), facadeTourPackage.getFoodSystemsOfTours());
    }

    @Test
    public void shouldReturnListTourPackages_whenCallGetAllFoodSystems(){
        List<TourPackage> expectedList = new ArrayList<>();
        Mockito.when(tourPackageServiceMock.getTourPackages()).thenReturn(expectedList);
        Assert.assertEquals(expectedList, facadeTourPackage.getTourPackages());
    }

    @Test
    public void shouldReturnListTourPackages_whenCallGetSelectedTourPackages() throws ServiceException {
        List<TourPackage> expectedList = new ArrayList<>();
        ParametersSelectedForTourPackages parametersSelectedForTourPackages =new ParametersSelectedForTourPackages();
        Mockito.when(tourPackageServiceMock.getSelectedTourPackages(parametersSelectedForTourPackages)).thenReturn(expectedList);
        Assert.assertEquals(expectedList, facadeTourPackage.getSelectedTourPackages(parametersSelectedForTourPackages));
    }

    @Test
    public void shouldReturnOptionalOfTourPackage_whenCallGetTourPackage() throws ServiceException {
        TourPackage expectedTour = new TourPackage();
        Mockito.when(tourPackageServiceMock.getTourPackage(Mockito.anyLong())).thenReturn(Optional.ofNullable(expectedTour));
        Assert.assertEquals(Optional.ofNullable(expectedTour), facadeTourPackage.getTourPackage(1L));
    }

    @Test
    public void shouldReturnOptionalOfTourPackage_whenCallCreateTourPackage() throws ServiceException {
        TourPackage expectedTour = new TourPackage();
        ModelTourPackage modelTourPackage=new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(tourPackageServiceMock.createTourPackage(Mockito.any())).thenReturn(Optional.ofNullable(expectedTour));
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.ofNullable(new Transport()));
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.ofNullable(new TypeTourPackage()));
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.ofNullable(new FoodSystem()));
        Assert.assertEquals(Optional.ofNullable(expectedTour), facadeTourPackage.createTourPackage(modelTourPackage));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenCallCreateTourPackageAndServicesReturnNull() throws ServiceException {
        TourPackage expectedTour = new TourPackage();
        ModelTourPackage modelTourPackage=new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(tourPackageServiceMock.createTourPackage(Mockito.any())).thenReturn(Optional.ofNullable(expectedTour));
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        Assert.assertEquals(Optional.ofNullable(expectedTour), facadeTourPackage.createTourPackage(modelTourPackage));
    }

    @Test
    public void shouldCallMethodUpdateTourPackageOfService_whenCallUpdateTourPackage() throws ServiceException {
        ModelTourPackage modelTourPackage=new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.ofNullable(new Transport()));
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.ofNullable(new TypeTourPackage()));
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.ofNullable(new FoodSystem()));
        facadeTourPackage.updateTourPackage(modelTourPackage);
        Mockito.verify(tourPackageServiceMock).updateTourPackage(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenCallUpdateTourPackageAndServicesReturnNull() throws ServiceException {
        ModelTourPackage modelTourPackage=new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.ofNullable(null));
        facadeTourPackage.createTourPackage(modelTourPackage);
    }

    @Test
    public void shouldCallMethodDeleteTourPackageOfService_whenCallDeleteTourPackage() throws ServiceException {
        facadeTourPackage.deleteTourPackage(1L);
        Mockito.verify(tourPackageServiceMock).deleteTourPackage(1L);
    }

    @Test
    public void shouldReturnExpectedValue_whenCallGetTotalPrice() throws ServiceException {
        Principal principal =Mockito.mock(Principal.class);
        Mockito.when(userServiceMock.getDiscount(principal)).thenReturn(10);
        Assert.assertEquals(90.0, facadeTourPackage.getTotalPrice(100, principal), 0.01);
    }
}
