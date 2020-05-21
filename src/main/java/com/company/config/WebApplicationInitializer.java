package com.company.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class registers a DispatcherServlet and use Java-based Spring configuration.
 *
 * @author Igor Ivanov
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    /**
     * This method creates and returns the list of configuration classes for root application context.
     *
     * @return the list of configuration classes for root application context.
     */
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]{ApplicationConfiguration.class, SecurityConfiguration.class};
    }

    /**
     * This method creates and returns the list of configuration classes for servlet application context.
     *
     * @return the list of configuration classes for servlet application context.
     */
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{WebConfiguration.class};
    }

    /**
     * This method returns the servlet mapping for the DispatcherServlet.
     *
     * @return the servlet mapping for the DispatcherServlet.
     */
    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }

    /**
     * This method receives context {@link WebApplicationContext}, creates and configures DispatcherServlet.
     *
     * @param servletAppContext - context.
     * @return DispatcherServlet.
     */
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext)
    {
        DispatcherServlet dispatcher = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        dispatcher.setThrowExceptionIfNoHandlerFound(true);
        return dispatcher;
    }
}
