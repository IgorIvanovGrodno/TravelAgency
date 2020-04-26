package com.company.model.service.facade;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.model.service.order.OrderService;
import com.company.model.service.order.statusOrder.StatusOrderService;
import org.springframework.stereotype.Component;

@Component
public class FacadeOrderImpl implements FacadeOrder {
    private OrderService orderService;
    private StatusOrderService statusOrderService;

    public FacadeOrderImpl(OrderService orderService, StatusOrderService statusOrderService) {
        this.orderService = orderService;
        this.statusOrderService = statusOrderService;
    }

    @Override
    public void makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost) throws ServiceException {
        if(order==null||tourPackageOrder==null||currentUser==null) throw new ServiceException("Incorrect input data for payment");
        StatusOrder statusOrder = statusOrderService.getStatusForNewOrder();
        orderService.makePayment(order, tourPackageOrder, currentUser, totalCost, statusOrder);
    }
}
