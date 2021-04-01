package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletRecipeDetails", urlPatterns = "/app/recipe/details")
public class ServletRecipeDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeName = request.getParameter("name");
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeName);
        request.setAttribute("recipeObj", recipe);
        String[] ingredients = recipe.getIngredients().split(" ");
        request.setAttribute("ingredientsArr", ingredients);
        getServletContext().getRequestDispatcher("/app/app-recipe-details.jsp").forward(request, response);

    }
}
