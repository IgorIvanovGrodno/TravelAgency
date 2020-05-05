package TestServices;

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

public class TestFoodSystemServiceImpl {
    private static FoodSystemDAO foodSystemDAOMock;
    private static FoodSystemService typeTourPackageService;

    @BeforeClass
    public static void setUp() {
        foodSystemDAOMock = Mockito.mock(FoodSystemDAO.class);
        typeTourPackageService = new FoodSystemServiceImpl(foodSystemDAOMock);
    }

    @Before
    public void set(){
        Mockito.reset(foodSystemDAOMock);
    }

    @Test
    public void shouldReturnExpectedOptionalOfListFoodSystems_whenCallGetAllFoodSystems(){
        List<FoodSystem> expectedList = new ArrayList<>();
        Mockito.when(foodSystemDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(Optional.ofNullable(expectedList), typeTourPackageService.getAllFoodSystems());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetFoodSystemById() throws ServiceException {
        typeTourPackageService.getFoodSystemById(null);
    }

    @Test
    public void shouldReturnExpectedOptionalOfFoodSystem_whenPassIdToGetFoodSystemById() throws ServiceException {
        FoodSystem foodSystem=new FoodSystem();
        Mockito.when(foodSystemDAOMock.findById(Mockito.anyLong())).thenReturn(foodSystem);
        Assert.assertEquals(Optional.ofNullable(foodSystem), typeTourPackageService.getFoodSystemById(1L));
    }

}
