package com.example.victorbello.facebookrecipes.recipelist;


/**
 * Created by victorbello on 24/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;

public interface RecipeListRepository {
    void getSavedRecipes();
    void getSavedFavorites();
    void updateRecipe(Recipe recipe);
    void removeRecipe(Recipe recipe);
}
