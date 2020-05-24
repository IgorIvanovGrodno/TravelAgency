package testServices;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.model.service.facade.FacadeOrder;
import com.company.model.service.facade.FacadeOrderImpl;
import com.company.model.service.order.OrderService;
import com.company.model.service.order.statusOrder.StatusOrderService;
import com.company.model.service.user.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

/**
 * This class is unit test class for {@link FacadeOrderImpl}.
 *
 * @author Igor Ivanov
 */
public class TestFacadeOrderImpl
{
    /**
     * This field is order's service mock.
     */
    private static OrderService orderServiceMock;

    /**
     * This field is status order service mock.
     */
    private static StatusOrderService statusOrderServiceMock;

    /**
     * This field is user's service mock.
     */
    private static UserService userServiceMock;

    /**
     * This field is order's facade.
     */
    private static FacadeOrder facadeOrder;

    /**
     * This method executes before all methods, creates status order service mock, user's service mock, order's service
     * mock, order's facade.
     */
    @BeforeClass
    public static void setUp()
    {
        statusOrderServiceMock = Mockito.mock(StatusOrderService.class);
        userServiceMock = Mockito.mock(UserService.class);
        orderServiceMock = Mockito.mock(OrderService.class);
        facadeOrder = new FacadeOrderImpl(orderServiceMock, statusOrderServiceMock, userServiceMock);
    }

    /**
     * This method executes before each method, resets status order service mock, user's service mock, order's service
     * mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(statusOrderServiceMock);
        Mockito.reset(userServiceMock);
        Mockito.reset(orderServiceMock);
    }

    /**
     * This test method tests throwing exception when invoke facadeOrder.makePayment(Order order, TourPackage tourPackageOrder, String loginUser)
     * with null parameters.
     *
     * @throws ServiceException when invoke facadeOrder.makePayment(Order order, TourPackage tourPackageOrder, String loginUser)
     *                          with null parameters.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParametersToMakePayment() throws ServiceException
    {
        facadeOrder.makePayment(null, null, null);
    }

    /**
     * This test method tests invoking method orderService.makePayment(Order order, TourPackage tourPackage, User user, StatusOrder statusOrder)
     * when invoke facadeOrder.makePayment(Order order, TourPackage tourPackageOrder, String loginUser)
     *
     * @throws ServiceException then service throw ServiceException.
     */
    @Test
    public void shouldCallMethodsOfOrderServiceMock_whenPassParametersToMakePayment() throws ServiceException
    {
        User user = new User();
        StatusOrder statusOrder = new StatusOrder();
        Order order = new Order();
        TourPackage tourPackage = new TourPackage();
        Mockito.when(statusOrderServiceMock.getStatusForNewOrder()).thenReturn(Optional.of(statusOrder));
        Mockito.when(userServiceMock.getUserByLogin(Mockito.anyString())).thenReturn(Optional.of(user));
        facadeOrder.makePayment(order, tourPackage, "login");
        Mockito.verify(orderServiceMock).makePayment(order, tourPackage, user, statusOrder);
    }

    /**
     * This test method tests throwing exception when status order service return null.
     *
     * @throws ServiceException when status order service return null.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenStatusOrderServiceReturnNull() throws ServiceException
    {
        User user = new User();
        StatusOrder statusOrder = new StatusOrder();
        Order order = new Order();
        TourPackage tourPackage = new TourPackage();
        Mockito.when(statusOrderServiceMock.getStatusForNewOrder()).thenReturn(Optional.empty());
        Mockito.when(userServiceMock.getUserByLogin(Mockito.anyString())).thenReturn(Optional.of(user));
        facadeOrder.makePayment(order, tourPackage, "login");
        Mockito.verify(orderServiceMock).makePayment(order, tourPackage, user, statusOrder);
    }

    /**
     * This test method tests throwing exception when user service return null.
     *
     * @throws ServiceException when user service return null.
     */
    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenUserServiceReturnNull() throws ServiceException
    {
        User user = new User();
        StatusOrder statusOrder = new StatusOrder();
        Order order = new Order();
        TourPackage tourPackage = new TourPackage();
        Mockito.when(statusOrderServiceMock.getStatusForNewOrder()).thenReturn(Optional.of(statusOrder));
        Mockito.when(userServiceMock.getUserByLogin(Mockito.anyString())).thenReturn(Optional.empty());
        facadeOrder.makePayment(order, tourPackage, "login");
        Mockito.verify(orderServiceMock).makePayment(order, tourPackage, user, statusOrder);
    }

}
