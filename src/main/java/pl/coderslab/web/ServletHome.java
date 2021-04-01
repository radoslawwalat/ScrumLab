package pl.coderslab.web;

import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Do not change servlet address !!!
 */
@WebServlet("")
public class ServletHome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
