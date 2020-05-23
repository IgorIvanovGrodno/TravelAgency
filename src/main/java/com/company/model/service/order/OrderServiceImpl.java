package com.company.model.service.order;

import com.company.exceptions.ServiceException;
import com.company.model.dao.order.OrderDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * This class is implementation for service which provides business logic's methods for working with orders.
 *
 * @author Igor Ivanov
 */
@Service
public class OrderServiceImpl implements OrderService
{
    /**
     * This field is order's data access object.
     */
    private OrderDAO orderDAO;

    /**
     * Constructor which receives and assign order's data access object.
     *
     * @param orderDAO - order's data access object.
     */
    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO)
    {
        this.orderDAO = orderDAO;
    }

    /**
     * This method receives order, tour package for order, current user, status of order. It makes payment with received parameters.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param currentUser      - current user.
     * @param statusOrder      - status of order.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public void makePayment(Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder) throws ServiceException
    {
        if (order == null
                || tourPackageOrder == null
                || currentUser == null
                || statusOrder == null)
        {
            throw new ServiceException("Incorrect input data for payment");
        }
        order.setTourPackage(tourPackageOrder);
        order.setUser(currentUser);
        order.setStatus(statusOrder);
        order.setCreateDate(new Date());
        orderDAO.makePersistent(order);
    }
}
