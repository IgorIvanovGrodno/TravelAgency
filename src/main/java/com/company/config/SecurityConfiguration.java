package com.company.config;

import com.company.controller.utils.CustomUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception{

        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT login, password, true FROM authorization WHERE login=?")
                .authoritiesByUsernameQuery("SELECT login, role\n" +
                        "        \t FROM authorization WHERE login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .csrf().disable()
        ;

    }
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomUrlAuthenticationSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
