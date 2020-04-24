package com.company.service.facade;

import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

public interface FacadeOrder {
    long makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost);
}
