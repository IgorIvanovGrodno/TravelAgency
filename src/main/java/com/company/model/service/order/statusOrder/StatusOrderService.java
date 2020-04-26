package com.company.model.service.order.statusOrder;

import com.company.model.domain.order.StatusOrder;

import java.util.Optional;

public interface StatusOrderService {
    Optional<StatusOrder> getStatusForNewOrder();
}
