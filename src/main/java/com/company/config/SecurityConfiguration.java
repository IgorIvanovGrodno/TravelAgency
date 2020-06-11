package com.company.config;

import com.company.utils.CustomUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

/**
 * This class is configuration class for spring security context.
 *
 * @author Igor Ivanov
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    /**
     * This field is data source.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * This method receives {@link AuthenticationManagerBuilder} and sets data source, password encoder, queries for
     * getting login, password, role, status.
     *
     * @param auth - {@link AuthenticationManagerBuilder}
     * @throws Exception when {@link AuthenticationManagerBuilder} methods throw exception.
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT login, password, active FROM authorization WHERE login=?")
                .authoritiesByUsernameQuery("SELECT login, role FROM authorization WHERE login=?");
    }

    /**
     * This method receives {@link HttpSecurity} and configures access to URL, authentication page,
     * logout and remember me features, handler success authentication.
     *
     * @param http - {@link HttpSecurity}
     * @throws Exception when {@link HttpSecurity} methods throw Exception.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .and()
                .formLogin().loginPage("/authorization").permitAll().usernameParameter("j_username")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").successHandler(myAuthenticationSuccessHandler()).failureUrl("/authorization?error=true")
                .and()
                .authorizeRequests().antMatchers("/admin*").hasRole("ADMIN")
                .antMatchers("/user*").hasRole("USER")
                .antMatchers("/registration*").anonymous()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .rememberMe().key("myKey").tokenValiditySeconds(300)
                .and()
                .csrf().disable();
    }

    /**
     * This method returns custom {@link AuthenticationSuccessHandler} which handles success authentication.
     *
     * @return custom {@link AuthenticationSuccessHandler}.
     */
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler()
    {
        return new CustomUrlAuthenticationSuccessHandler();
    }

    /**
     * This method returns password encoder.
     *
     * @return password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
