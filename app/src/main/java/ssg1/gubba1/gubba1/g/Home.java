package ssg1.gubba1.gubba1.g;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.Fragments.CRMLead;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadActivity;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadActivityList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadActivityMOM;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadActivityMOMList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadAddress;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadAddressList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadContacts;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadContactsList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadEnquiry;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadEnquiryList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadList;
import ssg1.gubba1.gubba1.g.Fragments.DashboardFragment;
import ssg1.gubba1.gubba1.g.Fragments.FirstContactForm;
import ssg1.gubba1.gubba1.g.Fragments.FirstContactFormList;
import ssg1.gubba1.gubba1.g.Fragments.Gateentry;
import ssg1.gubba1.gubba1.g.Fragments.GateentryList;
import ssg1.gubba1.gubba1.g.Fragments.InwardMemo;
import ssg1.gubba1.gubba1.g.Fragments.InwardMemoInspection;
import ssg1.gubba1.gubba1.g.Fragments.InwardMemoProduct;
import ssg1.gubba1.gubba1.g.Fragments.InwardMemoProductBayWiseDenomination;
import ssg1.gubba1.gubba1.g.Fragments.InwardMemoProductWeight;
import ssg1.gubba1.gubba1.g.Fragments.InwardmemoInspectionList;
import ssg1.gubba1.gubba1.g.Fragments.InwardmemoList;
import ssg1.gubba1.gubba1.g.Fragments.InwardmemoProductList;
import ssg1.gubba1.gubba1.g.Fragments.InwardmemoProductWeightList;
import ssg1.gubba1.gubba1.g.Fragments.InwardmemoproductbaywisedenominationList;
import ssg1.gubba1.gubba1.g.Fragments.SlotBooking;
import ssg1.gubba1.gubba1.g.Fragments.SlotBookingList;
import ssg1.gubba1.gubba1.g.Fragments.SlotBookingProduct;
import ssg1.gubba1.gubba1.g.Fragments.SlotBookingProductList;
import ssg1.gubba1.gubba1.g.interfaces.IJsonParserInterface;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;

public class Home extends AppCompatActivity implements IJsonParserInterface {
    private static Home homer;
    public static Toolbar mToolbar;
    Fragment fragment;
    CallWebService service;
    public static FirstContactForm firstContactForm;
    public static FirstContactFormList firstContactFormList;
    public static CRMLeadActivityMOM crmLeadActivityMOM;
    public static CRMLeadActivityMOMList crmLeadActivityMOMList;
    public static CRMLead crmLead;
    public static SlotBookingProductList slotBookingProductList;
    public static CRMLeadList crmLeadList;
    public static SlotBooking slotBooking;
    public static SlotBookingProduct slotBookingProduct;
    public static SlotBookingList slotBookingList;
    public static CRMLeadEnquiryList crmLeadEnquiryList;
    public static CRMLeadAddressList crmLeadAddressList;
    public static CRMLeadContactsList crmLeadContactsList;
    public static CRMLeadActivityList crmLeadActivityList;
    public static CRMLeadEnquiry crmLeadEnquiry;
    public static CRMLeadContacts crmLeadContacts;
    public static CRMLeadAddress crmLeadAddress;
    public static CRMLeadActivity crmLeadActivity;
    public static DashboardFragment dashboardFragment;
    public static Login login;
    public static Gateentry gateentry;
    public static InwardMemo inwardMemo;
    public static InwardMemoProduct inwardMemoProduct;
    public static InwardMemoInspection inwardMemoInspection;
    public static InwardMemoProductBayWiseDenomination inwardMemoProductBayWiseDenomination;
    public static InwardMemoProductWeight inwardMemoProductWeight;
    public static InwardmemoList inwardmemoList;
    public static InwardmemoProductList inwardmemoProductList;
    public static InwardmemoInspectionList inwardmemoInspectionList;
    public static InwardmemoproductbaywisedenominationList inwardmemoproductbaywisedenominationList;
    public static InwardmemoProductWeightList inwardmemoProductWeightList;
    public static GateentryList gateentryList;

