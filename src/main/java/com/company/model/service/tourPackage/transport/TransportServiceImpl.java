package com.company.model.service.tourPackage.transport;

import com.company.model.dao.tourPackage.transport.TransportDAO;
import com.company.model.domain.tourPackage.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    private TransportDAO transportDAO;

    @Autowired
    public TransportServiceImpl(TransportDAO transportDAO) {
        this.transportDAO = transportDAO;
    }

    @Override
    public List<Transport> getAllTransports() {
        return transportDAO.findAll();
    }

    @Override
    public Transport getTransportByName(String name) {
        return transportDAO.findByName(name);
    }
}
