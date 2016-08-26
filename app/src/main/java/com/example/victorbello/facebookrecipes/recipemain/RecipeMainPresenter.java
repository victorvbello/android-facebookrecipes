package com.example.victorbello.facebookrecipes.recipemain;

/**
 * Created by victorbello on 23/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.recipemain.event.RecipeMainEvent;
import com.example.victorbello.facebookrecipes.recipemain.ui.RecipeMainView;

public interface RecipeMainPresenter {
    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);

    void imageReady();
    void imageError(String error);

    RecipeMainView getView();
}
