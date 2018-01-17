package ssg1.gubba1.gubba1.g.Fragments;

import android.app.AlertDialog;
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

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class CRMLead extends Fragment {

    View view;

    EditText Location,SearchKey,CompanyName,AssignedTo,FirstContactID,BusinessPartnerID,Priority;
    TextInputLayout LLocation,LSearchKey,LCompanyName,LAssignedTo,LFirstContactID,LBusinessPartnerID,LPriority;
    Button Save;
    String SLocation,SSearchKey,SCompanyName,SAssignedTo,SFirstContactID,SBusinessPartnerID,SPriority;
    String RLocation,RSearchKey,RCompanyName,RAssignedTo,RFirstContactID,RBusinessPartnerID,RPriority,id;

    JSONArray jsonArray;

    JSONArray jsonAllRecords;

    CallWebService service;

    String bpName,bpId,userId,orgIdd;

    FragmentTransaction fragmentTransaction;

    String cn,fi,lo,pr,firstContactIdd;

    String replace="0";

    SharedPref sp;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_crmlead, null);

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
        view = inflater.inflate(R.layout.activity_crmlead, container, false);

        service = new CallWebService(getActivity());

        jsonAllRecords=new JSONArray();

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow4").equals("0"))
                {
                    sp.removeData("loginshow4");
                    sp.writeString("loginshow4", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow5").equals("0"))
                {
                    sp.removeData("loginshow5");
                    sp.writeString("loginshow5", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Location= (EditText) view.findViewById(R.id.input_location);
        SearchKey = (EditText) view.findViewById(R.id.input_searchkey);
        CompanyName = (EditText) view.findViewById(R.id.input_companyname);
        AssignedTo = (EditText) view.findViewById(R.id.input_assignedto);
        FirstContactID = (EditText) view.findViewById(R.id.input_firstcontactid);
        BusinessPartnerID = (EditText) view.findViewById(R.id.input_businesspartnerid);
        Priority = (EditText) view.findViewById(R.id.input_priority);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LLocation = (TextInputLayout) view.findViewById(R.id.input_layout_location);
        LSearchKey = (TextInputLayout) view.findViewById(R.id.input_layout_searchkey);
        LCompanyName = (TextInputLayout) view.findViewById(R.id.input_layout_companyname);
        LAssignedTo = (TextInputLayout) view.findViewById(R.id.input_layout_assignedto);
        LFirstContactID = (TextInputLayout) view.findViewById(R.id.input_layout_firstcontactid);
        LBusinessPartnerID = (TextInputLayout) view.findViewById(R.id.input_layout_businesspartnerid);
        LPriority = (TextInputLayout) view.findViewById(R.id.input_layout_priority);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Location.setTypeface(UbuntuFont);
        SearchKey.setTypeface(UbuntuFont);
        CompanyName.setTypeface(UbuntuFont);
        AssignedTo.setTypeface(UbuntuFont);
        FirstContactID.setTypeface(UbuntuFont);
        BusinessPartnerID.setTypeface(UbuntuFont);
        Priority.setTypeface(UbuntuFont);

        LLocation.setTypeface(UbuntuFont);
        LSearchKey.setTypeface(UbuntuFont);
        LCompanyName.setTypeface(UbuntuFont);
        LAssignedTo.setTypeface(UbuntuFont);
        LFirstContactID.setTypeface(UbuntuFont);
        LBusinessPartnerID.setTypeface(UbuntuFont);
        LPriority.setTypeface(UbuntuFont);

        SearchKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Search Key is Not Editable", Snackbar.LENGTH_LONG).show();

            }
        });

        FirstContactID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "First Contact Id is Not Editable", Snackbar.LENGTH_LONG).show();
            }
        });

        BusinessPartnerID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Business Partner Id is Not Editable", Snackbar.LENGTH_LONG).show();
            }
        });


        AssignedTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignedToDialogBox();
            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationDialogBox();
            }
        });

        try
        {
            cn=getArguments().getString("companyname");
            fi=getArguments().getString("documentno");
            lo=getArguments().getString("location");
            orgIdd=getArguments().getString("locationid");
            pr=getArguments().getString("priority");
            firstContactIdd=getArguments().getString("documentnoid");
            replace=getArguments().getString("replace");
        }catch (Exception e){}

        try
        {
            if (getArguments().getString("iam").equals("1")==true)
            {
                CompanyName.setText(cn);
                FirstContactID.setText(fi);
            }
        }catch (Exception e){}

        try {
            RLocation=getArguments().getString("location");
            RSearchKey = getArguments().getString("searchkey");
            RCompanyName=getArguments().getString("companyname");
            RAssignedTo = getArguments().getString("assignedto");
            RFirstContactID=getArguments().getString("fcid");
            RBusinessPartnerID = getArguments().getString("bpid");
            RPriority=getArguments().getString("priority");
            orgIdd=getArguments().getString("locationid");
            userId=getArguments().getString("assignedtoo");;
            id=getArguments().getString("id");
            firstContactIdd=getArguments().getString("documentnoid");
            if (RBusinessPartnerID.equals("null"))
            {
                bpId="";
            }
            else
            {
                bpId=RBusinessPartnerID;
            }

        }catch (Exception e){}

                Location.setText(RLocation);
                SearchKey.setText(RSearchKey);
                CompanyName.setText(RCompanyName);
                AssignedTo.setText(RAssignedTo);
                if (TextUtils.isEmpty(FirstContactID.getText()))
                {
                    FirstContactID.setText(RFirstContactID);
                }
                BusinessPartnerID.setText(RBusinessPartnerID);
                Priority.setText(RPriority);

        Priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDilagbox();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SLocation=Location.getText().toString();
                SSearchKey=SearchKey.getText().toString();
                SCompanyName=CompanyName.getText().toString();
                SAssignedTo=AssignedTo.getText().toString();
                SFirstContactID=FirstContactID.getText().toString();
                SBusinessPartnerID=BusinessPartnerID.getText().toString();
                SPriority=Priority.getText().toString();
                if(TextUtils.isEmpty(SLocation)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Location is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SPriority)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Priority is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SLocation)) && !(TextUtils.isEmpty(SPriority)))
                {

                    Home.crmLead_url = "GCRM_Lead?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    try {

                        if (replace.equalsIgnoreCase("0"))
                        {
                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("searchkey", SSearchKey)
                                    .put("companyname", SCompanyName)
                                    .put("user", userId)
                                    .put("gcrmContact", firstContactIdd)
                                    .put("bpartner", bpId)
                                    .put("priority",SPriority));
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLead_url, jsonObject, true);

                        }
                        else
                        {
                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("searchkey", SSearchKey)
                                    .put("companyname", SCompanyName)
                                    .put("user", userId)
                                    .put("gcrmContact", firstContactIdd)
                                    .put("bpartner", bpId)
                                    .put("priority",SPriority)
                                    .put("id",id));
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLead_url, jsonObject, true);

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
                Location.setText("");
                AssignedTo.setText("");
                Priority.setText("");
                CompanyName.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Bundle args = new Bundle();
                Fragment fragment = new CRMLeadList();
                Home.crmLeadList = (CRMLeadList) fragment;
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                fragmentTransaction.commitAllowingStateLoss();
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                return true;
            }

            /*case R.id.save: {
                SLocation = Location.getText().toString();
                SSearchKey = SearchKey.getText().toString();
                SCompanyName = CompanyName.getText().toString();
                SAssignedTo = AssignedTo.getText().toString();
                SFirstContactID = FirstContactID.getText().toString();
                SBusinessPartnerID = BusinessPartnerID.getText().toString();
                SPriority = Priority.getText().toString();
                if (TextUtils.isEmpty(SAssignedTo)) {
                    LAssignedTo.setError("Assigned To Field is Mandatory");
                }
                if (TextUtils.isEmpty(SLocation)) {
                    LLocation.setError("Location Field is Mandatory");
                }
                if (TextUtils.isEmpty(SPriority)) {
                    LPriority.setError("Priority Field is Mandatory");
                }
                if ((TextUtils.isEmpty(SAssignedTo)) || (TextUtils.isEmpty(SLocation)) || (TextUtils.isEmpty(SPriority))) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Fill Up All Mandatory Fields", Snackbar.LENGTH_LONG).show();
                }
                if (!(TextUtils.isEmpty(SAssignedTo)) && !(TextUtils.isEmpty(SLocation)) && !(TextUtils.isEmpty(SPriority))) {

                    Home.crmLead_url = "GCRM_Lead?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("data", new JSONObject()
                                .put("organization", orgIdd)
                                .put("searchkey", SSearchKey)
                                .put("companyname", SCompanyName)
                                .put("user", userId)
                                .put("gcrmContact$_identifier", SFirstContactID)
                                .put("bpartner", bpId)
                                .put("priority", SPriority));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                        *//*create=1;*//*
                    service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLead_url, jsonObject, true);

                }
            }*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    HashMap<String,String> assignedToHp=new HashMap<>();
    HashMap<String,String> assignedToHp2=new HashMap<>();
    HashMap<String,String> assignedToHp3=new HashMap<>();

    public void assignedToDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> assignedToSpinner = new ArrayList<String>();
        JSONObject jsonObject=Constants.jsonLoginResponse;
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
                if (bpId.equals("null"))
                {
                    bpId="";
                }
                userId=assignedToHp3.get(str);

                try {
                    if (bpName.equals(CompanyName.getText().toString()))
                    {
                        BusinessPartnerID.setText(bpId);
                    }
                }catch (Exception e){}

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

    public void crmLeadAllRecordsResponse(JSONObject json){
        try {

            jsonAllRecords = json.optJSONObject("response").optJSONArray("data");
        }catch (Exception e){}
    }

    public void createLeadResponse(JSONObject json){
        try {

            String searchKey=json.optJSONObject("response").optJSONArray("data").optJSONObject(0).optString("searchkey");

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
            builder.setMessage("Successfully Inserted into ERP\n\nSearch Key :"+searchKey+"\n\n");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                {
                    Location.setText("");
                    SearchKey.setText("");
                    CompanyName.setText("");
                    AssignedTo.setText("");
                    FirstContactID.setText("");
                    BusinessPartnerID.setText("");
                    Priority.setText("");
                }

                    dialog.cancel();
                }
            });

            builder.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText
    EditText inputSearch2;

    public void productsDilagbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("Hot");
        Items.add("Warm");
        Items.add("Cold");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Priority.setText(str);
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
                .addCircle()
                .withBorder()
                .show();
    }

    protected void displayTutoo() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_crmlead)
                .setFitsSystemWindows(true)
                .on(R.id.list)
                .addRoundRect()
                .withBorder()
                .show();
    }

}
