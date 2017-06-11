package co.pushfortask.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Marios on 10/06/2017.
 */

public class NetworkUtilities {

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        return false;
    }
}
