package com.example.victorbello.facebookrecipes.recipemain.ui;

/**
 * Created by victorbello on 23/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;

public interface RecipeMainView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);
    void onGetRecipeError(String error);
}
