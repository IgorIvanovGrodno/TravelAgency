package com.company.model.service.facade;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;

/**
 * This class is interface for facade which provides business logic's methods for working with orders.
 *
 * @author Igor Ivanov
 */
public interface FacadeOrder
{
    /**
     * This method receives order, tour package and user's login. It makes payment with received parameters.
     * It throws ServiceException when received parameters are incorrect.
     *
     * @param order            - order.
     * @param tourPackageOrder - tour package for order.
     * @param loginUser        - user's login.
     * @throws ServiceException
     */
    void makePayment(Order order, TourPackage tourPackageOrder, String loginUser) throws ServiceException;
}
