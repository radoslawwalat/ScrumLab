package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "ServletPlanAdd", urlPatterns = "/app/plan/add")
public class ServletPlanAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        HttpSession sess = request.getSession();
        Admin admin = (Admin) sess.getAttribute("login");
        PlanDao planDao = new PlanDao();
        String data = LocalDateTime.now().toString();

        Plan plan = new Plan(planName, planDescription, data, admin.getId());

        planDao.create(plan);

        response.sendRedirect("list");





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/app/app-add-schedules.jsp").forward(request, response);
    }
}
