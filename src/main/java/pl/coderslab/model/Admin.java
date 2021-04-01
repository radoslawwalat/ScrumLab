package pl.coderslab.model;

public class Admin {

    //(id, first_name, last_name, email, password, superadmin, enable)

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isSuperAdmin;
    private boolean isEnabled;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isSuperAdmin=" + isSuperAdmin +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
