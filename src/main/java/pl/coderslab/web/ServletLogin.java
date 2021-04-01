package pl.coderslab.web;


import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = "/login")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDao adminDao = new AdminDao();
        Admin user = adminDao.adminExists(email);


        if (user != null) {
            if (adminDao.authorize(password, user.getPassword())) {
                HttpSession sess = request.getSession();
                // ustaw na null hasło przed zapisaniem do sesji
                user.setPassword(null);
                sess.setAttribute("login", user);
                response.sendRedirect("app/dashboard");
                return;
            } else {
                response.sendRedirect("login");
                return;
            }
        }
            response.sendRedirect("login");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
