package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.model.PlanDetailed;
import pl.coderslab.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLAN_QUERY = "SELECT * FROM plan;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE id = ?;";
    private static final String PLAN_RECENTLY_ADDED_QUERY =
            "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description, recipe_plan.plan_id as plan_id, plan.name as plan_name\n" +
                    "FROM `recipe_plan` JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id JOIN plan ON recipe_plan.plan_id = plan.id\n" +
                    "WHERE recipe_plan.plan_id = (SELECT MAX(id) from plan WHERE admin_id = ?) ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String PLAN_COUNT_QUERY = "SELECT id FROM plan WHERE admin_id = ?";
    private static final String ADMIN_PLANS_QUERY = "SELECT * from plan where admin_id = ?;";
    private static final String PLAN_DETAILS_QUERY = "SELECT recipe_plan.id as id, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE plan_id = ? \n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    // zwraca plan po id
    public static Plan read(Integer planId) {
        Plan plan = new Plan();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)) {
            statement.setInt(1, planId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    plan.setId(rs.getInt("id"));
                    plan.setName(rs.getString("name"));
                    plan.setDescription(rs.getString("description"));
                    plan.setCreated(rs.getString("created"));
                    plan.setAdminId(rs.getInt("admin_id"));
                }
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    // zwraca liste wszystkich planów

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_QUERY);
             ResultSet rs = statement.executeQuery()) {
            while(rs.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(rs.getInt("id"));
                planToAdd.setName(rs.getString("name"));
                planToAdd.setDescription(rs.getString("description"));
                planToAdd.setCreated(rs.getString("created"));
                planToAdd.setAdminId(rs.getInt("admin_id"));
                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public List<Plan> findAll(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()){
             PreparedStatement statement = connection.prepareStatement(ADMIN_PLANS_QUERY);
             statement.setInt(1, adminId);
             ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(rs.getInt("id"));
                planToAdd.setName(rs.getString("name"));
                planToAdd.setDescription(rs.getString("description"));
                planToAdd.setCreated(rs.getString("created"));
                planToAdd.setAdminId(rs.getInt("admin_id"));
                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }


    // CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";

    public Plan create(Plan plan){
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setString(3, plan.getCreated());
            insertStm.setInt(4, plan.getAdminId());

            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Plan not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE	id = ?;";
    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(4, plan.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public int findAllAdminsPlans(int adminId) {
        int count = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(PLAN_COUNT_QUERY)) {
            statement.setInt(1, adminId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public List<PlanDetailed> findAdminLastPlan(int adminId) {
        List<PlanDetailed> resultPlan = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(PLAN_RECENTLY_ADDED_QUERY)) {
            statement.setInt(1, adminId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PlanDetailed planDetailed = new PlanDetailed();
                    planDetailed.setDayName(rs.getString("day_name"));
                    planDetailed.setMealName(rs.getString("meal_name"));
                    planDetailed.setRecipeName(rs.getString("recipe_name"));
                    planDetailed.setRecipeDescription(rs.getString("recipe_description"));
                    planDetailed.setPlanId(rs.getInt("plan_id"));
                    planDetailed.setPlanName(rs.getString("plan_name"));
                    resultPlan.add(planDetailed);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultPlan;
    }


    public List<PlanDetailed> findPlanDetails(int planId) {
        List<PlanDetailed> resultPlan = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(PLAN_DETAILS_QUERY)) {
            statement.setInt(1, planId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PlanDetailed planDetailed = new PlanDetailed();
                    planDetailed.setDayName(rs.getString("day_name"));
                    planDetailed.setMealName(rs.getString("meal_name"));
                    planDetailed.setRecipeName(rs.getString("recipe_name"));
                    planDetailed.setRecipeDescription(rs.getString("recipe_description"));
                    planDetailed.setId(rs.getInt("id"));
                    planDetailed.setPlanId(planId);
                    resultPlan.add(planDetailed);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return resultPlan;
    }





}
