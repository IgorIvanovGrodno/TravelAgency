package com.company.model.service.facade;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

public interface FacadeOrder {
    void makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost) throws ServiceException;
}
