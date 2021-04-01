package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletRegister", urlPatterns = "/register")
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");

        AdminDao userToAdd = new AdminDao();
        Admin singleUserToAdd = new Admin();
        singleUserToAdd.setFirstName(name);
        singleUserToAdd.setLastName(surname);
        singleUserToAdd.setEmail(email);
        if (pass.equals(repass)){
            singleUserToAdd.setPassword(pass);
            userToAdd.createAdmin(singleUserToAdd);
            response.sendRedirect("login");
        } else {
            response.getWriter().println("Podales zle hasla");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);

    }
}
