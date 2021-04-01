package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.Day;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {

    private final String DAY_ID_QUERY =
            "SELECT id FROM day_name WHERE id = ?";
    private final String DAY_NAME_QUERY =
            "SELECT name FROM day_name WHERE id = ?";
    private final String ALL_DAYS_QUERY =
            "SELECT * FROM day_name";

    public int getDayId(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DAY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
//            "SELECT id FROM day_name WHERE id = ?";
            if (resultSet.next()) {
                id = resultSet.getInt("id");

                return id;
            }
            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public String getDayName(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DAY_NAME_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
//            "SELECT day FROM day_name WHERE id = ?";
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Day> findAll() {

        List<Day> dayList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(ALL_DAYS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Day day = new Day();
                day.setId(resultSet.getInt("id"));
                day.setName(resultSet.getString("name"));
                day.setDisplayOrder(resultSet.getInt("display_order"));
                dayList.add(day);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return dayList;
    }

}
