package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.PlanDetailed;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletTestAS", value = "/ServletTestAS")
public class ServletTestAS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlanDao planDao = new PlanDao();

       PlanDetailed test = (planDao.findAdminLastPlan(1)).get(0);


        response.getWriter().println(test.getDayName());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
