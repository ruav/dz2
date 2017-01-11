package ru.inno.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Rudnev
 */
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();

        if(req.getSession().getAttribute("userName") == null && !url.endsWith("login")
                && !url.endsWith("register") && !url.endsWith("autority")
                && !url.contains("error")
                && !url.contains("js/")
                ) {
                resp.sendRedirect("/login");
                return;
        } else if(req.getSession().getAttribute("userName") != null
                && (req.getRequestURI().endsWith("login") || req.getRequestURI().endsWith("register")
                && !url.contains("js/")
                )){
            resp.sendRedirect("/login");
            return;
        }

        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    public void destroy() {
    }

}