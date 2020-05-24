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

/**
 * This class is unit test class for {@link OrderServiceImpl}.
 *
 * @author Igor Ivanov
 */
public class TestOrderServiceImpl
{
    /**
     * This field is order DAO mock.
     */
    private static OrderDAO orderDAOMock;

    /**
     * This field is order's service.
     */
    private static OrderService orderService;

    /**
     * This method executes before all methods, creates order DAO mock, order's service.
     */
    @BeforeClass
    public static void setUp()
    {
        orderDAOMock = Mockito.mock(OrderDAO.class);
        orderService = new OrderServiceImpl(orderDAOMock);
    }

    /**
     * This method executes before each method, resets order DAO mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(orderDAOMock);
    }

    /**
     * This method tests throwing exception when invoke orderService.makePayment with null parameters.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParametersToMakePayment() throws ServiceException
    {
        orderService.makePayment(null, null, null, null);
    }

    /**
     * This method tests execution methods when invoke orderService.makePayment.
     *
     * @throws ServiceException when service throws ServiceException.
     */
    @Test
    public void shouldCallMethodsOfUserAndOrderDAO_whenPassParametersToMakePayment() throws ServiceException
    {
        Order orderMock = Mockito.mock(Order.class);
        orderService.makePayment(orderMock, new TourPackage(), new User(), new StatusOrder());
        Mockito.verify(orderMock).setTourPackage(Mockito.any());
        Mockito.verify(orderMock).setUser(Mockito.any());
        Mockito.verify(orderMock).setStatus(Mockito.any());
        Mockito.verify(orderMock).setCreateDate(Mockito.any());
        Mockito.verify(orderDAOMock).makePersistent(orderMock);
    }
}
