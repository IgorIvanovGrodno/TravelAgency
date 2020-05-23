package com.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is exception for services.
 *
 * @author Igor Ivanov
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends Exception
{
    /**
     * Constructor which receives error's message and transmits it to constructor of super class.
     *
     * @param message - error's message.
     */
    public ServiceException(final String message)
    {
        super(message);
    }
}
