package com.company.model.service.order;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

/**
 * This class is interface for service which provides business logic's methods for working with orders.
 *
 * @author Igor Ivanov
 */
public interface OrderService
{
    /**
     * This method receives order, tour package for order, current user, status of order. It makes payment with received parameters.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param currentUser      - current user.
     * @param statusOrder      - status of order.
     * @throws ServiceException - exception when input parameters are incorrect.
     */
    void makePayment(Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder) throws ServiceException;
}
