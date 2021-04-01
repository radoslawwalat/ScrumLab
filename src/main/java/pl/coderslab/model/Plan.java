package pl.coderslab.model;

public class Plan {
    private int id;
    private String name;
    private String description;
    private String created;
    private int adminId;

    public Plan(){

    }

    public Plan(String name, String description, String created, int adminId) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created + ", admin_id=" + adminId +"]";
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getCreated() {
        return created;
    }
    public int getAdminId() {
        return adminId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

}
