package com.example.victorbello.facebookrecipes.recipemain.ui;

/**
 * Created by victorbello on 22/08/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.GestureDetector;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.support.design.widget.Snackbar;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.example.victorbello.facebookrecipes.R;
import com.example.victorbello.facebookrecipes.recipemain.di.RecipeMainComponent;
import com.example.victorbello.facebookrecipes.FacebookRecipesApp;
import com.example.victorbello.facebookrecipes.recipelist.ui.RecipeListActivity;
import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.recipemain.RecipeMainPresenter;
import com.example.victorbello.facebookrecipes.libs.base.ImageLoader;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView,OnClickListener,SwipeGestureListener {

    private ImageView imgRecipe;
    private ImageButton imgDismiss;
    private ImageButton imgKeep;
    private ProgressBar progressBar;
    private RelativeLayout layoutContainer;

    private Recipe currentRecipe;
    private ImageLoader imageLoader;
    private RecipeMainPresenter presenter;
    private RecipeMainComponent component;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);

        imgRecipe=(ImageView) findViewById(R.id.imgRecipe);
        imgDismiss=(ImageButton) findViewById(R.id.imgDismiss);
        imgKeep=(ImageButton) findViewById(R.id.imgKeep);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        layoutContainer=(RelativeLayout) findViewById(R.id.layoutContainer);

        imgDismiss.setOnClickListener(this);
        imgKeep.setOnClickListener(this);

        setupInjection();
        setupImageLoader();
        setupGestureDetection();
        presenter.onCreate();
        presenter.getNextRecipe();
    }

    private void setupGestureDetection() {
        final GestureDetector gestureDetector=new GestureDetector(this,new SwipeGestureDetector(this));
        View.OnTouchListener gestureOnTouchListener=new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        imgRecipe.setOnTouchListener(gestureOnTouchListener);
    }

    private void setupImageLoader() {
        RequestListener glideRequestListener=new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoadingListener(glideRequestListener);
    }

    public void setupInjection(){
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        component=app.getRecipeMainComponent(this,this);
        imageLoader=getImageLoader();
        presenter=getPresenter();
    }

    public ImageLoader getImageLoader(){
        return component.getImageLoader();
    }

    public RecipeMainPresenter getPresenter(){
        return component.getPresenter();
    }

    @Override
    public void onKeep(){
        if(currentRecipe!=null){
            presenter.saveRecipe(currentRecipe);
        }
    }

    @Override
    public void onDismiss(){
        presenter.dismissRecipe();
    }


    public void navigateToListScreen(){
        startActivity(new Intent(this,RecipeListActivity.class));
    }

    public void logout(){
        FacebookRecipesApp app=(FacebookRecipesApp)getApplication();
        app.logout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_recipes_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_list){
            navigateToListScreen();
        }else if(id==R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view ){
        switch (view.getId()){
            case R.id.imgDismiss:
                onDismiss();
                break;
            case R.id.imgKeep:
                onKeep();
                break;
        }
    }

    @Override
    protected void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        imgKeep.setVisibility(View.VISIBLE);
        imgDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        imgKeep.setVisibility(View.GONE);
        imgDismiss.setVisibility(View.GONE);
    }

    private void clearImage(){
        imgRecipe.setImageResource(0);
    }

    @Override
    public void saveAnimation() {
        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.save_animation);
        animation.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(animation);
    }

    @Override
    public void dismissAnimation() {
        Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dismiss_animation);
        animation.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(animation);
    }

    private Animation.AnimationListener getAnimationListener(){
     return new Animation.AnimationListener(){

         @Override
         public void onAnimationStart(Animation animation) {

         }

         @Override
         public void onAnimationEnd(Animation animation) {
            clearImage();
         }

         @Override
         public void onAnimationRepeat(Animation animation) {

         }
     };
    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(layoutContainer,R.string.recipemain_notice_saved,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        this.currentRecipe=recipe;
        imageLoader.load(imgRecipe,recipe.getImageURL());
    }

    @Override
    public void onGetRecipeError(String error) {
        String msgError=String.format(getString(R.string.recipemain_error),error);
        Snackbar.make(layoutContainer,msgError,Snackbar.LENGTH_SHORT).show();
    }
}
