package com.company.model.service.tourPackage.transport;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.transport.TransportDAO;
import com.company.model.domain.tourPackage.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is implementation for service which provides business logic's methods for working with transports.
 *
 * @author Igor Ivanov
 */
@Service
public class TransportServiceImpl implements TransportService
{
    /**
     * This field is transport's data access object.
     */
    private TransportDAO transportDAO;

    /**
     * Constructor which receives and assign transport's data access object.
     *
     * @param transportDAO - transport's data access object.
     */
    @Autowired
    public TransportServiceImpl(TransportDAO transportDAO)
    {
        this.transportDAO = transportDAO;
    }

    /**
     * This method returns all transports.
     *
     * @return optional list of all transports.
     */
    @Override
    public Optional<List<Transport>> getAllTransports()
    {
        return Optional.ofNullable(transportDAO.findAll());
    }

    /**
     * This method receives identifier and returns transport by this identifier.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param idOfTransport - identifier of transport.
     * @return optional of transport by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public Optional<Transport> getTransportById(Long idOfTransport) throws ServiceException
    {
        if (idOfTransport == null)
        {
            throw new ServiceException("Incorrect value of type tour of transport");
        }
        return Optional.ofNullable(transportDAO.findById(idOfTransport));
    }
}
