package com.company.service.order;

import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

public interface OrderService {
    long makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost);
}
