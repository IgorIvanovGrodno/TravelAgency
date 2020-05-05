package TestServices;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.transport.TransportDAO;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.service.tourPackage.transport.TransportService;
import com.company.model.service.tourPackage.transport.TransportServiceImpl;
import com.company.model.service.tourPackage.typeTourPackage.TypeTourPackageServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestTransportServiceImpl {
    private static TransportDAO transportDAOMock;
    private static TransportService transportService;

    @BeforeClass
    public static void setUp() {
        transportDAOMock = Mockito.mock(TransportDAO.class);
        transportService = new TransportServiceImpl(transportDAOMock);
    }

    @Before
    public void set(){
        Mockito.reset(transportDAOMock);
    }

    @Test
    public void shouldReturnExpectedOptionalOfListTransport_whenCallGetAllTransports(){
        List<Transport> expectedList = new ArrayList<>();
        Mockito.when(transportDAOMock.findAll()).thenReturn(expectedList);
        Assert.assertEquals(Optional.ofNullable(expectedList), transportService.getAllTransports());
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParameterToGetTransportById() throws ServiceException {
        transportService.getTransportById(null);
    }

    @Test
    public void shouldReturnExpectedOptionalOfTransport_whenPassIdToGetTransportById() throws ServiceException {
        Transport transport=new Transport();
        Mockito.when(transportDAOMock.findById(Mockito.anyLong())).thenReturn(transport);
        Assert.assertEquals(Optional.ofNullable(transport), transportService.getTransportById(1L));
    }

}
