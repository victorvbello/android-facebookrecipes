package com.example.victorbello.facebookrecipes.recipelist.ui;

/**
 * Created by victorbello on 23/08/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;
import android.net.Uri;

import com.example.victorbello.facebookrecipes.R;
import com.example.victorbello.facebookrecipes.FacebookRecipesApp;
import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.recipelist.RecipeListPresenter;
import com.example.victorbello.facebookrecipes.recipelist.di.RecipeListComponent;
import com.example.victorbello.facebookrecipes.recipelist.ui.adapter.OnItemClickListener;
import com.example.victorbello.facebookrecipes.recipelist.ui.adapter.RecipesAdapter;
import com.example.victorbello.facebookrecipes.recipemain.ui.RecipeMainActivity;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener{

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecipeListPresenter presenter;
    private RecipesAdapter adapter;
    private RecipeListComponent component;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);

        setupToolbar();
        setupInjection();
        setupRecyclerView();

        toolbar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onToolbarClick();
            }
        });

        presenter.onCreate();
        presenter.getRecipes();
    }

    private void setupInjection() {
        FacebookRecipesApp app=(FacebookRecipesApp) getApplication();
        component=app.getRecipeListComponent(this,this,this);
        presenter=getPresenter();
        adapter=getAdapter();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }

    public void setupToolbar(){
        setSupportActionBar(toolbar);
    }

    public void onToolbarClick(){
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_recipes_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        switch(id){
            case R.id.action_main:
                navigateToMainScreen();
                break;
            case R.id.action_show_all:
                showAllRecipes();
                break;
            case R.id.action_show_favs:
                showAllFavoritesRecipes();
                break;
            case R.id.action_logout:
                logout();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void showAllFavoritesRecipes() {
        presenter.getFavoritesRecipes();
    }

    private void showAllRecipes() {
        presenter.getRecipes();
    }

    private void logout(){
        FacebookRecipesApp app=(FacebookRecipesApp) getApplication();
        app.logout();
    }

    private void navigateToMainScreen(){
        startActivity(new Intent(this, RecipeMainActivity.class));
    }
    @Override
    public void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }
    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdate() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
       startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(recipe.getSourceURL())));
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    public RecipeListPresenter getPresenter() {
        return component.getPresenter();
    }

    public RecipesAdapter getAdapter() {
        return component.getAdapter();
    }
}
