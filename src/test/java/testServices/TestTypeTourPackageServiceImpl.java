package testServices;


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

/**
 * This class is unit test class for {@link TypeTourPackageServiceImpl}.
 *
 * @author Igor Ivanov
 */
public class TestTypeTourPackageServiceImpl
{
    /**
     * This field is type tour package DAO mock.
     */
    private static TypeTourPackageDAO typeTourPackageDAOMock;
    /**
     * This field is type tour package service.
     */
    private static TypeTourPackageService typeTourPackageService;

    /**
     * This method executes before all methods, creates type tour package DAO mock, type tour package service.
     */
    @BeforeClass
    public static void setUp()
    {
        typeTourPackageDAOMock = Mockito.mock(TypeTourPackageDAO.class);
        typeTourPackageService = new TypeTourPackageServiceImpl(typeTourPackageDAOMock);
    }

    /**
     * This method executes before each method, resets type tour package DAO mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(typeTourPackageDAOMock);
    }

    /**
     * This method tests returned result typeTourPackageService.getAllTypes() method.
     */
    @Test
    public void shouldReturnExpectedOptionalOfListTypes_whenCallGetAllTypes()
    {
        List<TypeTourPackage> expectedList = new ArrayList<>();
        Mockito.when(typeTourPackageDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(Optional.of(expectedList), typeTourPackageService.getAllTypes());
    }

    /**
     * This method tests throwing exception when invoke typeTourPackageService.getTypeTourPackageById with null parameter.
     *
     * @throws ServiceException when invoke typeTourPackageService.getTypeTourPackageById with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetTypeTourPackageById() throws ServiceException
    {
        typeTourPackageService.getTypeTourPackageById(null);
    }

    /**
     * This method tests returned result typeTourPackageService.getTypeTourPackageById method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnExpectedOptionalOfType_whenPassIdToGetTypeTourPackageById() throws ServiceException
    {
        TypeTourPackage typeTourPackage = new TypeTourPackage();
        Mockito.when(typeTourPackageDAOMock.findById(Mockito.anyLong())).thenReturn(typeTourPackage);
        Assert.assertEquals(Optional.of(typeTourPackage), typeTourPackageService.getTypeTourPackageById(1L));
    }
}
