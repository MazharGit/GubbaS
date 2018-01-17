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

public class FirstContactForm extends Fragment {

    View view;


    EditText DocumentNo,Location,CompanyName,ContactName,ContactNo,Email,City,RequirementDetails,Priority;
    Button CreateLead;
    String SDocumentNo,SLocation,SCompanyName,SContactName,SContactNo,SEmail,SCity,SRequirementDetails,SPriority;

    String FLocation,FFLocation,FCompanyName,FContactName,FContactNo,FEmail,FCity,FRequirementDetails,FPriority,FId;

    JSONArray jsonAllRecords;

    String no;

    JSONArray jsonArray;

    FragmentTransaction fragmentTransaction;
    CallWebService service;

    TextInputLayout LDocumentNo,LLocation,LCompanyName,LContactName,LContactNo,LEmail,LCity,LRequirementDetails,LPriority;

    String orgIdd;

    String replace="0";

    SharedPref sp;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.fragment_first_fragment_form, null);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_first_fragment_form, container, false);

            service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow").equals("0"))
                {
                    sp.removeData("loginshow");
                    sp.writeString("loginshow", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow2").equals("0"))
                {
                    sp.removeData("loginshow2");
                    sp.writeString("loginshow2", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        try {
                SDocumentNo=getArguments().getString("documentno");
                FLocation=getArguments().getString("organization");
                FFLocation=getArguments().getString("organizationid");
                orgIdd=FFLocation;
                FCompanyName=getArguments().getString("companyname");
                FContactName=getArguments().getString("contactname");
                FContactNo=getArguments().getString("phonenumber");
                FEmail=getArguments().getString("email");
                FCity=getArguments().getString("cityname");
                FRequirementDetails=getArguments().getString("descprition");
                FPriority=getArguments().getString("priority");
                FId=getArguments().getString("id");
                replace=getArguments().getString("replace");

            }catch (Exception e){}

            DocumentNo = (EditText) view.findViewById(R.id.input_documentno);
            Location=(EditText) view.findViewById(R.id.input_location);
            CompanyName = (EditText) view.findViewById(R.id.input_companyname);
            ContactName = (EditText) view.findViewById(R.id.input_contactname);
            ContactNo = (EditText) view.findViewById(R.id.input_contactno);
            Email = (EditText) view.findViewById(R.id.input_email);
            City = (EditText) view.findViewById(R.id.input_city);
            RequirementDetails = (EditText) view.findViewById(R.id.input_requirementdetails);
            Priority = (EditText) view.findViewById(R.id.input_priority);
            CreateLead=(Button) view.findViewById(R.id.button) ;

            DocumentNo.setText(SDocumentNo);
            Location.setText(FLocation);
            CompanyName.setText(FCompanyName);
            ContactName.setText(FContactName);
            ContactNo.setText(FContactNo);
            Email.setText(FEmail);
            City.setText(FCity);
            RequirementDetails.setText(FRequirementDetails);
            Priority.setText(FPriority);

            try{
                Home.mToolbar.setTitle(" ");
            }catch (Exception e){}

            LDocumentNo = (TextInputLayout) view.findViewById(R.id.input_layout_documentno);
            LLocation =(TextInputLayout) view.findViewById(R.id.input_layout_location);
            LCompanyName = (TextInputLayout) view.findViewById(R.id.input_layout_companyname);
            LContactName = (TextInputLayout) view.findViewById(R.id.input_layout_contactname);
            LContactNo = (TextInputLayout) view.findViewById(R.id.input_layout_contactno);
            LEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
            LCity = (TextInputLayout) view.findViewById(R.id.input_layout_city);
            LRequirementDetails = (TextInputLayout) view.findViewById(R.id.input_layout_requirementdetails);
            LPriority = (TextInputLayout) view.findViewById(R.id.input_layout_priority);

            Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


            CreateLead.setTypeface(UbuntuFont);
            DocumentNo.setTypeface(UbuntuFont);
            Location.setTypeface(UbuntuFont);
            CompanyName.setTypeface(UbuntuFont);
            ContactName.setTypeface(UbuntuFont);
            ContactNo.setTypeface(UbuntuFont);
            Email.setTypeface(UbuntuFont);
            City.setTypeface(UbuntuFont);
            RequirementDetails.setTypeface(UbuntuFont);
            Priority.setTypeface(UbuntuFont);

            LDocumentNo.setTypeface(UbuntuFont);
            LLocation.setTypeface(UbuntuFont);
            LCompanyName.setTypeface(UbuntuFont);
            LContactName.setTypeface(UbuntuFont);
            LContactNo.setTypeface(UbuntuFont);
            LEmail.setTypeface(UbuntuFont);
            LCity.setTypeface(UbuntuFont);
            LRequirementDetails.setTypeface(UbuntuFont);
            LPriority.setTypeface(UbuntuFont);

            DocumentNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*showwithanimation(view);*/

                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Document Number is Not Editable",Snackbar.LENGTH_LONG).show();

                    jsonAllRecords=new JSONArray();

                }
            });

            Priority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productsDilagbox();
                }
            });

            Location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        locationDialogBox();
                }
            });

            CreateLead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SLocation=orgIdd;
                    SCompanyName=CompanyName.getText().toString();
                    SContactName=ContactName.getText().toString();
                    SContactNo=ContactNo.getText().toString();
                    SEmail=Email.getText().toString();
                    SCity=City.getText().toString();
                    SRequirementDetails=RequirementDetails.getText().toString();
                    SPriority=Priority.getText().toString();

                    if(TextUtils.isEmpty(SLocation)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Location is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if(TextUtils.isEmpty(SCompanyName)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Company Name is Mandatory",Snackbar.LENGTH_LONG).show();
                        CompanyName.setError("Company Name is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SContactName)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact Name is Mandatory",Snackbar.LENGTH_LONG).show();
                        ContactName.setError("Contact Name is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SContactNo)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact number is Mandatory",Snackbar.LENGTH_LONG).show();
                        ContactNo.setError("Contact Number is Mandatory");
                    }
                    else if(SContactNo.length()!=10){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact Number should be 10 digits",Snackbar.LENGTH_LONG).show();
                        ContactNo.setError("Contact Number should be 10 digits");
                    }
                    else if(TextUtils.isEmpty(SEmail)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Email is Mandatory",Snackbar.LENGTH_LONG).show();
                        Email.setError("Email is Mandatory");
                    }
                    else if(!SEmail.contains("@")){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Enter Valid Email",Snackbar.LENGTH_LONG).show();
                        Email.setError("Enter Valid Email");
                    }
                    else if(TextUtils.isEmpty(SCity)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"City is Mandatory",Snackbar.LENGTH_LONG).show();
                        City.setError("City is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SRequirementDetails)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Requirement details is Mandatory",Snackbar.LENGTH_LONG).show();
                        RequirementDetails.setError("Requirement details is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SPriority)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Priority is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if (!(TextUtils.isEmpty(SLocation)) && !(TextUtils.isEmpty(SCompanyName)) && !(TextUtils.isEmpty(SContactName)) && !(TextUtils.isEmpty(SContactNo)) && (!(TextUtils.isEmpty(SContactNo)) && SContactNo.length()==10) && !(TextUtils.isEmpty(SEmail)) && !(TextUtils.isEmpty(SCity)) && !(TextUtils.isEmpty(SRequirementDetails)) && !(TextUtils.isEmpty(SPriority)))
                    {
                        Home.firstContactForm_url = "GCRM_Firstcontactform?" + new Constants().USER_N_PASSWORD;
                        JSONObject jsonObject = new JSONObject();

                        try {

                            if (replace.equalsIgnoreCase("0"))
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("organization", SLocation)
                                        .put("companyname", SCompanyName)
                                        .put("contactname", SContactName)
                                        .put("phonenumber", SContactNo)
                                        .put("email",SEmail)
                                        .put("cityname", SCity)
                                        .put("descprition",SRequirementDetails)
                                        .put("priority",SPriority)/*
                                        .put("leadcreatecheck", true)*/);
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.firstContactForm_url, jsonObject, true);

                            }
                            else
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("organization", SLocation)
                                        .put("companyname", SCompanyName)
                                        .put("contactname", SContactName)
                                        .put("phonenumber", SContactNo)
                                        .put("email",SEmail)
                                        .put("cityname", SCity)
                                        .put("descprition",SRequirementDetails)
                                        .put("priority",SPriority)/*
                                        .put("leadcreatecheck", true)*/
                                        .put("id",FId));
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.firstContactForm_url, jsonObject, true);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }
            });
            return view;
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
                .setContentView(R.layout.tuto_showcase_firstcontactform9)
                .setFitsSystemWindows(true)
                .on(R.id.list)
                .addRoundRect()
                .withBorder()
                .show();
    }

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;
    HashMap<String,String> locationHp=new HashMap<>();

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.addbutton, menu);

    }

    public void firstContactFormRespense(JSONObject json){
        try {

            final JSONArray jsonArray=json.optJSONObject("response").optJSONArray("data");
            no=jsonArray.getJSONObject(0).optString("documentno");
            int noo=Integer.valueOf(no);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(false);
            builder.setMessage("Successfully Inserted into ERP\n\nDocument Number :"+String.valueOf(noo));
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DocumentNo.setText("");
                    Location.setText("");
                    CompanyName.setText("");
                    ContactName.setText("");
                    ContactNo.setText("");
                    Email.setText("");
                    City.setText("");
                    RequirementDetails.setText("");
                    Priority.setText("");
                }
            });

            /*builder.setNegativeButton("Cread Lead", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Bundle args = new Bundle();
                    try {
                        args.putString("companyname", jsonArray.getJSONObject(0).optString("companyname"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        args.putString("firstcontactid", jsonArray.getJSONObject(0).optString("documentno"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Fragment fragment = new CRMLead();
                    Home.crmLead = (CRMLead) fragment;
                    fragment.setArguments(args);
                    FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                    DocumentNo.setText("");
                    Location.setText("");
                    CompanyName.setText("");
                    ContactName.setText("");
                    ContactNo.setText("");
                    Email.setText("");
                    City.setText("");
                    RequirementDetails.setText("");
                    Priority.setText("");
                }
            });*/

            builder.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void firstContactFormSaveRespense(JSONObject json){
        try {

            JSONArray jsonArray=json.optJSONObject("response").optJSONArray("data");
            no=jsonArray.getJSONObject(0).optString("documentno");
            int noo=Integer.valueOf(no);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
            builder.setMessage("Successfully Inserted into ERP\n\nDocument Number :"+String.valueOf(noo));
            builder.setCancelable(false);
            builder.setPositiveButton("New", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DocumentNo.setText("");
                    Location.setText("");
                    CompanyName.setText("");
                    ContactName.setText("");
                    ContactNo.setText("");
                    Email.setText("");
                    City.setText("");
                    RequirementDetails.setText("");
                    Priority.setText("");
                }
            });

            *//*builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    *//**//*Toast.makeText(getActivity(), "coming cancel", Toast.LENGTH_SHORT).show();
                    Bundle args = new Bundle();
                    Fragment fragment = new FirstContactFormList();
                    Home.firstContactFormList = (FirstContactFormList) fragment;
                    fragment.setArguments(args);
                    FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);*//**//*

                    Bundle args = new Bundle();
                    Fragment fragment = new FirstContactFormList();
                    Home.firstContactFormList = (FirstContactFormList) fragment;
                    fragment.setArguments(args);
                    FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                    Toast.makeText(getActivity(), "cancel working now", Toast.LENGTH_SHORT).show();

                }
            });
