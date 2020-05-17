package com.company.model.service.tourPackage.transport;

import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.Transport;

import java.util.List;
import java.util.Optional;

/**
 * This class is interface for service which provides business logic's methods for working with transports.
 *
 * @author Igor Ivanov
 */
public interface TransportService
{
    /**
     * This method returns all transports.
     *
     * @return optional list of all transports.
     */
    Optional<List<Transport>> getAllTransports();

    /**
     * This method receives identifier and returns transport by this identifier.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param idOfTransport - identifier of transport.
     * @return optional of transport by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    Optional<Transport> getTransportById(Long idOfTransport) throws ServiceException;
}
