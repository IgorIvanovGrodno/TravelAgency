package com.company.model.service.tourPackage.transport;


import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.Transport;

import java.util.List;
import java.util.Optional;

public interface TransportService {
    Optional<List<Transport>> getAllTransports();

    Optional<Transport> getTransportByName(String name) throws ServiceException;
}
