package com.captcha.botdetect.examples.jsf.login_form_filter.filters;

import com.captcha.botdetect.examples.jsf.login_form_filter.view.LoginForm;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the loginBean from session attribute
        LoginForm loginForm = (LoginForm) ((HttpServletRequest) request).getSession().getAttribute("loginForm");

        // Redirect to the login.xhtml page if user is not logged in.
        if ((loginForm == null) || (!loginForm.isLoggedIn())) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/login.xhtml");
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }		
}
