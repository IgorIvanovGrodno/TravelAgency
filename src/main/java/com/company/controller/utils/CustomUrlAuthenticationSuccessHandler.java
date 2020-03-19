package com.company.controller.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class CustomUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException{
            //do some logic here if you want something to be done whenever
            //the user successfully logs in.

            Collection<? extends GrantedAuthority> authorities
                    = authentication.getAuthorities();
            String targetUrl = null;
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("user")) {
                    targetUrl="user";
                    break;
                } else if (grantedAuthority.getAuthority().equals("admin")) {
                    targetUrl="admin";
                    break;
                }
            }
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
        }

}
