package com.company.config;

import com.company.utils.logger.LoggingAspect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.Locale;

/**
 * This class is implementation {@link WebMvcConfigurer}. It configure Spring MVC.
 *
 * @author Igor Ivanov
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.company.controller", "com.company.utils"})
public class WebConfiguration implements WebMvcConfigurer
{
    /**
     * This method adds static resources handler.
     *
     * @param registry - resource registry handler.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * This method creates, configures, returns Tiles configure.
     *
     * @return Tiles configure.
     */
    @Bean
    public TilesConfigurer tilesConfigurer()
    {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/*.xml");
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    /**
     * This method creates and returns AOP logger.
     *
     * @return AOP logger.
     */
    @Bean
    public LoggingAspect loggingAspect()
    {
        return new LoggingAspect();
    }

    /**
     * This method returns tiles view resolver.
     * @return tiles view resolver.
     */
   @Bean
   public TilesViewResolver viewResolver()
   {
       return new TilesViewResolver();
   }

    /**
     * This method creates, configures and returns exception resolver.
     *
     * @return exception resolver.
     */
    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver()
    {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        simpleMappingExceptionResolver.setDefaultErrorView("generic_error");
        return simpleMappingExceptionResolver;
    }

    /**
     * This method creates, configures and returns message source.
     *
     * @return message source.
     */
    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/locales/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * This method creates, configures and returns locale change interceptor.
     *
     * @return locale change interceptor.
     */
    @Bean(name = "localeChangeInterceptor")
    public LocaleChangeInterceptor getLocaleChangeInterceptor()
    {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("languageVar");
        return localeChangeInterceptor;
    }

    /**
     * This method receives interceptor registry and adds locale change interceptor to the registry.
     *
     * @param registry - interceptor registry.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(getLocaleChangeInterceptor()).addPathPatterns("/*");
    }

    /**
     * This method creates, configures and returns cookie locale resolver.
     *
     * @return cookie locale resolver.
     */
    @Bean(name = "localeResolver")
    public CookieLocaleResolver getLocaleResolver()
    {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("ru"));
        cookieLocaleResolver.setCookieMaxAge(100000);
        return cookieLocaleResolver;
    }
}
