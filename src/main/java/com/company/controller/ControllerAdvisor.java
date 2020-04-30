package com.company.controller;

import com.company.exceptions.ControllerException;
import com.company.exceptions.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("404");
        return modelAndView;
    }

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("generic_error");
        return modelAndView;
    }

    @ExceptionHandler(ControllerException.class)
    public ModelAndView handleControllerException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("generic_error");
        return modelAndView;
    }
}
