package com.example.mazhar.g.utils;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.mazhar.g.AppController;
import com.example.mazhar.g.Home;
import com.example.mazhar.g.Login;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by muni on 28/08/17.
 */

public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    Fragment fragment;
    FragmentTransaction fragmentTransaction;

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String activityToBeOpened;

        if (data != null) {
            activityToBeOpened = data.optString("activityToBeOpened", null);
            if (activityToBeOpened != null && activityToBeOpened.equalsIgnoreCase("supervisor")) {
                if(!DataValidation.isNullString(new Constants().USER_ID)) {
                    Intent intent = new Intent(AppController.getInstance(), Home.class);
                    intent.putExtra("From","supervisorconfirmation");
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppController.getInstance().startActivity(intent);
                }
                else{
                    AppController.getInstance().startActivity(new Intent(AppController.getInstance(), Login.class));
                }
               // AppController.getInstance().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            } else if (activityToBeOpened != null && activityToBeOpened.equalsIgnoreCase("executive")) {
                if(!DataValidation.isNullString(new Constants().USER_ID)) {
                    Intent intent = new Intent(AppController.getInstance(), Home.class);
                    intent.putExtra("From","executiveconformation");
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppController.getInstance().startActivity(intent);
                }
                else{
                    AppController.getInstance().startActivity(new Intent(AppController.getInstance(), Login.class));
                }
            }
            else if (activityToBeOpened != null && activityToBeOpened.equalsIgnoreCase("matindentsup")){
                if(!DataValidation.isNullString(new Constants().USER_ID)) {
                    Intent intent = new Intent(AppController.getInstance(), Home.class);
                    intent.putExtra("From","miapproval");
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppController.getInstance().startActivity(intent);
                }
                else{
                    AppController.getInstance().startActivity(new Intent(AppController.getInstance(), Login.class));
                }
            }
            else if (activityToBeOpened != null && activityToBeOpened.equalsIgnoreCase("matindentexe")){
                if(!DataValidation.isNullString(new Constants().USER_ID)) {
                    Intent intent = new Intent(AppController.getInstance(), Home.class);
                    intent.putExtra("From","miapproval");
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppController.getInstance().startActivity(intent);
                }
                else{
                    AppController.getInstance().startActivity(new Intent(AppController.getInstance(), Login.class));
                }
            }

        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

    }

    private void overridePendingTransition(int slide_in_left, int slide_out_right) {
    }
}
