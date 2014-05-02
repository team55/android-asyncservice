package com.joanzap.minim.internal;

import com.joanzap.minim.api.BaseEvent;
import com.joanzap.minim.demo.MainActivity;
import com.joanzap.minim.demo.event.UserEvent;

public class MainActivityInjector extends Injector<MainActivity> {

    // Make sure only minim internals can instantiate this
    // Instantiated using reflection
    @SuppressWarnings("UnusedDeclaration")
    MainActivityInjector() {}

    @Override
    public void inject(MainActivity injectable) {
        // Inject all @InjectService
        injectable.service = DemoServiceApi.get(injectable);
    }

    @Override
    void dispatch(MainActivity target, BaseEvent event) {
        // Dispatch all applicable @InjectResult
        if (event instanceof UserEvent) {
            try {
                target.onUserFetched((UserEvent) event);
            } catch (Throwable e) {
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
            }
        }
    }

}
