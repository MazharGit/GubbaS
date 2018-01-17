package ssg1.gubba1.gubba1.g;

import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Mazhar on 13/09/17.
 */

public class ToolbarConfigurer implements View.OnClickListener {

    boolean doubleBackToExitPressedOnce = false;
    public ToolbarConfigurer(Toolbar toolbar, boolean displayHomeAsUpEnabled) {

        if (!displayHomeAsUpEnabled) return;
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {

            if (Home.getInstance().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                Home.getInstance().getSupportFragmentManager().popBackStack();

            } else {

                if (doubleBackToExitPressedOnce) {
                    System.exit(0);
                    doubleBackToExitPressedOnce = false;
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                // Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (doubleBackToExitPressedOnce) {
                            doubleBackToExitPressedOnce = false;
                            Home.getInstance().closeA();
                        }
                    }
                }, 500);
            }


        } catch (Exception e) {
        }


    }
}
