package com.example.victorbello.facebookrecipes.recipelist.di;


/**
 * Created by victorbello on 26/08/16.
 */


import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import java.util.List;
import java.util.ArrayList;

import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.libs.base.EventBus;
import com.example.victorbello.facebookrecipes.libs.base.ImageLoader;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListInteractor;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListInteractorImpl;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListPresenter;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListPresenterImpl;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListRepository;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListRepositoryImpl;
import com.example.victorbello.facebookrecipes.recipelist.StoredRecipesInteractor;
import com.example.victorbello.facebookrecipes.recipelist.StoredRecipesInteractorImpl;
import com.example.victorbello.facebookrecipes.recipelist.ui.RecipeListView;
import com.example.victorbello.facebookrecipes.recipelist.ui.adapter.OnItemClickListener;
import com.example.victorbello.facebookrecipes.recipelist.ui.adapter.RecipesAdapter;



@Module
public class RecipeListModule {

    private RecipeListView view;
    private OnItemClickListener clickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.clickListener=onItemClickListener;
    }

    @Provides
    @Singleton
    public RecipeListView providesRecipeListView(){
        return this.view;
    }

    @Provides
    @Singleton
    public RecipeListPresenter providesRecipeListPresenter(EventBus eventBus, RecipeListView view, StoredRecipesInteractor storedInteractor, RecipeListInteractor listInteractor){
        return new RecipeListPresenterImpl(eventBus, view, storedInteractor, listInteractor);
    }

    @Provides
    @Singleton
    public StoredRecipesInteractor providesStoredRecipesInteractor(RecipeListRepository repository){
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    public RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repository){
        return new RecipeListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    public RecipeListRepository providesRecipeListRepository(EventBus eventBus){
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides
    @Singleton
    public RecipesAdapter providesRecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new RecipesAdapter(recipeList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    public OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    public List<Recipe> providesEmtyList(){
        return new ArrayList<Recipe>();
    }
}
