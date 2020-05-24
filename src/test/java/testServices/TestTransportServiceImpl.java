package testServices;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.transport.TransportDAO;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.service.tourPackage.transport.TransportService;
import com.company.model.service.tourPackage.transport.TransportServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is unit test class for {@link TransportServiceImpl}.
 *
 * @author Igor Ivanov
 */
public class TestTransportServiceImpl
{
    /**
     * This field is transport DAO mock.
     */
    private static TransportDAO transportDAOMock;

    /**
     * This field is transport service.
     */
    private static TransportService transportService;

    /**
     * This method executes before all methods, creates transport DAO mock, transport service.
     */
    @BeforeClass
    public static void setUp()
    {
        transportDAOMock = Mockito.mock(TransportDAO.class);
        transportService = new TransportServiceImpl(transportDAOMock);
    }

    /**
     * This method executes before each method, resets transport DAO mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(transportDAOMock);
    }

    /**
     * This method tests returned result transportService.getAllTransports() method.
     */
    @Test
    public void shouldReturnExpectedOptionalOfListTransport_whenCallGetAllTransports()
    {
        List<Transport> expectedList = new ArrayList<>();
        Mockito.when(transportDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(Optional.of(expectedList), transportService.getAllTransports());
    }

    /**
     * This method tests throwing exception when invoke transportService.getTransportById with null parameter.
     *
     * @throws ServiceException when invoke transportService.getTransportById with null parameter.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetTransportById() throws ServiceException
    {
        transportService.getTransportById(null);
    }

    /**
     * This method tests returned result transportService.getTransportById(id) method.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldReturnExpectedOptionalOfTransport_whenPassIdToGetTransportById() throws ServiceException
    {
        Transport transport = new Transport();
        Mockito.when(transportDAOMock.findById(Mockito.anyLong())).thenReturn(transport);
        Assert.assertEquals(Optional.of(transport), transportService.getTransportById(1L));
    }
}
