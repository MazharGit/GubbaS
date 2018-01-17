package ssg1.gubba1.gubba1.g.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class SlotBooking extends Fragment {

    View view;

    EditText Location,Customername,Slotnumber,TransactionType,PackagingType,ScheduledFromTime,ReScheduledFromTime;
    TextInputLayout LLocation,LCustomername,LSlotnumber,LTransactionType,LPackagingType,LScheduledFromTime,LReScheduledFromTime;
    Button Save;
    String SLocation,SCustomername,SSlotnumber,STransactionType,SPackagingType,SScheduledFromTime,SReScheduledFromTime;
    String RLocation,RCustomername,RSlotnumber,RTransactionType,RPackagingType,RScheduledFromTime,RReScheduledFromTime;

    CallWebService service;

    String orgIdd,customernameid,search;

    FragmentTransaction fragmentTransaction;

    JSONArray jsonArray,jsoncustomernameArray;

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText
    EditText inputSearch2;

    TimePickerDialog timepickerdialog;

    Calendar calendar;

    private int CalendarHour, CalendarMinute;

    String format,id;

    String replace="0";

    public static String bagtype;

    JSONArray jsonslot;

    public static String finall;

    SharedPref sp;


    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_slot_booking, null);

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
        view = inflater.inflate(R.layout.activity_slot_booking, container, false);

        service = new CallWebService(getActivity());
        jsoncustomernameArray=new JSONArray();

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow22").equals("0"))
                {
                    sp.removeData("loginshow22");
                    sp.writeString("loginshow22", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow27").equals("0"))
                {
                    sp.removeData("loginshow27");
                    sp.writeString("loginshow27", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Location= (EditText) view.findViewById(R.id.input_location);
        Customername= (EditText) view.findViewById(R.id.input_customer_name);
        Slotnumber= (EditText) view.findViewById(R.id.input_slot_no);
        TransactionType = (EditText) view.findViewById(R.id.input_transaction_type);
        PackagingType = (EditText) view.findViewById(R.id.input_packaging_type);
        ScheduledFromTime = (EditText) view.findViewById(R.id.input_scheduled_from_time);
        ReScheduledFromTime = (EditText) view.findViewById(R.id.input_rescheduled_from_time);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LLocation = (TextInputLayout) view.findViewById(R.id.input_layout_location);
        LCustomername = (TextInputLayout) view.findViewById(R.id.input_layout_customer_name);
        LSlotnumber = (TextInputLayout) view.findViewById(R.id.input_layout_slot_no);
        LTransactionType = (TextInputLayout) view.findViewById(R.id.input_layout_transaction_type);
        LPackagingType = (TextInputLayout) view.findViewById(R.id.input_layout_packaging_type);
        LScheduledFromTime = (TextInputLayout) view.findViewById(R.id.input_layout_scheduled_from_time);
        LReScheduledFromTime = (TextInputLayout) view.findViewById(R.id.input_layout_rescheduled_from_time);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Location.setTypeface(UbuntuFont);
        Customername.setTypeface(UbuntuFont);
        Slotnumber.setTypeface(UbuntuFont);
        TransactionType.setTypeface(UbuntuFont);
        PackagingType.setTypeface(UbuntuFont);
        ScheduledFromTime.setTypeface(UbuntuFont);
        ReScheduledFromTime.setTypeface(UbuntuFont);

        LLocation.setTypeface(UbuntuFont);
        LCustomername.setTypeface(UbuntuFont);
        LSlotnumber.setTypeface(UbuntuFont);
        LTransactionType.setTypeface(UbuntuFont);
        LPackagingType.setTypeface(UbuntuFont);
        LScheduledFromTime.setTypeface(UbuntuFont);
        LReScheduledFromTime.setTypeface(UbuntuFont);

        try
        {
            orgIdd=getArguments().getString("organizationid");
            RLocation=getArguments().getString("organization");
            RCustomername=getArguments().getString("customername");
            RSlotnumber=getArguments().getString("slotnumber");
            RTransactionType=getArguments().getString("transactiontype");
            RPackagingType=getArguments().getString("packagingtype");
            RScheduledFromTime=getArguments().getString("schedulefromtime");
            RReScheduledFromTime=getArguments().getString("reschedulefromtime");
            replace=getArguments().getString("replace");
            id=getArguments().getString("id");

        }catch (Exception e){}

        Location.setText(RLocation);
        Customername.setText(RCustomername);
        Slotnumber.setText(RSlotnumber);
        TransactionType.setText(RTransactionType);
        PackagingType.setText(RPackagingType);
        ScheduledFromTime.setText(RScheduledFromTime);
        ReScheduledFromTime.setText(RReScheduledFromTime);

        Customername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customernamesbusinesspartner();
            }
        });

        TransactionType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactiontypedialogbox();
            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationDialogBox();
            }
        });

        PackagingType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packagingtypedialogbox();
            }
        });

        Slotnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Slot Number not Editable",Snackbar.LENGTH_LONG).show();
            }
        });

        ScheduledFromTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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
                                ScheduledFromTime.setText(finalt);
                            }
                        }, CalendarHour, CalendarMinute, false);
                timepickerdialog.show();

            }
        });

        ReScheduledFromTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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
                                ReScheduledFromTime.setText(finalt);
                            }
                        }, CalendarHour, CalendarMinute, false);
                timepickerdialog.show();

            }
        });


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SLocation=Location.getText().toString();
                SCustomername=Customername.getText().toString();
                SSlotnumber=Slotnumber.getText().toString();
                STransactionType=TransactionType.getText().toString();
                SPackagingType=PackagingType.getText().toString();
                SScheduledFromTime=ScheduledFromTime.getText().toString();
                SReScheduledFromTime=ReScheduledFromTime.getText().toString();
                bagtype=SPackagingType;

                if(TextUtils.isEmpty(SLocation)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Location is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCustomername)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Customer Name is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(STransactionType)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Transaction Type is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SPackagingType)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Packaging Type is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SScheduledFromTime)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Scheduled From Time is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SLocation)) && !(TextUtils.isEmpty(SCustomername)) && !(TextUtils.isEmpty(STransactionType)) && !(TextUtils.isEmpty(SPackagingType)) && !(TextUtils.isEmpty(SScheduledFromTime)))
                {

                    Home.slotbooking_url = "sqt_prealert?" + new Constants().USER_N_PASSWORD;
                    if (replace.equalsIgnoreCase("0"))
                    {
                        JSONObject jsonObject = new JSONObject();

                        try {
                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("bpartner", customernameid)
                                    .put("transactiontype", STransactionType)
                                    .put("bagtype", SPackagingType)
                                    .put("scheduledfromtime", SScheduledFromTime)
                                    .put("rescheduledfromtime", SReScheduledFromTime));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.slotbooking_url, jsonObject, true);

                    }
                    else
                    {
                        JSONObject jsonObject = new JSONObject();

                        try {
                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("bpartner", customernameid)
                                    .put("transactiontype", STransactionType)
                                    .put("bagtype", SPackagingType)
                                    .put("id",id)
                                    .put("scheduledfromtime", SScheduledFromTime)
                                    .put("rescheduledfromtime", SReScheduledFromTime));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        replace="0";
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.slotbooking_url, jsonObject, true);

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
                Location.setText("");
                Customername.setText("");
                Slotnumber.setText("");
                TransactionType.setText("");
                PackagingType.setText("");
                ScheduledFromTime.setText("");
                ReScheduledFromTime.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Fragment fragment = new SlotBookingList();
                Home.slotBookingList = (SlotBookingList) fragment;
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

    private void customernamerequest() throws Exception {

        Home.getcustomernamerecords_url = "BusinessPartner?"+new Constants().USER_N_PASSWORD+"&_where=businessPartnerCategory=%27EB88C45CD32A4F138B1C4C8F9CB92FA5%27%20and%20name%20like%20%27%25"+search+"%25%27";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.getcustomernamerecords_url, true);

    }

    public void customernameresponse(JSONObject json){
        try {
            jsoncustomernameArray=json.optJSONObject("response").optJSONArray("data");
            System.out.println("jsoncustomernameArray"+jsoncustomernameArray);

            smallMethod();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void slotbookingresponse(JSONObject json2){
        jsonslot=json2.optJSONObject("response").optJSONArray("data");
        System.out.println("coming"+jsonslot);
        try {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
            builder.setMessage("Successfully Inserted into ERP\n\n");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    {
                        Location.setText("");
                        Customername.setText("");
                        Slotnumber.setText("");
                        TransactionType.setText("");
                        PackagingType.setText("");
                        ScheduledFromTime.setText("");
                        ReScheduledFromTime.setText("");
                    }

                    dialog.cancel();
                }
            });

            builder.setNegativeButton("Product", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    {
                        Bundle args = new Bundle();
                        args.putString("productid", jsonslot.optJSONObject(0).optString("id"));
                        args.putString("organization", jsonslot.optJSONObject(0).optString("organization$_identifier"));
                        args.putString("organizationid", jsonslot.optJSONObject(0).optString("organization"));
                        args.putString("replace", "0");
                        args.putString("fromtime", jsonslot.optJSONObject(0).optString("scheduledfromtime"));
                        args.putString("updateid", jsonslot.optJSONObject(0).optString("id"));


                        SlotBookingProduct fragment = new SlotBookingProduct();
                        Home.slotBookingProduct = (SlotBookingProduct) fragment;
                        fragment.setArguments(args);
                        FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                        fragmentTransaction.commitAllowingStateLoss();
                        ((Activity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                    }

                    dialog.cancel();
                }
            });

            builder.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
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

    HashMap<String,String> customernameHp=new HashMap<>();

    public void customernamesbusinesspartner() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> locationSpinner = new ArrayList<String>();

                        for(int i=0;i<jsoncustomernameArray.length();i++)
                        {
                            locationSpinner.add(jsoncustomernameArray.optJSONObject(i).optString("name"));
                            customernameHp.put(jsoncustomernameArray.optJSONObject(i).optString("name"),jsoncustomernameArray.optJSONObject(i).optString("id"));
                        }

                        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, locationSpinner);
                        lv2.setAdapter(adapter2);
                        lv2.setClickable(true);
                        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                                Object o = lv2.getItemAtPosition(position);
                                String str=(String)o;//As you are using Default String Adapter
                                Customername.setText(str);
                                customernameid=customernameHp.get(str);
                                dialog2.dismiss();

            }
        });

        try {
            inputSearch2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    search=String.valueOf(cs).toUpperCase();
                    try {
                        customernamerequest();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    public void smallMethod()
    {
        ArrayList<String> locationSpinner = new ArrayList<String>();

        for(int i=0;i<jsoncustomernameArray.length();i++)
        {
            locationSpinner.add(jsoncustomernameArray.optJSONObject(i).optString("name"));
            customernameHp.put(jsoncustomernameArray.optJSONObject(i).optString("name"),jsoncustomernameArray.optJSONObject(i).optString("id"));
        }
        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, locationSpinner);
        lv2.setAdapter(adapter2);
    }

    public void packagingtypedialogbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("Small Bag");
        Items.add("Jumbo Bag");
        Items.add("Crates");
        Items.add("Carton Box");
        Items.add("Barrel");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                PackagingType.setText(str);
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

    protected void displayTuto() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_firstcontactform)
                .setFitsSystemWindows(true)
                .on(R.id.add)
                .addRoundRect()
                .withBorder()
                .show();
    }

    protected void displayTutoo() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_slotbooking)
                .setFitsSystemWindows(true)
                .on(R.id.list)
                .addRoundRect()
                .withBorder()
                .show();
    }

}