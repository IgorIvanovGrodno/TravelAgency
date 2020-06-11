package com.company.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * This class is custom authentication success handler which implements AuthenticationSuccessHandler
 *
 * @author Igor Ivanov
 */
public class CustomUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    /**
     * This field is redirect strategy.
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * This method receives HTTP servlet request, HTTP servlet response, authentication.
     * It calls {@link CustomUrlAuthenticationSuccessHandler#handle(HttpServletRequest, HttpServletResponse, Authentication)}
     * and {@link CustomUrlAuthenticationSuccessHandler#clearAuthenticationAttributes(HttpServletRequest)}
     *
     * @param request        - HTTP servlet request.
     * @param response       - HTTP servlet response.
     * @param authentication -  authentication.
     * @throws IOException when {@link CustomUrlAuthenticationSuccessHandler#handle(HttpServletRequest, HttpServletResponse, Authentication)}
     *                     throws IOException.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException
    {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    /**
     * This method receives HTTP servlet request, HTTP servlet response, authentication. It gets target url
     * from {@link CustomUrlAuthenticationSuccessHandler#determineTargetUrl(Authentication)} and passes redirect parameters
     * to redirectStrategy.
     *
     * @param request        - HTTP servlet request.
     * @param response       - HTTP servlet response.
     * @param authentication -  authentication.
     * @throws IOException when redirectStrategy throws IOException.
     */
    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication) throws IOException
    {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted())
        {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * This methods receives authentication and determines target url according to authentication's role.
     *
     * @param authentication - authentication.
     * @return target url.
     */
    protected String determineTargetUrl(Authentication authentication)
    {
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities)
        {
            if (grantedAuthority.getAuthority().equals("ROLE_USER"))
            {
                isUser = true;
                break;
            }
            else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
            {
                isAdmin = true;
                break;
            }
        }

        if (isUser)
        {
            return "/";
        }
        else if (isAdmin)
        {
            return "/admin";
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    /**
     * This method receives HTTP servlet request and clears authentication's attribute in session.
     *
     * @param request - HTTP servlet.
     */
    protected void clearAuthenticationAttributes(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
