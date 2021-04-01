package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterLogin", urlPatterns = "/app/*")
public class FilterLogin implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession();
        Object isLoggedIn = session.getAttribute("login");
        if(isLoggedIn==null){
            HttpServletResponse httpResp = (HttpServletResponse) response;
            httpResp.sendRedirect("login");
            return;
        }

        chain.doFilter(request,response);

    }
}
