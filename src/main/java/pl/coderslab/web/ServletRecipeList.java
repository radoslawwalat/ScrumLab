package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletRecipeList", urlPatterns = "/app/recipe/list")
public class ServletRecipeList extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RecipeDao recipeDao = new RecipeDao();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("login");
        List<Recipe> recipeList = recipeDao.findAll(admin.getId());
        request.setAttribute("recipeList", recipeList);
        getServletContext().getRequestDispatcher("/app/app-recipes.jsp").forward(request, response);
    }
}