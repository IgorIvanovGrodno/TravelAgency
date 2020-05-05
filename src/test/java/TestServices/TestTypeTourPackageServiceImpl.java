package TestServices;


import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.typeTourPackage.TypeTourPackageDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.service.tourPackage.typeTourPackage.TypeTourPackageService;
import com.company.model.service.tourPackage.typeTourPackage.TypeTourPackageServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestTypeTourPackageServiceImpl {
    private static TypeTourPackageDAO typeTourPackageDAOMock;
    private static TypeTourPackageService typeTourPackageService;

    @BeforeClass
    public static void setUp() {
        typeTourPackageDAOMock= Mockito.mock(TypeTourPackageDAO.class);
        typeTourPackageService = new TypeTourPackageServiceImpl(typeTourPackageDAOMock);
    }

    @Before
    public void set(){
        Mockito.reset(typeTourPackageDAOMock);
    }

    @Test
    public void shouldReturnExpectedOptionalOfListTypes_whenCallGetAllTypes(){
        List<TypeTourPackage> expectedList = new ArrayList<>();
        Mockito.when(typeTourPackageDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(Optional.ofNullable(expectedList), typeTourPackageService.getAllTypes());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetTypeTourPackageById() throws ServiceException {
        typeTourPackageService.getTypeTourPackageById(null);
    }

    @Test
    public void shouldReturnExpectedOptionalOfType_whenPassIdToGetTypeTourPackageById() throws ServiceException {
        TypeTourPackage typeTourPackage=new TypeTourPackage();
        Mockito.when(typeTourPackageDAOMock.findById(Mockito.anyLong())).thenReturn(typeTourPackage);
        Assert.assertEquals(Optional.ofNullable(typeTourPackage), typeTourPackageService.getTypeTourPackageById(1L));
    }

}
