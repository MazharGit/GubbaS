package ssg1.gubba1.gubba1.g.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class Gateentry extends Fragment {

    View view;

    EditText Organization,Location,PreAlertSlotNo,TransactionType,PreAlertScheduledInTime,CustomerName,VehicleNumber,DriverName,DriverPhoneNumber,DriverLicenseNumber,DCNumber,TotalNoOfPackagingUnitsAsPerDC,TotalQtyAsPerDC,PreAlertRescheduledInTime,GateEntry,GateEntryTime,GateEntryDate,Status,DockInTime,DockOutTime,GateOutTime;
    TextInputLayout LOrganization,LLocation,LPreAlertSlotNo,LTransactionType,LPreAlertScheduledInTime,LCustomerName,LVehicleNumber,LDriverName,LDriverPhoneNumber,LDriverLicenseNumber,LDCNumber,LTotalNoOfPackagingUnitsAsPerDC,LTotalQtyAsPerDC,LPreAlertRescheduledInTime,LGateEntry,LGateEntryTime,LGateEntryDate,LStatus,LDockInTime,LDockOutTime,LGateOutTime;
    Button Save;
    String SOrganization,SLocation,SPreAlertSlotNo,STransactionType,SPreAlertScheduledInTime,SCustomerName,SVehicleNumber,SDriverName,SDriverPhoneNumber,SDriverLicenseNumber,SDCNumber,STotalNoOfPackagingUnitsAsPerDC,STotalQtyAsPerDC,SPreAlertRescheduledInTime,SGateEntry,SGateEntryTime,SGateEntryDate,SStatus,SDockInTime,SDockOutTime,SGateOutTime;
    String SROrganization,SRLocation,SRPreAlertSlotNo,SRTransactionType,SRPreAlertScheduledInTime,SRCustomerName,SRVehicleNumber,SRDriverName,SRDriverPhoneNumber,SRDriverLicenseNumber,SRDCNumber,SRTotalNoOfPackagingUnitsAsPerDC,SRTotalQtyAsPerDC,SRPreAlertRescheduledInTime,SRGateEntry,SRGateEntryTime,SRGateEntryDate,SRStatus,SRDockInTime,SRDockOutTime,SRGateOutTime;

    CallWebService service;

    String bpName,bpId,userId,orgIdd;

    FragmentTransaction fragmentTransaction;

    String replace="0";

    SharedPref sp;

    JSONArray jsonArray,jsonslotnumber,jsondriver;

    String customerid;

    int day,month,year;

    int part1,part2,part3;

    String driverid,slotnumberid;

    public String totalDate;

    Calendar calendar;

    TimePickerDialog timepickerdialog;

    private int CalendarHour, CalendarMinute;

    String format;

    public static String finall;

    String outputString,id,srname;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_gateentry, null);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.activity_gateentry, container, false);

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());
        jsonArray=new JSONArray();
        jsonslotnumber=new JSONArray();
        jsondriver=new JSONArray();

        try {
            slotnumberRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            driverRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow80").equals("0"))
                {
                    sp.removeData("loginshow80");
                    sp.writeString("loginshow80", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow81").equals("0"))
                {
                    sp.removeData("loginshow81");
                    sp.writeString("loginshow81", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Organization=(EditText) view.findViewById(R.id.input_organization);
        Location= (EditText) view.findViewById(R.id.input_location);
        PreAlertSlotNo = (EditText) view.findViewById(R.id.input_prealertslotno);
        TransactionType = (EditText) view.findViewById(R.id.input_transactiontypee);
        PreAlertScheduledInTime = (EditText) view.findViewById(R.id.input_scheduledintime);
        CustomerName = (EditText) view.findViewById(R.id.input_customernamee);
        VehicleNumber = (EditText) view.findViewById(R.id.input_vehiclenumber);
        DriverName = (EditText) view.findViewById(R.id.input_drivername);
        DriverPhoneNumber=(EditText) view.findViewById(R.id.input_driverphonenumber);
        DriverLicenseNumber=(EditText) view.findViewById(R.id.input_driverlicensenumber);
        DCNumber= (EditText) view.findViewById(R.id.input_dcnumber);
        TotalNoOfPackagingUnitsAsPerDC = (EditText) view.findViewById(R.id.input_totalnoofpackagingunits);
        TotalQtyAsPerDC = (EditText) view.findViewById(R.id.input_totalqtyasperdc);
        PreAlertRescheduledInTime = (EditText) view.findViewById(R.id.input_scheduledintime);
        GateEntry = (EditText) view.findViewById(R.id.input_gateentry);
        GateEntryTime = (EditText) view.findViewById(R.id.input_gateentrytime);
        GateEntryDate = (EditText) view.findViewById(R.id.input_gateentrydate);
        Status = (EditText) view.findViewById(R.id.input_status);
        DockInTime = (EditText) view.findViewById(R.id.input_dockintime);
        DockOutTime = (EditText) view.findViewById(R.id.input_dockoutoftime);
        GateOutTime = (EditText) view.findViewById(R.id.input_gateoutoftime);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LOrganization=(TextInputLayout) view.findViewById(R.id.input_layout_organization);
        LLocation= (TextInputLayout) view.findViewById(R.id.input_layout_location);
        LPreAlertSlotNo = (TextInputLayout) view.findViewById(R.id.input_layout_prealertslotno);
        LTransactionType = (TextInputLayout) view.findViewById(R.id.input_layout_transactiontypee);
        LPreAlertScheduledInTime = (TextInputLayout) view.findViewById(R.id.input_layout_scheduledintime);
        LCustomerName = (TextInputLayout) view.findViewById(R.id.input_layout_customernamee);
        LVehicleNumber = (TextInputLayout) view.findViewById(R.id.input_layout_vehiclenumber);
        LDriverName = (TextInputLayout) view.findViewById(R.id.input_layout_drivername);
        LDriverPhoneNumber=(TextInputLayout) view.findViewById(R.id.input_layout_driverphonenumber);
        LDriverLicenseNumber=(TextInputLayout) view.findViewById(R.id.input_layout_driverlicensenumber);
        LDCNumber= (TextInputLayout) view.findViewById(R.id.input_layout_dcnumber);
        LTotalNoOfPackagingUnitsAsPerDC = (TextInputLayout) view.findViewById(R.id.input_layout_totalnoofpackagingunits);
        LTotalQtyAsPerDC = (TextInputLayout) view.findViewById(R.id.input_layout_totalqtyasperdc);
        LPreAlertRescheduledInTime = (TextInputLayout) view.findViewById(R.id.input_layout_scheduledintime);
        LGateEntry = (TextInputLayout) view.findViewById(R.id.input_layout_gateentry);
        LGateEntryTime = (TextInputLayout) view.findViewById(R.id.input_layout_gateentrytime);
        LGateEntryDate = (TextInputLayout) view.findViewById(R.id.input_layout_gateentrydate);
        LStatus = (TextInputLayout) view.findViewById(R.id.input_layout_status);
        LDockInTime = (TextInputLayout) view.findViewById(R.id.input_layout_dockintime);
        LDockOutTime = (TextInputLayout) view.findViewById(R.id.input_layout_dockoutoftime);
        LGateOutTime = (TextInputLayout) view.findViewById(R.id.input_layout_gateoutoftime);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Location.setTypeface(UbuntuFont);
        Organization.setTypeface(UbuntuFont);
        PreAlertSlotNo.setTypeface(UbuntuFont);
        TransactionType.setTypeface(UbuntuFont);
        PreAlertScheduledInTime.setTypeface(UbuntuFont);
        CustomerName.setTypeface(UbuntuFont);
        VehicleNumber.setTypeface(UbuntuFont);
        DriverName.setTypeface(UbuntuFont);
        DriverPhoneNumber.setTypeface(UbuntuFont);
        DriverLicenseNumber.setTypeface(UbuntuFont);
        DCNumber.setTypeface(UbuntuFont);
        TotalNoOfPackagingUnitsAsPerDC.setTypeface(UbuntuFont);
        TotalQtyAsPerDC.setTypeface(UbuntuFont);
        PreAlertRescheduledInTime.setTypeface(UbuntuFont);
        GateEntry.setTypeface(UbuntuFont);
        GateEntryTime.setTypeface(UbuntuFont);
        GateEntryDate.setTypeface(UbuntuFont);
        Status.setTypeface(UbuntuFont);
        DockOutTime.setTypeface(UbuntuFont);
        GateOutTime.setTypeface(UbuntuFont);

        LOrganization.setTypeface(UbuntuFont);
        LLocation.setTypeface(UbuntuFont);
        LPreAlertSlotNo.setTypeface(UbuntuFont);
        LTransactionType.setTypeface(UbuntuFont);
        LPreAlertScheduledInTime.setTypeface(UbuntuFont);
        LCustomerName.setTypeface(UbuntuFont);
        LVehicleNumber.setTypeface(UbuntuFont);
        LDriverName.setTypeface(UbuntuFont);
        LDriverPhoneNumber.setTypeface(UbuntuFont);
        LDriverLicenseNumber.setTypeface(UbuntuFont);
        LDCNumber.setTypeface(UbuntuFont);
        LTotalNoOfPackagingUnitsAsPerDC.setTypeface(UbuntuFont);
        LTotalQtyAsPerDC.setTypeface(UbuntuFont);
        LPreAlertRescheduledInTime.setTypeface(UbuntuFont);
        LGateEntry.setTypeface(UbuntuFont);
        LGateEntryTime.setTypeface(UbuntuFont);
        LGateEntryDate.setTypeface(UbuntuFont);
        LStatus.setTypeface(UbuntuFont);
        LDockOutTime.setTypeface(UbuntuFont);
        LGateOutTime.setTypeface(UbuntuFont);

        try
        {

            SROrganization=getArguments().getString("sqtorganizationid");
            srname=getArguments().getString("sqtorganization");
            SRLocation=getArguments().getString("location");
            orgIdd=getArguments().getString("locationid");
            SRPreAlertSlotNo=getArguments().getString("slotno");
            slotnumberid=getArguments().getString("slotnoid");
            SRTransactionType=getArguments().getString("transactiontype");
            SRPreAlertScheduledInTime=getArguments().getString("scheduledintime");
            SRCustomerName=getArguments().getString("customername");
            customerid=getArguments().getString("customernameid");
            SRVehicleNumber=getArguments().getString("vehiclenumber");
            SRDriverName=getArguments().getString("drivername");
            driverid=getArguments().getString("drivernameid");
            SRDriverPhoneNumber=getArguments().getString("driverphonenumber");
            orgIdd=getArguments().getString("locationid");
            SRDriverLicenseNumber=getArguments().getString("driverlicensenumber");
            SRDCNumber=getArguments().getString("dcnumber");
            SRTotalNoOfPackagingUnitsAsPerDC=getArguments().getString("packagingunits");
            SRTotalQtyAsPerDC=getArguments().getString("quantitydc");
            SRPreAlertRescheduledInTime=getArguments().getString("rescheduledintime");
            SRGateEntry=getArguments().getString("documentno");
            SRGateEntryTime=getArguments().getString("gateentrydate");
            SRGateEntryDate=getArguments().getString("gateentrydate");
            SRStatus=getArguments().getString("status");
            id= getArguments().getString("id");
            SRDockInTime=getArguments().getString("dockintime");
            SRDockOutTime=getArguments().getString("dockouttime");
            SRGateOutTime=getArguments().getString("gateouttime");
            replace=getArguments().getString("replace");


        }catch (Exception e){}

        Organization.setText(srname);
        Location.setText(SRLocation);
        PreAlertSlotNo.setText(SRPreAlertSlotNo);
        TransactionType.setText(SRTransactionType);
        PreAlertScheduledInTime.setText(SRPreAlertScheduledInTime);
        CustomerName.setText(SRCustomerName);
        VehicleNumber.setText(SRVehicleNumber);
        DriverName.setText(SRDriverName);
        DriverPhoneNumber.setText(SRDriverPhoneNumber);
        DriverLicenseNumber.setText(SRDriverLicenseNumber);
        DCNumber.setText(SRDCNumber);
        TotalNoOfPackagingUnitsAsPerDC.setText(SRTotalNoOfPackagingUnitsAsPerDC);
        TotalQtyAsPerDC.setText(SRTotalQtyAsPerDC);
        PreAlertRescheduledInTime.setText(SRPreAlertRescheduledInTime);
        GateEntry.setText(SRGateEntry);
        GateEntryTime.setText(SRGateEntryTime);
        GateEntryDate.setText(SRGateEntryDate);
        Status.setText(SRStatus);
        DockInTime.setText(SRDockInTime);
        DockOutTime.setText(SRDockOutTime);
        GateOutTime.setText(SRGateOutTime);
        Status.setText("prealert");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = mdformat.format(calendar.getTime());
        GateEntryDate.setText(strDate);

        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        String currentDateandTime = df.format(date);
        System.out.println("datee"+currentDateandTime);
        GateEntryTime.setText(currentDateandTime);

        Organization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Organization Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationDialogBox();
            }
        });

        PreAlertSlotNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prealertslotnumber();
            }
        });

        TransactionType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactiontypedialogbox();
            }
        });

        CustomerName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customername();
                    }
                }
        );

        DriverName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        driver();
                    }
                }
        );

        GateOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Gate out Time Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        GateEntryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Gate Entry Date Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        GateEntryTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Gate Entry Time Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        DockOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Dock out Time Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Status Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        GateEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Gate Entry Number Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        PreAlertScheduledInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Pre Alert Scheduled In Time Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        DockInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTime();
                getDate();
            }
        });

        PreAlertRescheduledInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Pre Alert Rescheduled In Time Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SLocation=Location.getText().toString();
                SPreAlertSlotNo=PreAlertSlotNo.getText().toString();
                STransactionType=TransactionType.getText().toString();
                SPreAlertScheduledInTime=PreAlertScheduledInTime.getText().toString();
                SCustomerName=CustomerName.getText().toString();
                SVehicleNumber=VehicleNumber.getText().toString();
                SDriverName=DriverName.getText().toString();
                SDriverPhoneNumber=DriverPhoneNumber.getText().toString();
                SDriverLicenseNumber=DriverLicenseNumber.getText().toString();
                SDCNumber=DCNumber.getText().toString();
                STotalNoOfPackagingUnitsAsPerDC=TotalNoOfPackagingUnitsAsPerDC.getText().toString();
                STotalQtyAsPerDC=TotalQtyAsPerDC.getText().toString();
                SGateEntryTime=GateEntryTime.getText().toString();
                SGateEntryDate=GateEntryDate.getText().toString();
                SStatus=Status.getText().toString();

                if(TextUtils.isEmpty(SPreAlertSlotNo)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Pre Alert Slot Number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCustomerName)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Customer Name is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SVehicleNumber)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Vehicle Number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SDriverName)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Driver Name is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SDriverPhoneNumber)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Driver Phone Number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SDriverLicenseNumber)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Driver License Number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SDCNumber)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"DC Number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(STotalNoOfPackagingUnitsAsPerDC)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Total Number Of Packaging Units As Per DC is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(STotalQtyAsPerDC)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Total Quantity As Per DC is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SPreAlertSlotNo)) && !(TextUtils.isEmpty(SCustomerName)) && !(TextUtils.isEmpty(SVehicleNumber)) && !(TextUtils.isEmpty(SDriverName)) && !(TextUtils.isEmpty(SDriverPhoneNumber)) && !(TextUtils.isEmpty(SDriverLicenseNumber)) && !(TextUtils.isEmpty(SDCNumber)) && !(TextUtils.isEmpty(STotalNoOfPackagingUnitsAsPerDC)) && !(TextUtils.isEmpty(STotalQtyAsPerDC)))
                {

                    Home.gateentry_url = "sqt_gateentry?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    try {

                        if (replace.equalsIgnoreCase("0"))
                        {
                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("sQTPrealert",slotnumberid)
                                    .put("gateentrydate", SGateEntryDate)
                                    .put("gateentrytime", SGateEntryTime)
                                    .put("dockintime", SDockInTime)
                                    .put("transactiontype", STransactionType)
                                    .put("sQTCustomer", customerid)
                                    .put("vehicleno", SVehicleNumber)
                                    .put("sQTDriver",driverid)
                                    .put("phone", SDriverPhoneNumber)
                                    .put("licenseno", SDriverLicenseNumber)
                                    .put("dcnumber", SDCNumber)
                                    .put("status", SStatus)
                                    .put("unitperdc", Double.parseDouble(STotalNoOfPackagingUnitsAsPerDC))
                                    .put("qtyperdc", Double.parseDouble(STotalQtyAsPerDC)));
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.gateentry_url, jsonObject, true);

                        }
                        else
                        {

                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("sQTOrg", SROrganization)
                                    .put("sQTPrealert",slotnumberid)
                                    .put("gateentrydate", SGateEntryDate)
                                    .put("gateentrytime", SGateEntryTime)
                                    .put("dockintime", SDockInTime)
                                    .put("transactiontype", STransactionType)
                                    .put("sQTCustomer", customerid)
                                    .put("vehicleno", SVehicleNumber)
                                    .put("sQTDriver",driverid)
                                    .put("phone", SDriverPhoneNumber)
                                    .put("licenseno", SDriverLicenseNumber)
                                    .put("dcnumber", SDCNumber)
                                    .put("status", SStatus)
                                    .put("id",id)
                                    .put("unitperdc", Double.parseDouble(STotalNoOfPackagingUnitsAsPerDC))
                                    .put("qtyperdc", Double.parseDouble(STotalQtyAsPerDC)));
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.gateentry_url, jsonObject, true);

                            replace="0";
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    }
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.addbutton, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
            {
                Organization.setText("");
                Location.setText("");
                PreAlertSlotNo.setText("");
                TransactionType.setText("");
                PreAlertScheduledInTime.setText("");
                CustomerName.setText("");
                VehicleNumber.setText("");
                DriverName.setText("");
                DriverPhoneNumber.setText("");
                DriverLicenseNumber.setText("");
                DCNumber.setText("");
                TotalNoOfPackagingUnitsAsPerDC.setText("");
                TotalQtyAsPerDC.setText("");
                PreAlertRescheduledInTime.setText("");
                GateEntry.setText("");
                GateEntryTime.setText("");
                GateEntryDate.setText("");
                Status.setText("");
                DockOutTime.setText("");
                GateOutTime.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Bundle args = new Bundle();
                Fragment fragment = new GateentryList();
                Home.gateentryList = (GateentryList) fragment;
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                fragmentTransaction.commitAllowingStateLoss();
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void slotnumberRequest() throws Exception {

        Home.slotnumberdocumentno_url = "sqt_prealert?"+new Constants().USER_N_PASSWORD+"&_sortBy=updated%20";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.slotnumberdocumentno_url, true);

    }

    public void slotnumberResponse(JSONObject json){
        try {
            jsonslotnumber=json.optJSONObject("response").optJSONArray("data");
            System.out.println("jsonslotnumber"+jsonslotnumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void driverRequest() throws Exception {

        Home.driver_url = "SQT_Driver?"+new Constants().USER_N_PASSWORD+"&_sortBy=updated%20";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.driver_url, true);

    }

    public void driverResponse(JSONObject json){
        try {
            jsondriver=json.optJSONObject("response").optJSONArray("data");
            System.out.println("jsondriver"+jsondriver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gateentryResponse(JSONObject json){
        try {

            System.out.println("agte entry response"+json);

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n\n");

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {

                        }catch (Exception e){
                    }
                }
            });

            builder.show();

            Organization.setText("");
            Location.setText("");
            PreAlertSlotNo.setText("");
            TransactionType.setText("");
            PreAlertScheduledInTime.setText("");
            CustomerName.setText("");
            VehicleNumber.setText("");
            DriverName.setText("");
            DriverPhoneNumber.setText("");
            DriverLicenseNumber.setText("");
            DCNumber.setText("");
            TotalNoOfPackagingUnitsAsPerDC.setText("");
            TotalQtyAsPerDC.setText("");
            PreAlertRescheduledInTime.setText("");
            GateEntry.setText("");
            GateEntryTime.setText("");
            GateEntryDate.setText("");
            Status.setText("");
            DockOutTime.setText("");
            GateOutTime.setText("");

        }catch (Exception e){e.printStackTrace();}
    }

    HashMap<String,String> locationHp=new HashMap<>();

    public void locationDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> locationSpinner = new ArrayList<String>();
        JSONObject jsonObject=Constants.jsonLoginResponse;
        jsonArray=jsonObject.optJSONObject("response").optJSONObject("data").optJSONArray("Organizations");

        for(int i=0;i<jsonArray.length();i++)
        {
            locationSpinner.add(jsonArray.optJSONObject(i).optString("orgName"));
            locationHp.put(jsonArray.optJSONObject(i).optString("orgName"),jsonArray.optJSONObject(i).optString("orgId"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, locationSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Location.setText(str);
                orgIdd=locationHp.get(str);
                dialog2.dismiss();

            }
        });

        try {
            inputSearch2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    adapter2.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });
        }catch (Exception e){}

        dialog2.show();

    }

    HashMap<String,String> slothp=new HashMap<>();
    HashMap<String,String> slothp2=new HashMap<>();
    HashMap<String,String> trtype=new HashMap<>();

    public void prealertslotnumber() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> slotnumberSpinner = new ArrayList<String>();

        for(int i=0;i<jsonslotnumber.length();i++)
        {
            slotnumberSpinner.add(jsonslotnumber.optJSONObject(i).optString("documentno"));
            slothp.put(jsonslotnumber.optJSONObject(i).optString("documentno"),jsonslotnumber.optJSONObject(i).optString("scheduledfromtime"));
            slothp2.put(jsonslotnumber.optJSONObject(i).optString("documentno"),jsonslotnumber.optJSONObject(i).optString("id"));
            trtype.put(jsonslotnumber.optJSONObject(i).optString("documentno"),jsonslotnumber.optJSONObject(i).optString("transactiontype"));

        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, slotnumberSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                PreAlertSlotNo.setText(str);
                PreAlertScheduledInTime.setText(slothp.get(str));
                slotnumberid=slothp2.get(str);
                STransactionType=trtype.get(str);
                TransactionType.setText(STransactionType);
                dialog2.dismiss();

            }
        });

        try {
            inputSearch2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    adapter2.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });
        }catch (Exception e){}

        dialog2.show();

    }

    HashMap<String,String> driverhp=new HashMap<>();
    HashMap<String,String> driverphone=new HashMap<>();
    HashMap<String,String> driverlicense=new HashMap<>();

    public void driver() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> driverSpinner = new ArrayList<String>();

        for(int i=0;i<jsondriver.length();i++)
        {
            driverSpinner.add(jsondriver.optJSONObject(i).optString("name"));
            driverhp.put(jsondriver.optJSONObject(i).optString("name"),jsondriver.optJSONObject(i).optString("id"));
            driverphone.put(jsondriver.optJSONObject(i).optString("name"),jsondriver.optJSONObject(i).optString("phone"));
            driverlicense.put(jsondriver.optJSONObject(i).optString("name"),jsondriver.optJSONObject(i).optString("licenseno"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, driverSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                DriverName.setText(str);
                driverid=driverhp.get(str);
                DriverPhoneNumber.setText(driverphone.get(str));
                DriverLicenseNumber.setText(driverlicense.get(str));
                dialog2.dismiss();

            }
        });

        try {
            inputSearch2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    adapter2.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });
        }catch (Exception e){}

        dialog2.show();

    }

    HashMap<String,String> customernameid=new HashMap<>();

    public void customername() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> customernameSpinner = new ArrayList<String>();

        for(int i=0;i<jsonslotnumber.length();i++)
        {
            customernameSpinner.add(jsonslotnumber.optJSONObject(i).optString("bpartner$_identifier"));
            customernameid.put(jsonslotnumber.optJSONObject(i).optString("bpartner$_identifier"),jsonslotnumber.optJSONObject(i).optString("id"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, customernameSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                CustomerName.setText(str);
                customerid=customernameid.get(str);
                dialog2.dismiss();

            }
        });

        try {
            inputSearch2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    adapter2.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });
        }catch (Exception e){}

        dialog2.show();

    }

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText
    EditText inputSearch2;

    protected void displayTuto() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_firstcontactform)
                .setFitsSystemWindows(true)
                .on(R.id.add)
                .addCircle()
                .withBorder()
                .show();
    }

    protected void displayTutoo() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_gateentry)
                .setFitsSystemWindows(true)
                .on(R.id.list)
                .addRoundRect()
                .withBorder()
                .show();
    }

    public void transactiontypedialogbox() {

        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("Inward");
        Items.add("Outward");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                TransactionType.setText(str);
                dialog2.dismiss();
            }
        });

        try {
            inputSearch2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    adapter2.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });
        }catch (Exception e){}

        dialog2.show();

    }


    private int mYear, mMonth, mDay, mHour, mMinute, mSec;
    String outputDateForServer = null;

    private void getDate() {
        try {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {


                            String date = Integer.toString(dayOfMonth) + "-" + Integer.toString((monthOfYear + 1)) + "-" + Integer.toString(year);
                            Date date12 = null;
                            DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");

                            try {
                                if (!(date.equalsIgnoreCase(null)) && !(date.equalsIgnoreCase("")))
                                    date12 = inputFormat.parse(date);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


// Format date into output format
                            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                            outputString = null;
                            if (date12 != null)
                                outputString = outputFormat.format(date12);

                            DockInTime.setText(outputString);


                            DateFormat outputFormatForServer = new SimpleDateFormat("yyyy-MM-dd");

                            if (date12 != null)
                                outputDateForServer = outputFormatForServer.format(date12);
                        }
                    }, mYear , mMonth, mDay);
            //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse((mDay)+"/"+(mMonth+1)+"/"+(mYear));
            datePickerDialog.getDatePicker().setMinDate((d.getTime()));

            datePickerDialog.show();
        }catch (Exception e){e.printStackTrace();}


    }

    public void getTime()
    {
        calendar = Calendar.getInstance();
        CalendarHour = calendar.get(Calendar.HOUR_OF_DAY);
        CalendarMinute = calendar.get(Calendar.MINUTE);


        timepickerdialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        if (hourOfDay == 0) {

                            hourOfDay += 12;

                            format = "AM";
                        }
                        else if (hourOfDay == 12) {

                            format = "PM";

                        }
                        else if (hourOfDay > 12) {

                            hourOfDay -= 12;

                            format = "PM";

                        }
                        else {

                            format = "AM";
                        }

                        String t=hourOfDay + ":" + minute +" : "+ format;
                        int hour2=hourOfDay;
                        String[] separated = t.split(":");
                        String hour1 = separated[0];
                        if (t.contains("PM") && !hour1.contains("12"))
                        {
                            hour2=Integer.valueOf(hour1)+12;
                        }
                        else if (t.contains("PM") && hour1.contains("12"))
                        {
                            hour2=Integer.valueOf(hour1);
                        }
                        else if (t.contains("AM") && hour1.contains("12"))
                        {
                            hour2=Integer.valueOf(hour1)-12;
                        }
                        String finalt=String.valueOf(hour2)+":"+minute+":00";
                        finall=String.valueOf(hour2)+":"+minute;
                        SDockInTime=outputString+"T"+finalt;
                        System.out.println("dockintime"+SDockInTime);
                        DockInTime.setText(SDockInTime);
                    }
                }, CalendarHour, CalendarMinute, false);
        timepickerdialog.show();

    }
}