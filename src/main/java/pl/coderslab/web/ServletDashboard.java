package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.PlanDetailed;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletDashboard", value = "/app/dashboard")
public class ServletDashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RecipeDao recipeDao = new RecipeDao();
        PlanDao planDao = new PlanDao();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("login");
        request.setAttribute("uRecipeCount", recipeDao.findAllAdminsRecipes(admin.getId()));
        request.setAttribute("uPlanCount", planDao.findAllAdminsPlans(admin.getId()));
        List<PlanDetailed> planDetailedList = planDao.findAdminLastPlan(admin.getId());
        request.setAttribute("uPlanLast", planDetailedList);
        getServletContext().getRequestDispatcher("/app/dashboard.jsp").include(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
