package testServices;

import com.company.model.dao.order.statusOrder.StatusOrderDAO;
import com.company.model.domain.order.StatusOrder;
import com.company.model.service.order.statusOrder.StatusOrderService;
import com.company.model.service.order.statusOrder.StatusOrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

/**
 * This class is unit test class for {@link StatusOrderServiceImpl}.
 *
 * @author Igor Ivanov
 */
public class TestStatusOrderImpl
{
    /**
     * This field is status order DAO mock.
     */
    private static StatusOrderDAO statusOrderDAOMock;

    /**
     * This field is status order's service.
     */
    private static StatusOrderService statusOrderService;

    /**
     * This method executes before all methods, creates status order DAO mock, status order's service.
     */
    @BeforeClass
    public static void setUp()
    {
        statusOrderDAOMock = Mockito.mock(StatusOrderDAO.class);
        statusOrderService = new StatusOrderServiceImpl(statusOrderDAOMock);
    }

    /**
     * This method executes before each method, resets status order DAO mock.
     */
    @Before
    public void set()
    {
        Mockito.reset(statusOrderDAOMock);
    }

    /**
     * This method tests returned result statusOrderService.getStatusForNewOrder() and tests execution
     * statusOrderDAOMock.findById(id) method when invoke orderService.makePayment.
     */
    @Test
    public void shouldReturnExpectedOptionalOfStatusOrder_whenCallGetStatusForNewOrder()
    {
        StatusOrder statusOrder = new StatusOrder();
        Mockito.when(statusOrderDAOMock.findById(1L)).thenReturn(statusOrder);
        Assert.assertEquals(Optional.of(statusOrder), statusOrderService.getStatusForNewOrder());
        Mockito.verify(statusOrderDAOMock).findById(1L);
    }
}
