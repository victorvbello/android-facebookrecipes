package com.example.victorbello.facebookrecipes;

/**
 * Created by victorbello on 22/08/16.
 */

import android.app.Application;
import android.content.Intent;

import com.example.victorbello.facebookrecipes.recipelist.di.DaggerRecipeListComponent;
import com.example.victorbello.facebookrecipes.recipelist.di.RecipeListModule;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;

import com.example.victorbello.facebookrecipes.libs.di.LibsModule;
import com.example.victorbello.facebookrecipes.login.ui.LoginActivity;
import com.example.victorbello.facebookrecipes.recipelist.ui.RecipeListView;
import com.example.victorbello.facebookrecipes.recipemain.ui.RecipeMainView;
import com.example.victorbello.facebookrecipes.recipelist.di.RecipeListComponent;
import com.example.victorbello.facebookrecipes.recipelist.ui.RecipeListActivity;
import com.example.victorbello.facebookrecipes.recipelist.ui.adapter.OnItemClickListener;
import com.example.victorbello.facebookrecipes.recipemain.di.DaggerRecipeMainComponent;
import com.example.victorbello.facebookrecipes.recipemain.di.RecipeMainComponent;
import com.example.victorbello.facebookrecipes.recipemain.di.RecipeMainModule;
import com.example.victorbello.facebookrecipes.recipemain.ui.RecipeMainActivity;

public class FacebookRecipesApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        DBTearDown();
    }

    public void DBTearDown(){
        FlowManager.destroy();
    }

    public void initFacebook(){
        FacebookSdk.sdkInitialize(this);
    }

    public void initDB(){
        FlowManager.init(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent=new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                |Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view){
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }

    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener clickListener){
        return DaggerRecipeListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(view,clickListener))
                .build();
    }
}
