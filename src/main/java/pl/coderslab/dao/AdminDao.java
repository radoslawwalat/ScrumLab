package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY =
            "INSERT INTO admins (id, first_name, last_name, email, password, superadmin, enable) VALUES (DEFAULT, ?, ?, ?,?, 0, DEFAULT)";

    private static final String READ_ADMIN_QUERY =
            "SELECT id, first_name, last_name, email, superadmin, enable FROM admins WHERE id = ?";

    private static final String READ_ALL_ADMIN_QUERY =
            "SELECT id, first_name, last_name, email FROM admins";

    private static final String UPDATE_PASS_ADMIN_QUERY =
            "UPDATE admins SET password = ? WHERE id = ?";

    private static final String UPDATE_SUPER_ADMIN_QUERY =
            "UPDATE admins SET superadmin = ? WHERE id = ?";

    private static final String UPDATE_MAIL_ADMIN_QUERY =
            "UPDATE admins SET email = ? WHERE id = ?";

    private static final String DELETE_ADMIN_QUERY =
            "DELETE FROM admins WHERE id = ? ";

    private static final String EXISTS_QUERY =
            "SELECT * FROM admins WHERE email = ?";

//    private static final String AUTHORIZE_QUERY =
//            "SELECT password FROM admins WHERE id = ?";

    public Admin createAdmin(Admin admin) {
        //"INSERT INTO admins (id , first_name 1, last_name 2, email 3, password 4, superadmin) VALUES (DEFAULT, ?, ?, ?,?, 0)";
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Admin readAdmin(int id) {
        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_ADMIN_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
//"SELECT first_name, last_name, email, superadmin, enable FROM admins WHERE id = ?";
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setSuperAdmin(intToBoolean(resultSet.getInt("superadmin")));
                admin.setEnabled(intToBoolean(resultSet.getInt("enable")));

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<Admin> listAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_ALL_ADMIN_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setSuperAdmin(intToBoolean(resultSet.getInt("superadmin")));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return adminList;

    }

    public Admin updatePassword(int id, String newPassword) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_PASS_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
//            "UPDATE admins SET password = ? WHERE id = ?";
            statement.setString(1, hashPassword(newPassword));
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();

            Admin admin = new Admin();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Admin updateSuperAdmin(int id, int trueFalse) {

        if (trueFalse < 0 || trueFalse > 1) {
            return null;
        }

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_SUPER_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
//            "UPDATE admins SET superadmin = ? WHERE id = ?";
            statement.setString(1, String.valueOf(trueFalse));
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();

            Admin admin = new Admin();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Admin updateMail(int id, String email) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_MAIL_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS);
//            "UPDATE admins SET email = ? WHERE id = ?";
            statement.setString(1, email);
            statement.setString(2, String.valueOf(id));
            statement.executeUpdate();

            Admin admin = new Admin();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void deleteAdmin(int id) {
        if (id == 1) {
            return;
        }
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(DELETE_ADMIN_QUERY);
//            "DELETE FROM admins WHERE id = ? ";
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //    private static final String EXISTS_QUERY =
//             "SELECT id, first_name, last_name, password FROM admins WHERE email = ?";
    public Admin adminExists(String email) {
        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(EXISTS_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
//"SELECT first_name, last_name, email, superadmin, enable FROM admins WHERE email = ?";
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setSuperAdmin(intToBoolean(resultSet.getInt("superadmin")));
                admin.setEnabled(intToBoolean(resultSet.getInt("enable")));
                admin.setPassword(resultSet.getString("password"));

                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    public boolean authorize(String inputPass, String hashPass) {

                if (BCrypt.checkpw(inputPass, hashPass)) {
                    return true;
                }
        return false;

    }

    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean intToBoolean(int zeroOne) {

        return zeroOne == 1;
    }

}

