package com.company.model.service.facade;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;

public interface FacadeOrder {
    void makePayment(Order order, TourPackage tourPackageOrder, String loginUser, long totalCost) throws ServiceException;
}
