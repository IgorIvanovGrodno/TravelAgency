package com.company.controller;

import com.company.exceptions.ControllerException;
import com.company.exceptions.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This class is exception handler for controllers and no found page error.
 *
 * @author Igor Ivanov
 */
@ControllerAdvice
public class ControllerAdvisor
{
    /**
     * This method handle NoHandlerFoundException and return ModelAndView with "404" page and error message.
     *
     * @param ex - exception.
     * @return ModelAndView with "404" page and error message.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("404");
        return modelAndView;
    }

    /**
     * This method handle ServiceException and return ModelAndView with "generic_error" page and error message.
     *
     * @param ex - exception.
     * @return ModelAndView with "generic_error" page and error message.
     */
    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(Exception ex)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("generic_error");
        return modelAndView;
    }

    /**
     * This method handle ControllerException and return ModelAndView with "generic_error" page and error message.
     *
     * @param ex - exception.
     * @return ModelAndView with "generic_error" page and error message.
     */
    @ExceptionHandler(ControllerException.class)
    public ModelAndView handleControllerException(Exception ex)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("generic_error");
        return modelAndView;
    }
}
