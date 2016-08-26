package com.example.victorbello.facebookrecipes.recipelist;


/**
 * Created by victorbello on 24/08/16.
 */

import java.util.List;
import java.util.Arrays;

import com.example.victorbello.facebookrecipes.entities.Recipe_Table;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Select;

import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.libs.base.EventBus;
import com.example.victorbello.facebookrecipes.recipelist.events.RecipeListEvent;


public class RecipeListRepositoryImpl implements RecipeListRepository {

    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList<Recipe> storeRecipe=new FlowCursorList<Recipe>(false,Recipe.class);
        post(RecipeListEvent.READ_EVENT,storeRecipe.getAll());
        storeRecipe.close();
    }

    @Override
    public void getSavedFavorites() {
        List<Recipe> recipes=new Select().from(Recipe.class).where(Recipe_Table.favorite.is(true)).queryList();
        post(RecipeListEvent.READ_EVENT,recipes);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT,Arrays.asList(recipe));
    }

    private void post(){
        post(RecipeListEvent.UPDATE_EVENT,null);
    }

    private void post(int type, List<Recipe> recipeList){
        RecipeListEvent event=new RecipeListEvent();
        event.setType(type);
        event.setRecipeList(recipeList);
        eventBus.post(event);
    }
}
