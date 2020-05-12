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

public class TestOrderStatusImpl {
    private static StatusOrderDAO statusOrderDAOMock;
    private static StatusOrderService statusOrderService;

    @BeforeClass
    public static void setUp() {
        statusOrderDAOMock = Mockito.mock(StatusOrderDAO.class);
        statusOrderService = new StatusOrderServiceImpl(statusOrderDAOMock);
    }

    @Before
    public void set(){
        Mockito.reset(statusOrderDAOMock);
    }

    @Test
    public void shouldReturnExpectedOptionalOfStatusOrder_whenCallGetStatusForNewOrder(){
        StatusOrder statusOrder = new StatusOrder();
        Mockito.when(statusOrderDAOMock.findById(1L)).thenReturn(statusOrder);
        Assert.assertEquals(Optional.ofNullable(statusOrder), statusOrderService.getStatusForNewOrder());
        Mockito.verify(statusOrderDAOMock).findById(1L);
    }
}