*//*
            builder.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void firstContactFormAllRecordsResponse(JSONObject json){
        try {

            jsonAllRecords = json.optJSONObject("response").optJSONArray("data");
        }catch (Exception e){}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add: {

                Location.setText("");
                CompanyName.setText("");
                ContactName.setText("");
                ContactNo.setText("");
                Email.setText("");
                City.setText("");
                RequirementDetails.setText("");
                Priority.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Bundle args = new Bundle();
                Fragment fragment = new FirstContactFormList();
                Home.firstContactFormList = (FirstContactFormList) fragment;
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                fragmentTransaction.commitAllowingStateLoss();
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                return true;
            }

            /*case R.id.save: {

                SLocation = orgIdd;
                SCompanyName = CompanyName.getText().toString();
                SContactName = ContactName.getText().toString();
                SContactNo = ContactNo.getText().toString();
                SEmail = Email.getText().toString();
                SCity = City.getText().toString();
                SRequirementDetails = RequirementDetails.getText().toString();
                SPriority = Priority.getText().toString();

                Home.firstContactFormSaved_url = "GCRM_Firstcontactform?" + new Constants().USER_N_PASSWORD;
                JSONObject jsonObject = new JSONObject();

                if(TextUtils.isEmpty(SLocation)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Location is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCompanyName)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Company Name is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SContactName)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact Name is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SContactNo)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(SContactNo.length()!=10){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact Number should be 10 digits",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SEmail)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Email is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCity)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"City is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SRequirementDetails)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Requirement details is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SPriority)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Priority is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SLocation)) && !(TextUtils.isEmpty(SCompanyName)) && !(TextUtils.isEmpty(SContactName)) && !(TextUtils.isEmpty(SContactNo)) && (!(TextUtils.isEmpty(SContactNo)) && SContactNo.length() == 10) && !(TextUtils.isEmpty(SEmail)) && !(TextUtils.isEmpty(SCity)) && !(TextUtils.isEmpty(SRequirementDetails)) && !(TextUtils.isEmpty(SPriority))) {
                    try {

                        int matched=0;

                        try {

                            for (int i=0;i<jsonAllRecords.length();i++)
                            {
                                if (jsonAllRecords.optJSONObject(i).optString("documentno").equals(SDocumentNo))
                                {
                                    matched=1;
                                }
                            }

                            if (matched==1)
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("organization", SLocation)
                                        .put("companyname", SCompanyName)
                                        .put("contactname", SContactName)
                                        .put("phonenumber", SContactNo)
                                        .put("email",SEmail)
                                        .put("cityname", SCity)
                                        .put("descprition",SRequirementDetails)
                                        .put("priority",SPriority)
                                        .put("leadcreatecheck", true)
                                        .put("id",FId));
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.firstContactFormSaved_url, jsonObject, true);

                            }
                            else
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("organization", SLocation)
                                        .put("companyname", SCompanyName)
                                        .put("contactname", SContactName)
                                        .put("phonenumber", SContactNo)
                                        .put("email",SEmail)
                                        .put("cityname", SCity)
                                        .put("descprition",SRequirementDetails)
                                        .put("priority",SPriority)
                                        .put("leadcreatecheck", true));
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.firstContactFormSaved_url, jsonObject, true);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                    }

                }

                return true;
            }*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
