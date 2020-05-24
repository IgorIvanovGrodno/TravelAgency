package testServices;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.TourPackageDAO;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.service.tourPackage.TourPackageService;
import com.company.model.service.tourPackage.TourPackageServiceImpl;
import com.company.utils.ParametersSelectedForTourPackages;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is unit test class for {@link TourPackageServiceImpl}.
 *
 * @author Igor Ivanov
 */
public class TestTourPackageServiceImpl
{
    /**
     * This field is tour package DAO mock.
     */
    private static TourPackageDAO tourPackageDAOMock;

    /**
     * This field is tour package service.
     */
    private static TourPackageService tourPackageService;

    /**
     * This method executes before all methods, creates tour package DAO mock, tour package service.
     */
    @BeforeClass
    public static void setUp()
    {
        tourPackageDAOMock = Mockito.mock(TourPackageDAO.class);
        tourPackageService = new TourPackageServiceImpl(tourPackageDAOMock);
    }

    /**
     * This method executes before each method, resets tour package DAO mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(tourPackageDAOMock);
    }

    /**
     * This method tests returned result tourPackageService.getTourPackages() method.
     */
    @Test
    public void shouldReturnExpectedListTours_whenCallGetTourPackages()
    {
        List<TourPackage> expectedList = new ArrayList<>();
        Mockito.when(tourPackageDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(expectedList, tourPackageService.getTourPackages());
    }

    /**
     * This method tests returned result tourPackageService.getTourPackages() method when DAO return null.
     */
    @Test
    public void shouldReturnEmptyListTours_whenCallGetTourPackages()
    {
        Mockito.when(tourPackageDAOMock.findAll()).thenReturn(null);
        Assert.assertEquals(0, tourPackageService.getTourPackages().size());
    }

    /**
     * This method tests throwing exception when invoke tourPackageService.getSelectedTourPackages with null parameter.
     *
     * @throws ServiceException when invoke tourPackageService.getSelectedTourPackages with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetSelectedTourPackages() throws ServiceException
    {
        tourPackageService.getSelectedTourPackages(null);
    }

    /**
     * This method tests returned result tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages)
     * method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnExpectedListTours_whenCallGetSelectedTourPackages() throws ServiceException
    {
        ParametersSelectedForTourPackages parametersSelectedForTourPackages = new ParametersSelectedForTourPackages();
        List<TourPackage> expectedList = new ArrayList<>();
        Mockito.when(tourPackageDAOMock.getSelectedTourPackages(parametersSelectedForTourPackages)).thenReturn(expectedList);
        Assert.assertEquals(expectedList, tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages));
    }

    /**
     * This method tests returned result tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages)
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnEmptyListTours_whenCallGetSelectedTourPackages() throws ServiceException
    {
        ParametersSelectedForTourPackages parametersSelectedForTourPackages = new ParametersSelectedForTourPackages();
        Mockito.when(tourPackageDAOMock.getSelectedTourPackages(parametersSelectedForTourPackages)).thenReturn(null);
        Assert.assertEquals(0, tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages).size());
    }

    /**
     * This method tests throwing exception when invoke tourPackageService.getTourPackage with null parameter.
     *
     * @throws ServiceException when invoke tourPackageService.getTourPackage with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetTourPackage() throws ServiceException
    {
        tourPackageService.getTourPackage(null);
    }

    /**
     * This method tests returned result tourPackageService.getTourPackage(id).
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnExpectedOptionalOfTour_whenPassIdToGetTourPackage() throws ServiceException
    {
        TourPackage expectedTour = new TourPackage();
        Mockito.when(tourPackageDAOMock.findById(1L)).thenReturn(expectedTour);
        Assert.assertEquals(Optional.of(expectedTour), tourPackageService.getTourPackage(1L));
    }

    /**
     * This method tests throwing exception when invoke tourPackageService.createTourPackage with null parameter.
     *
     * @throws ServiceException when invoke tourPackageService.createTourPackage with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToCreateTourPackage() throws ServiceException
    {
        tourPackageService.createTourPackage(null);
    }

    /**
     * This method tests returned result tourPackageService.createTourPackage(expectedTour) and tests invoking
     * tourPackageDAOMock.makePersistent(expectedTour).
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldCallMakePersistentOfDAOAndReturnExpectedOptionalOfTour_whenPassParameterToCreateTourPackage() throws ServiceException
    {
        TourPackage expectedTour = new TourPackage();
        Mockito.when(tourPackageDAOMock.makePersistent(expectedTour)).thenReturn(expectedTour);
        Assert.assertEquals(Optional.of(expectedTour), tourPackageService.createTourPackage(expectedTour));
        Mockito.verify(tourPackageDAOMock).makePersistent(expectedTour);
    }

    /**
     * This method tests throwing exception when invoke tourPackageService.updateTourPackage with null parameter.
     *
     * @throws ServiceException when invoke tourPackageService.updateTourPackage with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToUpdateTourPackage() throws ServiceException
    {
        tourPackageService.updateTourPackage(null);
    }

    /**
     * This method tests invoking tourPackageDAOMock.makePersistent(expectedTour).
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldCallMakePersistentOfDAO_whenPassParameterToUpdateTourPackage() throws ServiceException
    {
        TourPackage expectedTour = new TourPackage();
        Mockito.when(tourPackageDAOMock.makePersistent(expectedTour)).thenReturn(expectedTour);
        tourPackageService.updateTourPackage(expectedTour);
        Mockito.verify(tourPackageDAOMock).makePersistent(expectedTour);
    }

    /**
     * This method tests throwing exception when invoke tourPackageService.deleteTourPackage with null parameter.
     *
     * @throws ServiceException when invoke tourPackageService.deleteTourPackage with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToDeleteTourPackage() throws ServiceException
    {
        tourPackageService.deleteTourPackage(null);
    }

    /**
     * This method tests invoking tourPackageDAOMock.deleteById(id).
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldCallMakePersistentOfDAO_whenPassParameterToDeleteTourPackage() throws ServiceException
    {
        tourPackageService.deleteTourPackage(1L);
        Mockito.verify(tourPackageDAOMock).deleteById(1L);
    }
}
