package com.example.victorbello.facebookrecipes.recipelist;

/**
 * Created by victorbello on 24/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.recipelist.events.RecipeListEvent;
import com.example.victorbello.facebookrecipes.recipelist.ui.RecipeListView;

public interface RecipeListPresenter {
    void onCreate();
    void onDestroy();

    void getRecipes();
    void getFavoritesRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);

    RecipeListView getView();
}
