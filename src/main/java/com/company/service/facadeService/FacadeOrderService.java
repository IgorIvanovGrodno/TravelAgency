package com.company.service.facadeService;

import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

public interface FacadeOrderService {
    long makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost);
}
