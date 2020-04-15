package com.company.model.DAO.order;

import com.company.model.domain.order.Order;

public interface OrderDAO {
   long saveOrder(Order order);
}
