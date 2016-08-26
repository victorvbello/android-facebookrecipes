package com.example.victorbello.facebookrecipes.libs.base;

/**
 * Created by victorbello on 22/08/16.
 */

import android.widget.ImageView;

public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
