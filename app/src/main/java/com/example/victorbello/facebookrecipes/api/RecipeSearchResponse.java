package com.example.victorbello.facebookrecipes.api;


/**
 * Created by victorbello on 22/08/16.
 */

import java.util.List;

import com.example.victorbello.facebookrecipes.entities.Recipe;

public class RecipeSearchResponse {

    private int count;
    private List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe getFirstRecipe(){
        Recipe first=null;
        if(!recipes.isEmpty()){
            first=recipes.get(0);
        }
        return first;
    }
}
