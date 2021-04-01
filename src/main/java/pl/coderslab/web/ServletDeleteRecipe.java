package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletDeleteRecipe", urlPatterns = "/app/recipe/delete")
public class ServletDeleteRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String recipeId = request.getParameter("id");
        RecipeDao recipeDao = new RecipeDao();
        RecipePlanDao recipePlanDao = new RecipePlanDao();

        if (recipePlanDao.checkIfRecipeInPlan(Integer.parseInt(recipeId))) {
            response.sendRedirect("list");
            return;
        } else {
            recipeDao.delete(Integer.parseInt(recipeId));
        }

        response.sendRedirect("list");

    }
}
