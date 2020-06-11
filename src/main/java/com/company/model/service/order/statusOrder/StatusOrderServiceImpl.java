package com.company.model.service.order.statusOrder;

import com.company.model.dao.order.statusOrder.StatusOrderDAO;
import com.company.model.domain.order.StatusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is implementation for service which provides business logic's methods for working with order's statuses.
 *
 * @author Igor Ivanov
 */
@Service
public class StatusOrderServiceImpl implements StatusOrderService
{
    /**
     * This field is status order's data access object.
     */
    private StatusOrderDAO statusOrderDAO;

    /**
     * Constructor which receives and assign status order's data access object.
     *
     * @param statusOrderDAO - status order's data access object.
     */
    @Autowired
    public StatusOrderServiceImpl(StatusOrderDAO statusOrderDAO)
    {
        this.statusOrderDAO = statusOrderDAO;
    }

    /**
     * This method returns optional of status for new order.
     *
     * @return optional of status for new order.
     */
    @Override
    public Optional<StatusOrder> getStatusForNewOrder()
    {
        return Optional.ofNullable(statusOrderDAO.findById(1L));
    }

    /**
     * This method returns optional of status for payed order.
     *
     * @return optional of status for payed order.
     */
    @Override
    public Optional<StatusOrder> getStatusForPayedOrder() {
        return Optional.ofNullable(statusOrderDAO.findById(2L));
    }
}
