package pl.coderslab.model;

public class PlanDetailed {
    String dayName;
    String mealName;
    String recipeName;
    String recipeDescription;
    int planId;
    String planName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getDayName() {
        return dayName;
    }

    public String getMealName() {
        return mealName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public int getPlanId() {
        return planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
}
