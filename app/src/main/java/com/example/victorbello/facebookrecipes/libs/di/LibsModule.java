package com.example.victorbello.facebookrecipes.libs.di;


/**
 * Created by victorbello on 22/08/16.
 */

import dagger.Module;
import dagger.Provides;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import com.example.victorbello.facebookrecipes.libs.GlideImageLoader;
import com.example.victorbello.facebookrecipes.libs.GreenRobotEventBus;
import com.example.victorbello.facebookrecipes.libs.base.EventBus;
import com.example.victorbello.facebookrecipes.libs.base.ImageLoader;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Singleton;

@Module
public class LibsModule {
    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    public RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    public Activity providesActivity(){
        return this.activity;
    }

    @Provides
    @Singleton
    public EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    public org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
