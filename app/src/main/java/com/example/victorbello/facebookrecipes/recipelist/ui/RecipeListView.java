package com.example.victorbello.facebookrecipes.recipelist.ui;

/**
 * Created by victorbello on 24/08/16.
 */

import java.util.List;

import com.example.victorbello.facebookrecipes.entities.Recipe;

public interface RecipeListView {
    void setRecipes(List<Recipe> data);
    void recipeUpdate();
    void recipeDeleted(Recipe recipe);
}
