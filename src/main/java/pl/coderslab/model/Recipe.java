package pl.coderslab.model;

public class Recipe {
    int id;
    String name;
    String ingredients;
    String description;
    String created;
    String updated;
    int preparationTime;
    String preparation;
    int adminId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getPreparation() {
        return preparation;
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

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
