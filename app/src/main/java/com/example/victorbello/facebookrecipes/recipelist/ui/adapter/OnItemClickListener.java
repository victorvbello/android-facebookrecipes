package com.example.victorbello.facebookrecipes.recipelist.ui.adapter;


/**
 * Created by victorbello on 26/08/16.
 */

import com.example.victorbello.facebookrecipes.entities.Recipe;

public interface OnItemClickListener {
    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
}
