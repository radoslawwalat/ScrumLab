package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecipePlanDao {

    private static final String ADD_PLAN_RECIPE_QUERY =
            "INSERT INTO recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) \n"
                    + "VALUES (?, ?, ?, ?, ?)";
    private static final String CHECK_IF_GOT_RECIPEID_QUERY = "SELECT recipe_id FROM recipe_plan WHERE recipe_id = ?";
    private static final String DELETE_RECIPE_FROM_PLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";

    public RecipePlan addPlanRecipe(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_PLAN_RECIPE_QUERY,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, recipePlan.getRecipeId());
            statement.setString(2, recipePlan.getMealName());
            statement.setInt(3, recipePlan.getDisplayOrder());
            statement.setInt(4, recipePlan.getDayNameId());
            statement.setInt(5, recipePlan.getPlanId());

            int result = statement.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkIfRecipeInPlan (int recipeId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CHECK_IF_GOT_RECIPEID_QUERY);
            statement.setInt(1, recipeId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(int mealId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_FROM_PLAN_QUERY)) {
            statement.setInt(1, mealId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Id in recipe_plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}



