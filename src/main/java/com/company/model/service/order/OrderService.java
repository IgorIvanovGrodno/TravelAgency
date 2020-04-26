package com.company.model.service.order;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

import java.util.Optional;

public interface OrderService {

    Optional<Order> makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost, StatusOrder statusOrder) throws ServiceException;
}
