package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Book;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPE_QUERY = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String READ_RECIPE_QUERY_N = "SELECT * from recipe where name = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, created = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE	id = ?;";
private static final String ADMIN_RECIPES_QUERY =
        "SELECT * from recipe where admin_id = ?;";

    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }

    public Recipe read(String recipeName) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY_N)
        ) {
            statement.setString(1, recipeName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }

    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPE_QUERY);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();

                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));

                recipeList.add(recipeToAdd);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    public List<Recipe> findAll(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
             PreparedStatement statement = connection.prepareStatement(ADMIN_RECIPES_QUERY);
             statement.setInt(1, adminId);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();

                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));

                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }
// "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,?,?,?,?,?);";

    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setString(4, recipe.getCreated());
            insertStm.setString(5, recipe.getUpdated());
            insertStm.setInt(6, recipe.getPreparationTime());
            insertStm.setString(7, recipe.getPreparation());
            insertStm.setInt(8, recipe.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // deletes recipe by id
    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //"UPDATE recipe SET name = ? , ingredients = ?, description = ?, created = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE	id = ?;";


    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(9, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getCreated());
            statement.setString(5, recipe.getUpdated());
            statement.setInt(6, recipe.getPreparationTime());
            statement.setString(7, recipe.getPreparation());
            statement.setInt(8, recipe.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //  Utwórz metodę pobierającą liczbę planów dodanych przez aktualnie zalogowanego użytkownika.
    //  Informacja ta jest wyświetlana na ekranie głównym aplikacji.

    private static final String RECIPE_COUNT_QUERRY = "SELECT id FROM recipe WHERE admin_id = ?;";

    public int findAllAdminsRecipes(int adminId) {
        int count = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(RECIPE_COUNT_QUERRY)) {
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



}
