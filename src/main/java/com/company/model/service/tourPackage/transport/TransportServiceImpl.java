package com.company.model.service.tourPackage.transport;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.transport.TransportDAO;
import com.company.model.domain.tourPackage.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {
    private TransportDAO transportDAO;

    @Autowired
    public TransportServiceImpl(TransportDAO transportDAO) {
        this.transportDAO = transportDAO;
    }

    @Override
    public Optional<List<Transport>> getAllTransports() {
       return Optional.of(transportDAO.findAll());

    }

    @Override
    public Optional<Transport> getTransportByName(String name) throws ServiceException {
        if(name==null) throw new ServiceException("Incorrect value of type tour of transport");
        return Optional.of(transportDAO.findByName(name));
    }
}
