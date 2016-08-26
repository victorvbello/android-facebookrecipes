package com.example.victorbello.facebookrecipes.libs.base;

/**
 * Created by victorbello on 22/08/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
