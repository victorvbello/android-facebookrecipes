package com.example.victorbello.facebookrecipes.api;


/**
 * Created by victorbello on 22/08/16.
 */

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface RecipeService {
    @GET("search")
    Call<RecipeSearchResponse> search(@Query("key") String key,
                                      @Query("sort") String sort,
                                      @Query("count") int count,
                                      @Query("page") int page);
}
