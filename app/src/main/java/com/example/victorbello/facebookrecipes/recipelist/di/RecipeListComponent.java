package com.example.victorbello.facebookrecipes.recipelist.di;


/**
 * Created by victorbello on 26/08/16.
 */



import dagger.Component;

import javax.inject.Singleton;

import com.example.victorbello.facebookrecipes.libs.di.LibsModule;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListPresenter;
import com.example.victorbello.facebookrecipes.recipelist.ui.adapter.RecipesAdapter;

@Singleton
@Component(modules={RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {

    RecipeListPresenter getPresenter();
    RecipesAdapter getAdapter();
}
