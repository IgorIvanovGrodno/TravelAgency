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

public class TestFacadeOrderImpl {
    private static OrderService orderServiceMock;
    private static StatusOrderService statusOrderServiceMock;
    private static UserService userServiceMock;
    private static FacadeOrder facadeOrder;

    @BeforeClass
    public static void setUp() {
        statusOrderServiceMock=Mockito.mock(StatusOrderService.class);
        userServiceMock=Mockito.mock(UserService.class);
        orderServiceMock = Mockito.mock(OrderService.class);
        facadeOrder = new FacadeOrderImpl(orderServiceMock, statusOrderServiceMock, userServiceMock);
    }

    @Before
    public void set(){
        Mockito.reset(statusOrderServiceMock);
        Mockito.reset(userServiceMock);
        Mockito.reset(orderServiceMock);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenPassNullParametersToMakePayment() throws ServiceException {
        facadeOrder.makePayment(null, null, null);
    }

    @Test
    public void shouldCallMethodsOfOrderServiceMock_whenPassParametersToMakePayment() throws ServiceException {
        User user = new User();
        StatusOrder statusOrder = new StatusOrder();
        Order order=new Order();
        TourPackage tourPackage=new TourPackage();
        Mockito.when(statusOrderServiceMock.getStatusForNewOrder()).thenReturn(Optional.ofNullable(statusOrder));
        Mockito.when(userServiceMock.getUserByLogin(Mockito.anyString())).thenReturn(Optional.ofNullable(user));
        facadeOrder.makePayment(order, tourPackage, "login");
        Mockito.verify(orderServiceMock).makePayment(order, tourPackage, user, statusOrder);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenStatusOrderServiceReturnNull() throws ServiceException {
        User user = new User();
        StatusOrder statusOrder = new StatusOrder();
        Order order=new Order();
        TourPackage tourPackage=new TourPackage();
        Mockito.when(statusOrderServiceMock.getStatusForNewOrder()).thenReturn(Optional.ofNullable(null));
        Mockito.when(userServiceMock.getUserByLogin(Mockito.anyString())).thenReturn(Optional.ofNullable(user));
        facadeOrder.makePayment(order, tourPackage, "login");
        Mockito.verify(orderServiceMock).makePayment(order, tourPackage, user, statusOrder);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceException_whenUserServiceReturnNull() throws ServiceException {
        User user = new User();
        StatusOrder statusOrder = new StatusOrder();
        Order order=new Order();
        TourPackage tourPackage=new TourPackage();
        Mockito.when(statusOrderServiceMock.getStatusForNewOrder()).thenReturn(Optional.ofNullable(statusOrder));
        Mockito.when(userServiceMock.getUserByLogin(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        facadeOrder.makePayment(order, tourPackage, "login");
        Mockito.verify(orderServiceMock).makePayment(order, tourPackage, user, statusOrder);
    }

}
