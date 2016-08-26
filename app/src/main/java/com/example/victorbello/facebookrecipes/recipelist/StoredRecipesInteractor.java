package com.example.victorbello.facebookrecipes.recipelist;

/**
 * Created by victorbello on 24/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;

public interface StoredRecipesInteractor {
    void executeUpdate(Recipe recipe);
    void executeDelete(Recipe recipe);
}
