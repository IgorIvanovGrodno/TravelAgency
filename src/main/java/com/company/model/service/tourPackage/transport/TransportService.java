package com.company.model.service.tourPackage.transport;


import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.Transport;

import java.util.List;

public interface TransportService {
    List<Transport> getAllTransports();

    Transport getTransportByName(String name) throws ServiceException;
}
