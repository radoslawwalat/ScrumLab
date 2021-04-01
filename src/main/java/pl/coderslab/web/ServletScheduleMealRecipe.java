package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletScheduleMealRecipe", value = "/app/recipe/plan/add")
public class ServletScheduleMealRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RecipeDao recipeDao = new RecipeDao();
        PlanDao planDao = new PlanDao();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("login");
        List<Plan> planList = planDao.findAll(admin.getId());
        request.setAttribute("uPlans", planList);
        List<Recipe> recipeList = recipeDao.findAll(admin.getId());
        request.setAttribute("uRecipes", recipeList);
        DayNameDao dayNameDao = new DayNameDao();
        List<Day> dayList = dayNameDao.findAll();
        request.setAttribute("dayName", dayList);
        getServletContext().getRequestDispatcher("/app/app-schedules-meal-recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RecipePlanDao recipePlanDao = new RecipePlanDao();
//                recipe_plan: id  (recipe_id, meal_name, display_order, day_name_id, plan_id)
//            public RecipePlan(int recipeId, String mealName, int displayOrder, int dayNameId, int planId)

        try {
            int recipeId = Integer.parseInt(request.getParameter("recipe"));
            int dayId = Integer.parseInt(request.getParameter("day"));
            String mealName = request.getParameter("mealname");
            int mealOrder = Integer.parseInt(request.getParameter("mealorder"));
            int planId = Integer.parseInt(request.getParameter("plan"));

            recipePlanDao.addPlanRecipe(new RecipePlan(recipeId, mealName, mealOrder, dayId, planId));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }

        response.sendRedirect("add");

    }
}
