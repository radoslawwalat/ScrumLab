package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletPlanList", urlPatterns = "/app/plan/list")
public class ServletPlanList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("login");
        List<Plan> planList = planDao.findAll(admin.getId());
        request.setAttribute("planList", planList);
        getServletContext().getRequestDispatcher("/app/app-schedules.jsp").forward(request, response);
    }
}
