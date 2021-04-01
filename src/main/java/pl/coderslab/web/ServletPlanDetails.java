package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetailed;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletPlanDetails", urlPatterns = "/app/plan/details")
public class ServletPlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPlan = request.getParameter("id");
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(Integer.parseInt(idPlan));
        request.setAttribute("planObj", plan);
        
        List<PlanDetailed> foundPlans = planDao.findPlanDetails(plan.getId());
        request.setAttribute("detailsList", foundPlans);




        getServletContext().getRequestDispatcher("/app/app-schedule-details.jsp").forward(request, response);
    }
}
