package com.company.model.service.order.statusOrder;

import com.company.model.domain.order.StatusOrder;

import java.util.Optional;

/**
 * This class is interface for service which provides business logic's methods for working with order's statuses.
 *
 * @author Igor Ivanov
 */
public interface StatusOrderService
{
    /**
     * This method returns optional of status for new order.
     *
     * @return optional of status for new order.
     */
    Optional<StatusOrder> getStatusForNewOrder();
}
