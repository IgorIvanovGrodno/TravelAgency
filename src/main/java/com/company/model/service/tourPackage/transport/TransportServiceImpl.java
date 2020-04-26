package com.company.model.service.tourPackage.transport;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.transport.TransportDAO;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.domain.tourPackage.TypeTourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Transport> getAllTransports() {
        Optional<List<Transport>> optionalTransport = Optional.of(transportDAO.findAll());
        return optionalTransport.orElse(new ArrayList<>());
    }

    @Override
    public Transport getTransportByName(String name) throws ServiceException {
        if(name==null) throw new ServiceException("Incorrect value of type tour of transport");
        Optional<Transport> optionalTransport = Optional.of(transportDAO.findByName(name));
        return optionalTransport.orElse(new Transport());
    }
}
