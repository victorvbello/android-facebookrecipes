package com.example.victorbello.facebookrecipes.recipemain.event;

/**
 * Created by victorbello on 23/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;

public class RecipeMainEvent {
    private int type;
    private String error;
    private Recipe recipe;

    public final static int NEXT_EVENT=0;
    public final static int SAVE_EVENT=1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
