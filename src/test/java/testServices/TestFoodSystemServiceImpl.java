package testServices;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.foodSystem.FoodSystemDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.service.tourPackage.foodSystem.FoodSystemService;
import com.company.model.service.tourPackage.foodSystem.FoodSystemServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is unit test class for {@link FoodSystemServiceImpl}.
 *
 * @author Igor Ivanov
 */
public class TestFoodSystemServiceImpl
{
    /**
     * This field is food system DAO mock.
     */
    private static FoodSystemDAO foodSystemDAOMock;

    /**
     * This field is type tour package's service.
     */
    private static FoodSystemService typeTourPackageService;

    /**
     * This method executes before all methods, creates food system DAO mock, type tour package's service.
     */
    @BeforeClass
    public static void setUp()
    {
        foodSystemDAOMock = Mockito.mock(FoodSystemDAO.class);
        typeTourPackageService = new FoodSystemServiceImpl(foodSystemDAOMock);
    }

    /**
     * This method executes before each method, resets food system DAO mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(foodSystemDAOMock);
    }

    /**
     * This method tests returned result typeTourPackageService.getAllFoodSystems() method.
     */
    @Test
    public void shouldReturnExpectedOptionalOfListFoodSystems_whenCallGetAllFoodSystems()
    {
        List<FoodSystem> expectedList = new ArrayList<>();
        Mockito.when(foodSystemDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(Optional.of(expectedList), typeTourPackageService.getAllFoodSystems());
    }

    /**
     * This method tests throwing exception when invoke typeTourPackageService.getFoodSystemById with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetFoodSystemById() throws ServiceException
    {
        typeTourPackageService.getFoodSystemById(null);
    }

    /**
     * This method tests returned result typeTourPackageService.getFoodSystemById(id) method.
     *
     * @throws ServiceException when service throw ServiceException.
     */
    @Test
    public void shouldReturnExpectedOptionalOfFoodSystem_whenPassIdToGetFoodSystemById() throws ServiceException
    {
        FoodSystem foodSystem = new FoodSystem();
        Mockito.when(foodSystemDAOMock.findById(Mockito.anyLong())).thenReturn(foodSystem);
        Assert.assertEquals(Optional.of(foodSystem), typeTourPackageService.getFoodSystemById(1L));
    }
}
