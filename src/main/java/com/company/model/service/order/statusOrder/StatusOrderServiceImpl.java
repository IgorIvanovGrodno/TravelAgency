package com.company.model.service.order.statusOrder;

import com.company.model.dao.order.statusOrder.StatusOrderDAO;
import com.company.model.domain.order.StatusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusOrderServiceImpl implements StatusOrderService {
    private StatusOrderDAO statusOrderDAO;

    @Autowired
    public StatusOrderServiceImpl(StatusOrderDAO statusOrderDAO) {
        this.statusOrderDAO = statusOrderDAO;
    }

    @Override
    public Optional<StatusOrder> getStatusForNewOrder() {
        return Optional.ofNullable(statusOrderDAO.findById(1L));
    }
}
