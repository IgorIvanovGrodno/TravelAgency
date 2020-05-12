package testServices;

import com.company.exceptions.ServiceException;
import com.company.model.dao.order.OrderDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.model.service.order.OrderService;
import com.company.model.service.order.OrderServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class TestOrderServiceImpl {
    private static OrderDAO orderDAOMock;
    private static OrderService orderService;

    @BeforeClass
    public static void setUp() {
        orderDAOMock = Mockito.mock(OrderDAO.class);
        orderService = new OrderServiceImpl(orderDAOMock);
    }

    @Before
    public void set(){
        Mockito.reset(orderDAOMock);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParametersToMakePayment() throws ServiceException {
        orderService.makePayment(null, null, null, null);
    }

    @Test
    public void shouldCallMethodsOfUserAndOrderDAO_whenPassParametersToMakePayment() throws ServiceException {
        Order orderMock = Mockito.mock(Order.class);
        orderService.makePayment(orderMock, new TourPackage(), new User(), new StatusOrder());
        Mockito.verify(orderMock).setTourPackage(Mockito.any());
        Mockito.verify(orderMock).setUser(Mockito.any());
        Mockito.verify(orderMock).setStatus(Mockito.any());
        Mockito.verify(orderMock).setCreateDate(Mockito.any());
        Mockito.verify(orderDAOMock).makePersistent(orderMock);
    }
}
