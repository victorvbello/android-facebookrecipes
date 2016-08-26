package com.example.victorbello.facebookrecipes.api;

/**
 * Created by victorbello on 22/08/16.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeClient {

    private Retrofit retrofit;
    private final static String BASE_URL="http://food2fork.com/api/";

    public RecipeClient(){
        this.retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RecipeService getRecipeService() {
        return retrofit.create(RecipeService.class);
    }
}
