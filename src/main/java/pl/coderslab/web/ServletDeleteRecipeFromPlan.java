package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteRecipeFromPlan", urlPatterns = "/app/plan/delete")
public class ServletDeleteRecipeFromPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mealId = request.getParameter("mealId");
        String planId = request.getParameter("planId");

        RecipePlanDao recipePlanDao = new RecipePlanDao();

        // odpinam recipe od recipe_plan

        recipePlanDao.delete(Integer.parseInt(mealId));

        String location = "details?id=" + planId;

        response.sendRedirect(location);







    }
}
