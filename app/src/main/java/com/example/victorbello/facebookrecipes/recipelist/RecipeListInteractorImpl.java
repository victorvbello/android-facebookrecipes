package com.example.victorbello.facebookrecipes.recipelist;

/**
 * Created by victorbello on 24/08/16.
 */
public class RecipeListInteractorImpl implements RecipeListInteractor {

    private RecipeListRepository repository;

    public RecipeListInteractorImpl(RecipeListRepository  repository){
        this.repository=repository;
    }

    @Override
    public void execute() {
        repository.getSavedRecipes();
    }

    @Override
    public void getListFavorites() {
        repository.getSavedFavorites();
    }
}
