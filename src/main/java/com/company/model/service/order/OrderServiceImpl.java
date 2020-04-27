package com.company.model.service.order;

import com.company.exceptions.ServiceException;
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
    public void makePayment(Order order, TourPackage tourPackageOrder, User currentUser, StatusOrder statusOrder) throws ServiceException {
        if(order==null
                ||tourPackageOrder==null
                ||currentUser==null
                ||statusOrder==null) throw new ServiceException("Incorrect input data for payment");
        order.setTourPackage(tourPackageOrder);
        order.setUser(currentUser);
        order.setStatus(statusOrder);
        order.setCreateDate(new Date());
        orderDAO.makePersistent(order);
    }
}
