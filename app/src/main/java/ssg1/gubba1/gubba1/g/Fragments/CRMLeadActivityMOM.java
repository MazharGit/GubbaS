package ssg1.gubba1.gubba1.g.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import ssg1.gubba1.gubba1.g.DataValidation;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class CRMLeadActivityMOM extends Fragment implements DatePickerDialog.OnDateSetListener {

    View view;


    EditText AssignedTo,DueDate,Task,Type;
    Button Confirm;
    String SAssignedTo,SDueDate,STask,SType;
    String SSAssignedTo,SSDueDate,SSTask,SSType;

    FragmentTransaction fragmentTransaction;

    String bpName,bpId,userId,orgIdd;

    CallWebService service;
    JSONArray jsonArray;

    String replaceId,momId;

    JSONArray jsonMomAllRecords;

    Calendar mCurrentDate;

    int day,month,year;

    String activityId;
    public String totalDate;

    int part1,part2,part3;
    int check1,check2,check3;

    String replace="0";

    SharedPref sp;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.fragment_crmleadactivitymom, null);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            setHasOptionsMenu(true);
            view = inflater.inflate(R.layout.fragment_crmleadactivitymom, container, false);

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow21").equals("0"))
                {
                    sp.removeData("loginshow21");
                    sp.writeString("loginshow21", "0");

                    displayTuto();

                }
            }
        }, 500);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow2").equals("0"))
                {
                    sp.removeData("loginshow2");
                    sp.writeString("loginshow2", "0");

                    displayTutoo();

                }
            }
        }, 10000);*/

            service = new CallWebService(getActivity());

            jsonMomAllRecords=new JSONArray();

            mCurrentDate=Calendar.getInstance();
            day=mCurrentDate.get(Calendar.DAY_OF_MONTH);
            month=mCurrentDate.get(Calendar.MONTH)+1;
            year=mCurrentDate.get(Calendar.YEAR);
            

            AssignedTo = (EditText) view.findViewById(R.id.input_assignedto);
            DueDate = (EditText) view.findViewById(R.id.input_duedate);
            Task = (EditText) view.findViewById(R.id.input_task);
            Type = (EditText) view.findViewById(R.id.input_type);
            Confirm=(Button) view.findViewById(R.id.confirm);

            CRMLeadActivityMOMList crmLeadActivityMOMList=new CRMLeadActivityMOMList();
            activityId=crmLeadActivityMOMList.activityId;

            try{
                Home.mToolbar.setTitle(" ");
            }catch (Exception e){}

            TextInputLayout LAssignedTo = (TextInputLayout) view.findViewById(R.id.input_layout_assignedto);
            TextInputLayout LDueDate = (TextInputLayout) view.findViewById(R.id.input_layout_duedate);
            TextInputLayout LTask = (TextInputLayout) view.findViewById(R.id.input_layout_task);
            TextInputLayout LType = (TextInputLayout) view.findViewById(R.id.input_layout_type);

            Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


            AssignedTo.setTypeface(UbuntuFont);
            DueDate.setTypeface(UbuntuFont);
            Task.setTypeface(UbuntuFont);
            Type.setTypeface(UbuntuFont);

            LAssignedTo.setTypeface(UbuntuFont);
            LDueDate.setTypeface(UbuntuFont);
            LTask.setTypeface(UbuntuFont);
            LType.setTypeface(UbuntuFont);

        try {
            SSAssignedTo=getArguments().getString("assignedto");
            SSDueDate=getArguments().getString("duedate");
            SSTask=getArguments().getString("task");
            SSType=getArguments().getString("tasktype");
            momId=getArguments().getString("momId");
            userId=getArguments().getString("userid");
            String strDatee = SSDueDate;
            String[] check = strDatee.split("-");
            part1 = Integer.valueOf(check[0]);
            part2 = Integer.valueOf(check[1]);
            part3 = Integer.valueOf(check[2]);
            replace=getArguments().getString("replace");

        }catch (Exception e){}

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = mdformat.format(calendar.getTime());
        String[] check = strDate.split("-");
        check1 = Integer.valueOf(check[0]);
        check2 = Integer.valueOf(check[1]);
        check3 = Integer.valueOf(check[2]);// 004

        AssignedTo.setText(SSAssignedTo);
        DueDate.setText(SSDueDate);
        Task.setText(SSTask);
        Type.setText(SSType);

            AssignedTo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assignedToDilagbox();
                }
            });

        Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeDilagbox();
            }
        });

        DueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                        monthofyear=monthofyear+1;
                        totalDate=year+"-"+monthofyear+"-"+dayofmonth;
                        String string = totalDate;
                        String[] parts = string.split("-");
                        part1 = Integer.valueOf(parts[0]);
                        part2 = Integer.valueOf(parts[1]);
                        part3 = Integer.valueOf(parts[2]);

                        DueDate.setText(totalDate);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SAssignedTo=AssignedTo.getText().toString();
                    SDueDate=DueDate.getText().toString();
                    STask=Task.getText().toString();
                    SType=Type.getText().toString();

                    if(TextUtils.isEmpty(SAssignedTo)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Assigned To is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if(DataValidation.isNullString(SDueDate)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Due Date is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if (!(part1>=check1) && !(part1>=check2) && !(part1>=check3))
                    {
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Due Date is Should Greater than Today's Date ",Snackbar.LENGTH_LONG).show();
                        DueDate.setError("Due Date is Should Greater than Today's Date");
                    }
                    else if(DataValidation.isNullString(STask)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Task is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if(DataValidation.isNullString(SType)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Type is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if(!(TextUtils.isEmpty(SAssignedTo)) && !(TextUtils.isEmpty(SDueDate)) && !(TextUtils.isEmpty(STask)) && !(TextUtils.isEmpty(SType)))
                    {
                        Home.crmLeadActivitymomsending_url = "GCRM_Mom?" + new Constants().USER_N_PASSWORD;
                        JSONObject jsonObject = new JSONObject();

                        try {

                            if (replace.equalsIgnoreCase("0"))
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("task", STask)
                                        .put("user", userId)
                                        .put("duedate", totalDate)
                                        .put("tasktype", SType)
                                        .put("gcrmActivity",activityId));
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadActivitymomsending_url, jsonObject, true);

                            }
                            else
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("task", STask)
                                        .put("user", userId)
                                        .put("duedate", totalDate)
                                        .put("tasktype", SType)
                                        .put("id",replaceId)
                                        .put("gcrmActivity",activityId));
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadActivitymomsending_url, jsonObject, true);

                                replace="0";

                            }

                        }catch (Exception e){}

                    }

                }
            });
            return view;
    }

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText
    EditText inputSearch2;

    HashMap<String,String> assignedToHp=new HashMap<>();
    HashMap<String,String> assignedToHp2=new HashMap<>();
    HashMap<String,String> assignedToHp3=new HashMap<>();

    public void assignedToDilagbox()
    {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> assignedToSpinner = new ArrayList<String>();
        JSONObject jsonObject= Constants.jsonLoginResponse;
        jsonArray=jsonObject.optJSONObject("response").optJSONObject("data").optJSONArray("Users");

        for(int i=0;i<jsonArray.length();i++)
        {
            assignedToSpinner.add(jsonArray.optJSONObject(i).optString("userName"));
            assignedToHp.put(jsonArray.optJSONObject(i).optString("userName"),jsonArray.optJSONObject(i).optString("bpName"));
            assignedToHp2.put(jsonArray.optJSONObject(i).optString("userName"),jsonArray.optJSONObject(i).optString("bpId"));
            assignedToHp3.put(jsonArray.optJSONObject(i).optString("userName"),jsonArray.optJSONObject(i).optString("userId"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, assignedToSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                AssignedTo.setText(str);
                bpName=assignedToHp.get(str);
                bpId=assignedToHp2.get(str);
                userId=assignedToHp3.get(str);

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

    public void typeDilagbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("Complaint");
        Items.add("Feedback");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Type.setText(str);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.add, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:

                AssignedTo.setText("");
                DueDate.setText("");
                Task.setText("");
                Type.setText("");

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void momAllRecords(JSONObject json){
        try {
            jsonMomAllRecords=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finalresponse(JSONObject json){

        try {

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n");

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {

                        CRMLeadActivityMOMList fragment = new CRMLeadActivityMOMList();
                        Home.crmLeadActivityMOMList = (CRMLeadActivityMOMList) fragment;
                        FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                        fragmentTransaction.commitAllowingStateLoss();
                        ((Activity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                    }catch (Exception e){
                    }
                }
            });

            builder.show();

        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

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

    /*protected void displayTutoo() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_firstcontactform9)
                .setFitsSystemWindows(true)
                .on(R.id.list)
                .addRoundRect()
                .withBorder()
                .show();
    }*/

}
