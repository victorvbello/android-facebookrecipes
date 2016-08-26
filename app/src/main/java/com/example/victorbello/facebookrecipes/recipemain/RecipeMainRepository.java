package com.example.victorbello.facebookrecipes.recipemain;

/**
 * Created by victorbello on 23/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;

public interface RecipeMainRepository {
    public final static int COUNT=1;
    public final static String RECENT_SORT="r";
    public final static int RECIPE_RANGE=100000;

    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setRecipePage(int recipePage);
}
