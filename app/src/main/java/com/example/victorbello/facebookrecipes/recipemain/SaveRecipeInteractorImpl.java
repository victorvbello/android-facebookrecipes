package com.example.victorbello.facebookrecipes.recipemain;

import com.example.victorbello.facebookrecipes.entities.Recipe;

/**
 * Created by victorbello on 23/08/16.
 */
public class SaveRecipeInteractorImpl implements SaveRecipeInteractor {

    private RecipeMainRepository repository;

    public SaveRecipeInteractorImpl(RecipeMainRepository repository){
        this.repository=repository;
    }

    @Override
    public void execute(Recipe recipe) {
        repository.saveRecipe(recipe);
    }
}
