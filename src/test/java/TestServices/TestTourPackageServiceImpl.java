package TestServices;

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

public class TestTourPackageServiceImpl {
    private static TourPackageDAO tourPackageDAOMock;
    private static TourPackageService tourPackageService;

    @BeforeClass
    public static void setUp() {
        tourPackageDAOMock = Mockito.mock(TourPackageDAO.class);
        tourPackageService = new TourPackageServiceImpl(tourPackageDAOMock);
    }

    @Before
    public void set(){
        Mockito.reset(tourPackageDAOMock);
    }

    @Test
    public void shouldReturnExpectedListTours_whenCallGetTourPackages(){
        List<TourPackage> expectedList =new ArrayList<>();
        Mockito.when(tourPackageDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(expectedList, tourPackageService.getTourPackages());
    }

    @Test
    public void shouldReturnEmptyListTours_whenCallGetTourPackages(){
        Mockito.when(tourPackageDAOMock.findAll()).thenReturn(null);
        Assert.assertEquals(0, tourPackageService.getTourPackages().size());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetSelectedTourPackages() throws ServiceException {
        tourPackageService.getSelectedTourPackages(null);
    }

    @Test
    public void shouldReturnExpectedListTours_whenCallGetSelectedTourPackages() throws ServiceException {
        ParametersSelectedForTourPackages parametersSelectedForTourPackages = new ParametersSelectedForTourPackages();
        List<TourPackage> expectedList = new ArrayList<>();
        Mockito.when(tourPackageDAOMock.getSelectedTourPackages(parametersSelectedForTourPackages)).thenReturn(expectedList);
        Assert.assertEquals(expectedList, tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages));
    }

    @Test
    public void shouldReturnEmptyListTours_whenCallGetSelectedTourPackages() throws ServiceException {
        ParametersSelectedForTourPackages parametersSelectedForTourPackages = new ParametersSelectedForTourPackages();
        Mockito.when(tourPackageDAOMock.getSelectedTourPackages(parametersSelectedForTourPackages)).thenReturn(null);
        Assert.assertEquals(0, tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages).size());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetTourPackage() throws ServiceException {
        tourPackageService.getTourPackage(null);
    }

    @Test
    public void shouldReturnExpectedOptionalOfTour_whenPassIdToGetTourPackage() throws ServiceException {
        TourPackage expectedTour=new TourPackage();
        Mockito.when(tourPackageDAOMock.findById(1L)).thenReturn(expectedTour);
        Assert.assertEquals(Optional.of(expectedTour), tourPackageService.getTourPackage(1L));
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToCreateTourPackage() throws ServiceException {
        tourPackageService.createTourPackage(null);
    }

    @Test
    public void shouldCallMakePersistentOfDAOAndReturnExpectedOptionalOfTour_whenPassParameterToCreateTourPackage() throws ServiceException {
        TourPackage expectedTour=new TourPackage();
        Mockito.when(tourPackageDAOMock.makePersistent(expectedTour)).thenReturn(expectedTour);
        Assert.assertEquals(Optional.of(expectedTour), tourPackageService.createTourPackage(expectedTour));
        Mockito.verify(tourPackageDAOMock).makePersistent(expectedTour);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToUpdateTourPackage() throws ServiceException {
        tourPackageService.updateTourPackage(null);
    }

    @Test
    public void shouldCallMakePersistentOfDAO_whenPassParameterToUpdateTourPackage() throws ServiceException {
        TourPackage expectedTour=new TourPackage();
        Mockito.when(tourPackageDAOMock.makePersistent(expectedTour)).thenReturn(expectedTour);
        tourPackageService.updateTourPackage(expectedTour);
        Mockito.verify(tourPackageDAOMock).makePersistent(expectedTour);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToDeleteTourPackage() throws ServiceException {
        tourPackageService.deleteTourPackage(null);
    }

    @Test
    public void shouldCallMakePersistentOfDAO_whenPassParameterToDeleteTourPackage() throws ServiceException {
        tourPackageService.deleteTourPackage(1L);
        Mockito.verify(tourPackageDAOMock).deleteById(1L);
    }
}
