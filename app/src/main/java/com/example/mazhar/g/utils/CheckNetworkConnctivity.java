package com.example.mazhar.g.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by fz1 on 6/10/16.
 */

public class CheckNetworkConnctivity {

    public static boolean checkConnectivity(Context context) {
        boolean enabled = true;
try {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo info = connectivityManager.getActiveNetworkInfo();

    if ((info == null || !info.isConnected() || !info.isAvailable())) {

        enabled = false;
    } else {
        enabled = true;
    }
}catch (Exception ex){
    enabled=true;
}
        return enabled;
    }
}
