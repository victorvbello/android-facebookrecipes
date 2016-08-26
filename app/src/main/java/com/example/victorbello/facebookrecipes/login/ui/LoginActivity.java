package com.example.victorbello.facebookrecipes.login.ui;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.support.design.widget.Snackbar;

import java.util.Arrays;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.CallbackManager;


import com.example.victorbello.facebookrecipes.R;
import com.example.victorbello.facebookrecipes.recipemain.ui.RecipeMainActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginButton btnLogin;
    private RelativeLayout container;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin =(LoginButton) findViewById(R.id.btnLogin);
        container=(RelativeLayout) findViewById(R.id.container);

        if(AccessToken.getCurrentAccessToken()!=null){
            navigateToMainScreen();
        }
        callbackManager=CallbackManager.Factory.create();
        btnLogin.setPublishPermissions(Arrays.asList("publish_actions"));
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){

            @Override
            public void onSuccess(LoginResult loginResult) {
                navigateToMainScreen();
            }

            @Override
            public void onCancel() {
                Snackbar.make(container,R.string.login_cancel_error,Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                String msgError=String.format(getString(R.string.login_error),
                        error.getLocalizedMessage());
                Snackbar.make(container,msgError,Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void navigateToMainScreen(){
        Intent intent=new Intent(this,RecipeMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        |Intent.FLAG_ACTIVITY_NEW_TASK
                        |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
