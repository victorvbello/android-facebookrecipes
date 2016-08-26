package com.example.victorbello.facebookrecipes.recipelist;

/**
 * Created by victorbello on 24/08/16.
 */

import com.example.victorbello.facebookrecipes.libs.base.EventBus;
import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.recipelist.events.RecipeListEvent;
import com.example.victorbello.facebookrecipes.recipelist.ui.RecipeListView;

import org.greenrobot.eventbus.Subscribe;

public class RecipeListPresenterImpl implements RecipeListPresenter {

    private EventBus eventBus;
    private RecipeListView view;
    private StoredRecipesInteractor storedInteractor;
    private RecipeListInteractor listInteractor;

    public RecipeListPresenterImpl(EventBus eventBus, RecipeListView view, StoredRecipesInteractor storedInteractor, RecipeListInteractor listInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.storedInteractor = storedInteractor;
        this.listInteractor = listInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view=null;
    }

    @Override
    public void getRecipes() {
        listInteractor.execute();
    }

    @Override
    public void getFavoritesRecipes() {
        listInteractor.getListFavorites();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        storedInteractor.executeDelete(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean fav=recipe.getFavorite();
        recipe.setFavorite(!fav);
        storedInteractor.executeUpdate(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if(view!=null){
            switch (event.getType()){
                case RecipeListEvent.READ_EVENT:
                    view.setRecipes(event.getRecipeList());
                    break;
                case RecipeListEvent.UPDATE_EVENT:
                    view.recipeUpdate();
                    break;
                case RecipeListEvent.DELETE_EVENT:
                    Recipe recipe=event.getRecipeList().get(0);
                    view.recipeDeleted(recipe);
                    break;
            }
        }
    }

    @Override
    public RecipeListView getView() {
        return view;
    }
}
