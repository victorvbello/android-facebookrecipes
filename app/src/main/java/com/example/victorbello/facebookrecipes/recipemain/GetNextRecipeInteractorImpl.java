package com.example.victorbello.facebookrecipes.recipemain;

/**
 * Created by victorbello on 23/08/16.
 */

import java.util.Random;

public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor {

    private RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository){
        this.repository=repository;
    }

    @Override
    public void execute() {
        int recipePage=new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipePage);
        repository.getNextRecipe();
    }
}