    public static String firstContactForm_url;
    public static String firstContactFormSaved_url;
    public static String crmLeadActivitymomsending_url;
    public static String crmLead_url;
    public static String gateentry_url;
    public static String inwardmemo_url;
    public static String inwardmemoproduct_url;
    public static String inwardmemoinspection_url;
    public static String inwardmemoproductweight_url;
    public static String slotbooking_url;
    public static String slotbookingUpdate_url;
    public static String slotbooking_list_url;
    public static String gateentry_list_url;
    public static String inwardmemo_list_url;
    public static String inwardmemoproduct_list_url;
    public static String inwardmemoinspection_list_url;
    public static String inwardmemoproductbaywisedenomination_list_url;
    public static String inwardmemoproductweight_list_url;
    public static String product_list_url;
    public static String firstContactFormList_url;
    public static String crmLeadEnquirySending_url;
    public static String crmLeadEnquiryProduct_url;
    public static String slotbookinghybrid_url;
    public static String slotbookingproductProduct_url;
    public static String crmLeadAddress_url;
    public static String crmLeadContactsSending_url;
    public static String crmLeadAddressSending_url;
    public static String crmLeadCountry_url;
    public static String crmLeadRegion_url;
    public static String crmLeadActivity_url;
    public static String crmLeadContacts_url;
    public static String getCrmLeadContacts_url;
    public static String slotnumberdocumentno_url;
    public static String hamalicontractorrequest_url;
    public static String driver_url;
    public static String getcustomerdepartmentrecords_url;
    public static String slottimerecords_url;
    public static String getcustomernamerecords_url;
    public static String getCrmLeadActivityAllRecords_url;
    public static String getCrmLeadActivitymomAllRecords_url;
    public static String getCrmLeadmomAllRecords_url;
    public static String slotbookingProductlast_url;
    public static String getCrmLeadEnquiryAllRecords_url;
    public static String firstContactFormAllRecords_url;
    public static String getCrmLeadContactsAllRecords_url;
    public static String getCrmLeadAddressAllRecords_url;
    public static String crm_lead_list_url;
    public static String crm_lead_list_enquiry_url;
    public static String crm_lead_list_address_url;
    public static String crm_lead_list_contacts_url;
    public static String crm_lead_list_activity_url;
    public static String crm_lead_list_activity_mom_url;
    public static String crmLeadAddress_contacts_url;
    public static String crmLeadmomSending_url;
    public static String crmLeadAllRecords_url;
    public  static String mobileversion_url;

    FrameLayout frame;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    ActionBarDrawerToggle actionBarDrawerToggle;

    public static Home getInstance() {
        return homer;
    }

