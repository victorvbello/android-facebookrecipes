package com.example.victorbello.facebookrecipes.recipelist;

/**
 * Created by victorbello on 24/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;

public class StoredRecipesInteractorImpl implements StoredRecipesInteractor{

    private RecipeListRepository repository;

    public StoredRecipesInteractorImpl (RecipeListRepository repository){
        this.repository=repository;
    }

    @Override
    public void executeUpdate(Recipe recipe) {
        repository.updateRecipe(recipe);
    }

    @Override
    public void executeDelete(Recipe recipe) {
        repository.removeRecipe(recipe);
    }
}
