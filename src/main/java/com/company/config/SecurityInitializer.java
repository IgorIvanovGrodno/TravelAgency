package com.company.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This class is implementation of {@link AbstractSecurityWebApplicationInitializer} which automatically register
 * the springSecurityFilterChain Filter for every URL and adds a ContextLoaderListener that loads the SecurityConfig.
 *
 * @author Igor Ivanov
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer
{
}
