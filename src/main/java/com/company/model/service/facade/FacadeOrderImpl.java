package com.company.model.service.facade;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.model.service.order.OrderService;
import com.company.model.service.order.statusOrder.StatusOrderService;
import com.company.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacadeOrderImpl implements FacadeOrder {
    private OrderService orderService;
    private StatusOrderService statusOrderService;
    private UserService userService;

    @Autowired
    public FacadeOrderImpl(OrderService orderService, StatusOrderService statusOrderService, UserService userService) {
        this.orderService = orderService;
        this.statusOrderService = statusOrderService;
        this.userService = userService;
    }

    @Override
    public void makePayment(Order order, TourPackage tourPackageOrder, String loginUser, long totalCost) throws ServiceException {
        if(order==null||tourPackageOrder==null|| loginUser ==null) throw new ServiceException("Incorrect input data for payment");
        StatusOrder statusOrder = statusOrderService.getStatusForNewOrder();
        User user = userService.getUserByLogin(loginUser);
        orderService.makePayment(order, tourPackageOrder, user, totalCost, statusOrder);
    }
}
