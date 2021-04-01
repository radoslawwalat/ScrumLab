package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDelRecipePlanConf", value = "/app/plan/deletemeal")
public class ServletDelRecipePlanConf extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String planId = request.getParameter("planId");
        String mealId = request.getParameter("mealId");

        request.setAttribute("planId", planId);
        request.setAttribute("mealId", mealId);

        getServletContext().getRequestDispatcher("/app/app-schedule-recipe-delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
