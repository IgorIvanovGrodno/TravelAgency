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

/**
 * This class is unit test class for {@link FacadeTourPackageImpl}.
 *
 * @author Igor Ivanov
 */
public class TestFacadeTourPackageImpl
{
    /**
     * This field is food system's service mock.
     */
    private static FoodSystemService foodSystemServiceMock;

    /**
     * This field is transport's service mock.
     */
    private static TransportService transportServiceMock;

    /**
     * This field is type tour package's service mock.
     */
    private static TypeTourPackageService typeTourPackageServiceMock;

    /**
     * This field is tour package's service mock.
     */
    private static TourPackageService tourPackageServiceMock;

    /**
     * This field is user's service mock.
     */
    private static UserService userServiceMock;

    /**
     * This field is facade tour package.
     */
    private static FacadeTourPackage facadeTourPackage;

    /**
     * This method executes before all methods, creates food system's service mock, transport's service mock, type tour package's
     * service mock, tour package's service mock, user's service mock, facade tour package.
     */
    @BeforeClass
    public static void setUp()
    {
        foodSystemServiceMock = Mockito.mock(FoodSystemService.class);
        userServiceMock = Mockito.mock(UserService.class);
        transportServiceMock = Mockito.mock(TransportService.class);
        tourPackageServiceMock = Mockito.mock(TourPackageService.class);
        typeTourPackageServiceMock = Mockito.mock(TypeTourPackageService.class);
        facadeTourPackage = new FacadeTourPackageImpl(foodSystemServiceMock, transportServiceMock, typeTourPackageServiceMock, tourPackageServiceMock, userServiceMock);
    }

    /**
     * This method executes before each method, resets food system's service mock, transport's service mock, type tour package's
     * service mock, tour package's service mock, user's service mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(foodSystemServiceMock);
        Mockito.reset(userServiceMock);
        Mockito.reset(transportServiceMock);
        Mockito.reset(typeTourPackageServiceMock);
        Mockito.reset(tourPackageServiceMock);
    }

    /**
     * This method tests returned result typeTourPackageServiceMock.getAllTypes() method.
     */
    @Test
    public void shouldReturnOptionalOfListTypesOfTour_whenCallGetTypesOfTours()
    {
        List<TypeTourPackage> expectedList = new ArrayList<>();
        Mockito.when(typeTourPackageServiceMock.getAllTypes()).thenReturn(Optional.of(expectedList));
        Assert.assertEquals(Optional.of(expectedList), facadeTourPackage.getTypesOfTours());
    }

    /**
     * This method tests returned result transportServiceMock.getAllTransports() method.
     */
    @Test
    public void shouldReturnOptionalOfListTransports_whenCallGetAllTransports()
    {
        List<Transport> expectedList = new ArrayList<>();
        Mockito.when(transportServiceMock.getAllTransports()).thenReturn(Optional.of(expectedList));
        Assert.assertEquals(Optional.of(expectedList), facadeTourPackage.getTransportsOfTours());
    }

    /**
     * This method tests returned result foodSystemServiceMock.getAllFoodSystems() method.
     */
    @Test
    public void shouldReturnOptionalOfListFoodSystems_whenCallGetAllFoodSystems()
    {
        List<FoodSystem> expectedList = new ArrayList<>();
        Mockito.when(foodSystemServiceMock.getAllFoodSystems()).thenReturn(Optional.of(expectedList));
        Assert.assertEquals(Optional.of(expectedList), facadeTourPackage.getFoodSystemsOfTours());
    }

    /**
     * This method tests returned result tourPackageServiceMock.getTourPackages() method.
     */
    @Test
    public void shouldReturnListTourPackages_whenCallGetAllFoodSystems()
    {
        List<TourPackage> expectedList = new ArrayList<>();
        Mockito.when(tourPackageServiceMock.getTourPackages()).thenReturn(expectedList);
        Assert.assertEquals(expectedList, facadeTourPackage.getTourPackages());
    }

    /**
     * This method tests returned result  facadeTourPackage.getSelectedTourPackages(parametersSelectedForTourPackages) method.
     *
     * @throws ServiceException when facade throws ServiceException.
     */
    @Test
    public void shouldReturnListTourPackages_whenCallGetSelectedTourPackages() throws ServiceException
    {
        List<TourPackage> expectedList = new ArrayList<>();
        ParametersSelectedForTourPackages parametersSelectedForTourPackages = new ParametersSelectedForTourPackages();
        Mockito.when(tourPackageServiceMock.getSelectedTourPackages(parametersSelectedForTourPackages)).thenReturn(expectedList);
        Assert.assertEquals(expectedList, facadeTourPackage.getSelectedTourPackages(parametersSelectedForTourPackages));
    }

