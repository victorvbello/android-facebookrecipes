package com.example.victorbello.facebookrecipes.recipemain.di;


/**
 * Created by victorbello on 23/08/16.
 */


import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import com.example.victorbello.facebookrecipes.api.RecipeClient;
import com.example.victorbello.facebookrecipes.api.RecipeService;
import com.example.victorbello.facebookrecipes.libs.base.EventBus;
import com.example.victorbello.facebookrecipes.recipemain.GetNextRecipeInteractor;
import com.example.victorbello.facebookrecipes.recipemain.GetNextRecipeInteractorImpl;
import com.example.victorbello.facebookrecipes.recipemain.RecipeMainPresenterImpl;
import com.example.victorbello.facebookrecipes.recipemain.RecipeMainRepository;
import com.example.victorbello.facebookrecipes.recipemain.RecipeMainRepositoryImpl;
import com.example.victorbello.facebookrecipes.recipemain.SaveRecipeInteractor;
import com.example.victorbello.facebookrecipes.recipemain.SaveRecipeInteractorImpl;
import com.example.victorbello.facebookrecipes.recipemain.ui.RecipeMainView;
import com.example.victorbello.facebookrecipes.recipemain.RecipeMainPresenter;


@Module
public class RecipeMainModule {

    private RecipeMainView view;

    public RecipeMainModule(RecipeMainView view){
        this.view=view;
    }

    @Provides
    @Singleton
    RecipeMainView providesRecipeMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(EventBus eventBus,
                                                    RecipeMainView view,
                                                    SaveRecipeInteractor saveRecipeInteractor,
                                                    GetNextRecipeInteractor getNextRecipeInteractor){
        return new RecipeMainPresenterImpl(eventBus, view, saveRecipeInteractor, getNextRecipeInteractor);
    }

    @Provides
    @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository){
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repository){
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeMainRepository providesRecipeMainRepository(EventBus eventBus, RecipeService service){
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }
}
