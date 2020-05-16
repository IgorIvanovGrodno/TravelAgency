package com.company.model.dao.order;

import com.company.model.dao.GenericDAO;
import com.company.model.domain.order.Order;

/**
 * This interface is concrete repository interface, that provides operations for working with order's entities.
 *
 * @author Igor Ivanov
 */
public interface OrderDAO extends GenericDAO<Order, Long>
{
}
