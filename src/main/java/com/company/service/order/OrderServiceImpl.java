package com.company.service.order;

import com.company.model.DAO.order.OrderDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Override
    public long makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost) {
        order.setTourPackage(tourPackageOrder);
        order.setUser(currentUser);
        order.setTotalCost(totalCost);
        return orderDAO.saveOrder(order);
    }
}