package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter("/*")
public class FilterEncoding implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        System.out.println("ustawiono utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