    private void setupToolbar(final Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("  Gubba Dashboard");

        // Set up the activity to use this toolbar. As a side effect this sets the Toolbar's title
        // to the activity's title.
        setSupportActionBar(mToolbar);

        if (savedInstanceState != null) {
            // Some IDEs such as Android Studio complain about possible NPE without this check.
            assert getSupportActionBar() != null;
            getSupportActionBar().setTitle("  Gubba Dashboard");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homer = this;

        setupToolbar(savedInstanceState);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showShowCaseView();
            }
        }, 500);*/

        setNavigationHeader();

        frame = (FrameLayout) findViewById(R.id.frame);
        try {

            for (int i = 0; i < mToolbar.getChildCount(); i++) {
                View view = mToolbar.getChildAt(i);

                if (view instanceof TextView) {
                    TextView textView = (TextView) view;

                }

            }
        } catch (Exception ex) {
        }

        try {
            fragment = new DashboardFragment();
            dashboardFragment = (DashboardFragment) fragment;
            FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.frame, fragment, "Home");
            fragmentTransaction.commitAllowingStateLoss();
        }catch (Exception e){}



                drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

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

                item.setCheckable(true);
                item.setChecked(true);

                MenuItem mPreviousMenuItem = null;
                if (mPreviousMenuItem != null) {
                    mPreviousMenuItem.setChecked(false);
                }

                FragmentTransaction fragmentTransaction = null;

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

                    case R.id.create_lead: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new CRMLead();
                            Home.crmLead = (CRMLead) fragment;
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

                    case R.id.create_lead_list: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new CRMLeadList();
                            Home.crmLeadList = (CRMLeadList) fragment;
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

                    case R.id.slot_booking: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new SlotBooking();
                            Home.slotBooking = (SlotBooking) fragment;
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

                    case R.id.slot_booking_list: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new SlotBookingList();
                            Home.slotBookingList = (SlotBookingList) fragment;
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

                    case R.id.gate_entry: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new Gateentry();
                            Home.gateentry = (Gateentry) fragment;
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

                    case R.id.gate_entry_list: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new GateentryList();
                            Home.gateentryList = (GateentryList) fragment;
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

                    /*case R.id.inward_memo: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new InwardMemo();
                            Home.inwardMemo = (InwardMemo) fragment;
                            fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            fragmentTransaction.replace(R.id.frame, fragment, "Home");
                            fragmentTransaction.commitAllowingStateLoss();
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }*/

                    case R.id.inward_memo_list: {
                        try {
                            new ToolbarConfigurer(Home.mToolbar, true);
                            fragment = new InwardmemoList();
                            Home.inwardmemoList = (InwardmemoList) fragment;
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
        getMenuInflater().inflate(R.menu.signout, menu);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            try {
                exit();
                return true;
            }catch (Exception e){e.printStackTrace();}
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void parseJsonResult(JSONObject json, String webserviceName) {

        System.out.println("first contact form response"+json);
        if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + firstContactForm_url)) {
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                firstContactForm.firstContactFormRespense(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLead_url)) {

            System.out.println("crm lead response"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                    crmLead.createLeadResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + gateentry_url)) {

            System.out.println("gate entry response"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                gateentry.gateentryResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemo_url)) {

            System.out.println("inward response"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                inwardMemo.inwardmemoResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemoproduct_url)) {

            System.out.println("inward product response"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                inwardMemoProduct.inwardmemoproductResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemoinspection_url)) {

            System.out.println("inward inspection response"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                inwardMemoInspection.inwardmemoinspectionResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemoproductweight_url)) {

            System.out.println("inward inspection response"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                inwardMemoProductWeight.inwardmemoproductweightResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + slotbookingProductlast_url)) {

            System.out.println("slot booking product"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                slotBookingProduct.slotbookingproductresponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crm_lead_list_activity_mom_url)) {

            System.out.println("activivty mom list"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    crmLeadActivityMOMList.crmLeadActivitymomListResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadEnquiryProduct_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadEnquiry.productResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + slotbookingproductProduct_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                slotBookingProduct.productResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + slotbookinghybrid_url)) {

            System.out.println("hybrid"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                slotBookingProduct.hybridresponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadEnquirySending_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadEnquiry.enquiry_final_response(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadActivitymomsending_url)) {

            System.out.println("activity mom response"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadActivityMOM.finalresponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadAddress_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadContacts.createLeadAddressResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadAddressSending_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadAddressSending_url=null;
                crmLeadAddress.finalResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadContactsSending_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadContacts.finalResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadCountry_url)) {

            System.out.println("country response openbravo"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadAddress.countryResponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadRegion_url)) {

            System.out.println("region response openbravo"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadAddress.regionResponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadActivity_url)) {

            System.out.println("activityresponse"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadActivity.activityResponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getCrmLeadContacts_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadActivity.contactsResponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + slotnumberdocumentno_url)) {

            System.out.println("slot document number"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                gateentry.slotnumberResponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + hamalicontractorrequest_url)) {

            System.out.println("hamali contractor"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                inwardMemo.hamaliContractorResponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + driver_url)) {

            System.out.println("driver list"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                gateentry.driverResponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getcustomerdepartmentrecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                slotBookingProduct.customerdepartmentresponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + slottimerecords_url)) {

            System.out.println("slot time records"+json);
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                slotBookingProduct.slottimeresponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getcustomernamerecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                slotBooking.customernameresponse(json);
            }
        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + firstContactFormList_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("first contact form list"+json);
                    firstContactFormList.firstContactFormListResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + firstContactFormAllRecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                firstContactForm.firstContactFormAllRecordsResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadAllRecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLead.crmLeadAllRecordsResponse(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getCrmLeadActivityAllRecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadActivity.activityAllRecords(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getCrmLeadActivitymomAllRecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadActivityMOM.momAllRecords(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getCrmLeadContactsAllRecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadContacts.contactAllRecords(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getCrmLeadAddressAllRecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadAddress.addressAllRecords(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + getCrmLeadEnquiryAllRecords_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                crmLeadEnquiry.enquiryAllRecords(json);
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crm_lead_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("crm lead list:"+json);
                    crmLeadList.crmLeadListResponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crm_lead_list_enquiry_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("crm lead enquiry list:"+json);
                    crmLeadEnquiryList.crmLeadEnquiryListResponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crm_lead_list_address_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("crm lead address list:"+json);
                    crmLeadAddressList.crmLeadAddressListResponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crm_lead_list_contacts_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("crm lead contacts list:"+json);
                    crmLeadContactsList.crmLeadContactsListResponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crmLeadAddress_contacts_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("address contacts:"+json);
                    crmLeadContacts.createLeadAddressResponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + crm_lead_list_activity_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("address contacts:"+json);
                    crmLeadActivityList.crmLeadActivityListResponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + slotbooking_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("slot booking list:"+json);
                    slotBookingList.slotbookinglistresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + gateentry_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("slot booking list:"+json);
                    gateentryList.gateentryresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemo_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("inwardememo list:"+json);
                    inwardmemoList.inwardresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemoproduct_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("inwardememo list:"+json);
                    inwardmemoProductList.inwardresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemoinspection_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("inwardememo inspection list:"+json);
                    inwardmemoInspectionList.inwardresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemoproductbaywisedenomination_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("inwardememo product bay wise denomination list:"+json);
                    inwardmemoproductbaywisedenominationList.inwardproductbaywisedenominationresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + inwardmemoproductweight_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("inwardememo productweight list:"+json);
                    inwardmemoProductWeightList.inwardproductweightresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + slotbooking_url)) {


            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("slot booking:"+json);
                    slotBooking.slotbookingresponse(json);
                }catch (Exception e){}
            }

        }

        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + product_list_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                try {
                    System.out.println("slot booking product list:"+json);
                    slotBookingProductList.slotbookinglistresponse(json);
                }catch (Exception e){}
            }

        }

    }
}
