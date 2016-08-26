package com.example.victorbello.facebookrecipes.recipemain.di;


/**
 * Created by victorbello on 23/08/16.
 */


import javax.inject.Singleton;
import dagger.Component;

import com.example.victorbello.facebookrecipes.libs.base.ImageLoader;
import com.example.victorbello.facebookrecipes.libs.di.LibsModule;
import com.example.victorbello.facebookrecipes.recipemain.RecipeMainPresenter;

@Singleton
@Component(modules={RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {

    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();
}
