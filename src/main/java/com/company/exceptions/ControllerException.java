package com.company.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is exception for controllers.
 *
 * @author Igor Ivanov
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ControllerException extends Exception
{
    /**
     * Constructor which receives error's message and transmits it to constructor of super class.
     *
     * @param message - error's message.
     */
    public ControllerException(String message)
    {
        super(message);
    }
}
