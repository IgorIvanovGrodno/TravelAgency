package com.company.service.order;

import com.company.model.dao.order.OrderDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.order.StatusOrder;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Override
    public Order makePayment(Order order, TourPackage tourPackageOrder, User currentUser, long totalCost, StatusOrder statusOrder) {
        order.setTourPackage(tourPackageOrder);
        order.setUser(currentUser);
        order.setTotalCost(totalCost);
        order.setStatus(statusOrder);
        order.setCreateDate(new Date());
        return orderDAO.makePersistent(order);
    }
}
