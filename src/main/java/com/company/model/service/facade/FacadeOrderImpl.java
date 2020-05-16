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

/**
 * This class is concrete implementation facade which provides business logic's methods for working with orders.
 *
 * @author Igor Ivanov
 */
@Component
public class FacadeOrderImpl implements FacadeOrder
{
    /**
     * This field is service which provides business logic's methods for working with orders.
     */
    private OrderService orderService;
    /**
     * This field is service which provides business logic's methods for working with order's statuses.
     */
    private StatusOrderService statusOrderService;
    /**
     * This field is service which provides business logic's methods for working with users.
     */
    private UserService userService;

    /**
     * Constructor which receive and assign order's service, status order service, user's service.
     *
     * @param orderService       - service which provides business logic's methods for working with orders.
     * @param statusOrderService - service which provides business logic's methods for working with order's statuses.
     * @param userService        - service which provides business logic's methods for working with users.
     */
    @Autowired
    public FacadeOrderImpl(OrderService orderService, StatusOrderService statusOrderService, UserService userService)
    {
        this.orderService = orderService;
        this.statusOrderService = statusOrderService;
        this.userService = userService;
    }

    /**
     * This method receive order, tour package and user's login. It make payment with received parameters.
     * It throw ServiceException if received parameters are incorrect.
     *
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param loginUser        - user's login.
     * @throws ServiceException
     */
    @Override
    public void makePayment(Order order, TourPackage tourPackageOrder, String loginUser) throws ServiceException
    {
        if (order == null || tourPackageOrder == null || loginUser == null)
        {
            throw new ServiceException("Incorrect input data for payment");
        }
        StatusOrder statusOrder = statusOrderService.getStatusForNewOrder().orElseThrow(() -> new ServiceException("Not found status order"));
        User user = userService.getUserByLogin(loginUser).orElseThrow(() -> new ServiceException("Not found user"));
        orderService.makePayment(order, tourPackageOrder, user, statusOrder);
    }
}
