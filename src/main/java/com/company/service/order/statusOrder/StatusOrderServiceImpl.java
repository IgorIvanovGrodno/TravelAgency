package com.company.service.order.statusOrder;

import com.company.model.DAO.order.statusOrder.StatusOrderDAO;
import com.company.model.domain.order.StatusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusOrderServiceImpl implements StatusOrderService {
    private StatusOrderDAO statusOrderDAO;

    @Autowired
    public StatusOrderServiceImpl(StatusOrderDAO statusOrderDAO) {
        this.statusOrderDAO = statusOrderDAO;
    }

    @Override
    public StatusOrder getStatusForNewOrder() {
        return statusOrderDAO.getStatusForNewOrder();
    }
}
