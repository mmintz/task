package co.pushfortask.Application;

import android.content.Context;
import android.util.Log;

import co.pushfortask.events.ApplicationReadyEvent;
import de.greenrobot.event.EventBus;

/**
 * Created by Marios on 10/06/2017.
 */

public class Application extends android.app.Application {

    private static final String TAG = Application.class.getName();
    private static Application sApplication;
    private String mUserApiInternalStoragePath;

    public static Application getInstance() {
        return sApplication;
    }

    public static Context getContext() {
        return sApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        sApplication = this;
        mUserApiInternalStoragePath = Application.getContext().getFilesDir().getAbsolutePath();
    }

}