    /**
     * This method tests returned result facadeTourPackage.getTourPackage(id) method.
     *
     * @throws ServiceException when facade throws ServiceException.
     */
    @Test
    public void shouldReturnOptionalOfTourPackage_whenCallGetTourPackage() throws ServiceException
    {
        TourPackage expectedTour = new TourPackage();
        Mockito.when(tourPackageServiceMock.getTourPackage(Mockito.anyLong())).thenReturn(Optional.of(expectedTour));
        Assert.assertEquals(Optional.of(expectedTour), facadeTourPackage.getTourPackage(1L));
    }

    /**
     * This method tests returned result facadeTourPackage.createTourPackage(modelTourPackage) method.
     *
     * @throws ServiceException when facade throws ServiceException.
     */
    @Test
    public void shouldReturnOptionalOfTourPackage_whenCallCreateTourPackage() throws ServiceException
    {
        TourPackage expectedTour = new TourPackage();
        ModelTourPackage modelTourPackage = new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(tourPackageServiceMock.createTourPackage(Mockito.any())).thenReturn(Optional.of(expectedTour));
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.of(new Transport()));
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.of(new TypeTourPackage()));
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.of(new FoodSystem()));
        Assert.assertEquals(Optional.of(expectedTour), facadeTourPackage.createTourPackage(modelTourPackage));
    }

    /**
     * This test method tests throwing exception when invoke facadeTourPackage.createTourPackage(modelTourPackage)
     * and services return null.
     *
     * @throws ServiceException when invoke facadeTourPackage.createTourPackage(modelTourPackage)
     *                          and services return null.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenCallCreateTourPackageAndServicesReturnNull() throws ServiceException
    {
        TourPackage expectedTour = new TourPackage();
        ModelTourPackage modelTourPackage = new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(tourPackageServiceMock.createTourPackage(Mockito.any())).thenReturn(Optional.of(expectedTour));
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.empty());
        Assert.assertEquals(Optional.of(expectedTour), facadeTourPackage.createTourPackage(modelTourPackage));
    }

    /**
     * This method tests invoking tourPackageServiceMock.updateTourPackage method when
     * invokes facadeTourPackage.updateTourPackage(modelTourPackage).
     *
     * @throws ServiceException when services throw ServiceException.
     */
    @Test
    public void shouldCallMethodUpdateTourPackageOfService_whenCallUpdateTourPackage() throws ServiceException
    {
        ModelTourPackage modelTourPackage = new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.of(new Transport()));
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.of(new TypeTourPackage()));
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.of(new FoodSystem()));
        facadeTourPackage.updateTourPackage(modelTourPackage);
        Mockito.verify(tourPackageServiceMock).updateTourPackage(Mockito.any());
    }

    /**
     * This test method tests throwing exception when invoke facadeTourPackage.createTourPackage(modelTourPackage)
     * and services return null.
     *
     * @throws ServiceException when invoke facadeTourPackage.createTourPackage(modelTourPackage)
     *                          and services return null.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenCallUpdateTourPackageAndServicesReturnNull() throws ServiceException
    {
        ModelTourPackage modelTourPackage = new ModelTourPackage();
        modelTourPackage.setDay("1");
        modelTourPackage.setDescription("description");
        modelTourPackage.setId(1L);
        modelTourPackage.setIdOfFoodSystem(1L);
        modelTourPackage.setIdOfTransport(1L);
        modelTourPackage.setIdOfType(1L);
        modelTourPackage.setPrice("1");
        modelTourPackage.setStatusHot(true);
        Mockito.when(transportServiceMock.getTransportById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(typeTourPackageServiceMock.getTypeTourPackageById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(foodSystemServiceMock.getFoodSystemById(Mockito.any())).thenReturn(Optional.empty());
        facadeTourPackage.createTourPackage(modelTourPackage);
    }

    /**
     * This method tests invoking tourPackageServiceMock.deleteTourPackage(id) when invoke facadeTourPackage.deleteTourPackage(id)
     *
     * @throws ServiceException when facade throws ServiceException.
     */
    @Test
    public void shouldCallMethodDeleteTourPackageOfService_whenCallDeleteTourPackage() throws ServiceException
    {
        facadeTourPackage.deleteTourPackage(1L);
        Mockito.verify(tourPackageServiceMock).deleteTourPackage(1L);
    }

    /**
     * This method tests returned result facadeTourPackage.getTotalPrice(price, principal) method.
     *
     * @throws ServiceException when facade throws ServiceException.
     */
    @Test
    public void shouldReturnExpectedValue_whenCallGetTotalPrice() throws ServiceException
    {
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(userServiceMock.getDiscount(principal)).thenReturn(10);
        Assert.assertEquals(90.0, facadeTourPackage.getTotalPrice(100, principal), 0.01);
    }
}
