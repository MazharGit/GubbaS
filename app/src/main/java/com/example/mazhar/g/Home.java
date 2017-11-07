package com.example.mazhar.g;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mazhar.g.Fragments.CRMLeadActivityMOM;
import com.example.mazhar.g.Fragments.FirstContactForm;
import com.example.mazhar.g.Fragments.FirstContactFormList;
import com.example.mazhar.g.interfaces.IJsonParserInterface;
import com.example.mazhar.g.utils.CallWebService;
import com.example.mazhar.g.utils.Constants;
import com.yarolegovich.slidingrootnav.SlidingRootNav;

import org.json.JSONObject;

public class Home extends AppCompatActivity implements IJsonParserInterface {
    private static Home homer;
    public static Toolbar mToolbar;
    Fragment fragment;
    CallWebService service;
    public static FirstContactForm firstContactForm;
    public static FirstContactFormList firstContactFormList;
    public static CRMLeadActivityMOM crmLeadActivityMOM;

    public static String firstContactForm_url;

    public static String mobileuniqueid;

    private SlidingRootNav slidingRootNav;

    FrameLayout frame;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    public static FragmentActivity getInstance() {
        return homer;
    }

    private void setupToolbar(final Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Gubba Dashboard");

        // Set up the activity to use this toolbar. As a side effect this sets the Toolbar's title
        // to the activity's title.
        setSupportActionBar(mToolbar);

        if (savedInstanceState != null) {
            // Some IDEs such as Android Studio complain about possible NPE without this check.
            assert getSupportActionBar() != null;
            getSupportActionBar().setTitle("Gubba Dashboard");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homer = this;

        setupToolbar(savedInstanceState);

        setNavigationHeader();

        frame = (FrameLayout) findViewById(R.id.frame);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        if (mToolbar != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Fragment fragment = null;
                item.setCheckable(true);
                item.setChecked(true);

                MenuItem mPreviousMenuItem = null;
                if (mPreviousMenuItem != null) {
                    mPreviousMenuItem.setChecked(false);
                }
                mPreviousMenuItem = item;

                FragmentTransaction fragmentTransaction = null;

                /*if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);*/
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_first_contact_form: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new FirstContactForm();
                            Home.firstContactForm = (FirstContactForm) fragment;
                            fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            fragmentTransaction.replace(R.id.frame, fragment, "Home");
                            fragmentTransaction.commitAllowingStateLoss();
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    case R.id.nav_first_contact_form_list: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new FirstContactFormList();
                            Home.firstContactFormList = (FirstContactFormList) fragment;
                            fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            fragmentTransaction.replace(R.id.frame, fragment, "Home");
                            fragmentTransaction.commitAllowingStateLoss();
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    case R.id.logout: {
                        try {
                            exit();
                            return true;
                        }catch (Exception e){e.printStackTrace();}
                    }
                    default:
                        return true;

                }
            }
        });
    }

    public void setNavigationHeader() {


        try {

            navigationView = (NavigationView) findViewById(R.id.nav_view);

        } catch (Exception e) {
        }

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        try {

            // j = 0;
            System.out.println("FRAGMENT : " + getSupportFragmentManager().findFragmentByTag("fragment2"));

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();

            } else {

                if (doubleBackToExitPressedOnce) {
//                        super.onBackPressed();
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
                            closeA();
                        }
                    }
                }, 500);
            }


        } catch (Exception e) {
        }
    }

    public void closeA() {
        this.finish();
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    private void exit() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View titleView = inflater.inflate(R.layout.custom_title, null);

        builder.setCustomTitle(titleView);
        // builder.setTitle("Logout Confirmation");

        builder.setMessage("Do you want to exit ?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {

                    Toast.makeText(Home.this, "Logout successfully...", Toast.LENGTH_LONG).show();

                    /*mobileuniqueid = "ADUser?"+new Constants().USER_N_PASSWORD + "&_where=id='" +new Constants().USER_ID + "'&_startRow=0&_endRow=1";

                    try {
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + mobileuniqueid, new JSONObject().put("data", new JSONObject()
                                .put("_entityName", "ADUser")
                                .put("id", new Constants().USER_ID)
                                .put("comments", "null")), true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                    startActivity(new Intent(Home.this, Login.class));
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    getInstance().finish();

                }catch (Exception e){
                    Home.super.onBackPressed();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    @Override
    public void parseJsonResult(JSONObject json, String webserviceName) {

        if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + firstContactForm_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                firstContactForm.firstContactFormRespense(json);
            }

        }

    }
}
