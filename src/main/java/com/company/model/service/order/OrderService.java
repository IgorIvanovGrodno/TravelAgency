package com.company.model.service.order;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

public interface OrderService {

    void makePayment(Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder) throws ServiceException;
}
