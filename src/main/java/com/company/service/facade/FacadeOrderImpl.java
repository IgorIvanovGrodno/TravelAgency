package com.company.service.facade;

import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.service.order.OrderService;
import com.company.service.order.statusOrder.StatusOrderService;
import org.springframework.stereotype.Service;

@Service
public class FacadeOrderImpl implements FacadeOrder {
    private OrderService orderService;
    private StatusOrderService statusOrderService;

    public FacadeOrderImpl(OrderService orderService, StatusOrderService statusOrderService) {
        this.orderService = orderService;
        this.statusOrderService = statusOrderService;
    }

    @Override
    public long makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost) {
        StatusOrder statusOrder = statusOrderService.getStatusForNewOrder();
        return orderService.makePayment(order, tourPackageOrder, currentUser, totalCost,statusOrder).getId();
    }
}
