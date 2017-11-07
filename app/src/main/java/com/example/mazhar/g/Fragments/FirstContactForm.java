package com.example.mazhar.g.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
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
import android.widget.Toast;

import com.example.mazhar.g.DataValidation;
import com.example.mazhar.g.Home;
import com.example.mazhar.g.R;
import com.example.mazhar.g.ToolbarConfigurer;
import com.example.mazhar.g.utils.CallWebService;
import com.example.mazhar.g.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FirstContactForm extends Fragment {

    View view;


    EditText DocumentNo,Location,CompanyName,ContactName,ContactNo,Email,City,RequirementDetails,Priority;
    Button CreateLead;
    String SDocumentNo,SLocation,SCompanyName,SContactName,SContactNo,SEmail,SCity,SRequirementDetails,SPriority;

    int save,next;

    JSONArray jsonArray;

    FragmentTransaction fragmentTransaction;
    CallWebService service;

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


            setHasOptionsMenu(true);
            view = inflater.inflate(R.layout.fragment_first_fragment_form, container, false);

            service = new CallWebService(getActivity());

            DocumentNo = (EditText) view.findViewById(R.id.input_documentno);
            Location=(EditText) view.findViewById(R.id.input_location);
            CompanyName = (EditText) view.findViewById(R.id.input_companyname);
            ContactName = (EditText) view.findViewById(R.id.input_contactname);
            ContactNo = (EditText) view.findViewById(R.id.input_contactno);
            Email = (EditText) view.findViewById(R.id.input_email);
            City = (EditText) view.findViewById(R.id.input_city);
            RequirementDetails = (EditText) view.findViewById(R.id.input_requirementdetails);
            Priority = (EditText) view.findViewById(R.id.input_priority);
            CreateLead=(Button) view.findViewById(R.id.createlead) ;

            try{
                Home.mToolbar.setTitle("First Contact Form");
            }catch (Exception e){}

            TextInputLayout LDocumentNo = (TextInputLayout) view.findViewById(R.id.input_layout_documentno);
            TextInputLayout LLocation =(TextInputLayout) view.findViewById(R.id.input_layout_location);
            TextInputLayout LCompanyName = (TextInputLayout) view.findViewById(R.id.input_layout_companyname);
            TextInputLayout LContactName = (TextInputLayout) view.findViewById(R.id.input_layout_contactname);
            TextInputLayout LContactNo = (TextInputLayout) view.findViewById(R.id.input_layout_contactno);
            TextInputLayout LEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
            TextInputLayout LCity = (TextInputLayout) view.findViewById(R.id.input_layout_city);
            TextInputLayout LRequirementDetails = (TextInputLayout) view.findViewById(R.id.input_layout_requirementdetails);
            TextInputLayout LPriority = (TextInputLayout) view.findViewById(R.id.input_layout_priority);

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

                    SDocumentNo=DocumentNo.getText().toString();
                    SCompanyName=CompanyName.getText().toString();
                    SContactName=ContactName.getText().toString();
                    SContactNo=ContactNo.getText().toString();
                    SEmail=Email.getText().toString();
                    SCity=City.getText().toString();
                    SRequirementDetails=RequirementDetails.getText().toString();
                    SPriority=Priority.getText().toString();
                    if(DataValidation.isNullString(SCompanyName)){
                        CompanyName.setError("Company Name Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SContactName)){
                        ContactName.setError("Contact Name Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SContactNo)){
                        ContactNo.setError("Contact Number Field is Mandatory");
                        return;
                    }
                    else if(SContactNo.length()!=10){
                        ContactNo.setError("Contact Number Should be Length 10");
                        return;
                    }
                    else if(DataValidation.isNullString(SEmail)){
                        Email.setError("Email Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SCity)){
                        City.setError("City Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SRequirementDetails)){
                        RequirementDetails.setError("Requirement Details Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SPriority)){
                        Priority.setError("Priority Field is Mandatory");
                        return;
                    }
                    else
                    {
                        Home.firstContactForm_url = "GCRM_Firstcontactform?" + new Constants().USER_N_PASSWORD;
                        JSONObject jsonObject = new JSONObject();

                        try {
                            jsonObject.put("data", new JSONObject()
                                    .put("documentno", SDocumentNo)
                                    .put("companyname", SCompanyName)
                                    .put("contactname", SContactName)
                                    .put("phonenumber", SContactNo)
                                    .put("email",SEmail)
                                    .put("cityname", SCity)
                                    .put("descprition",SRequirementDetails)
                                    .put("priority",SPriority));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.firstContactForm_url, jsonObject, true);
                        next=1;

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

        ArrayList<String> Items = new ArrayList<String>();
        JSONObject jsonObject=Constants.jsonObject;
        try {
            jsonArray = jsonObject.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getActivity(), ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
        System.out.println("length"+jsonArray.length());

        //

        for(int i=0;i<jsonArray.length();i++)
        {
            try {
                Items.add(jsonObject.optJSONObject("response").optJSONArray("data").getJSONObject(i).optString("orgName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.addbutton, menu);

    }

    public void firstContactFormRespense(JSONObject json){
        try {

            JSONArray jsonArray=json.optJSONObject("response").optJSONArray("data");
            String no=jsonArray.getJSONObject(0).optString("documentno");
            int noo=Integer.valueOf(no)+1;

            //DocumentNo.setText(String.valueOf(noo));

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
            builder.setMessage("Successfully Inserted into ERP\n\nDocument Number :"+String.valueOf(noo));
            builder.setPositiveButton("OK", null);
            builder.show();

            if (next==1)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                CRMLead llf = new CRMLead();
                ft.replace(R.id.frame, llf);
                ft.commit();
                next=0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void firstContactFormRequest() throws Exception {

        Home.firstContactForm_url = "GCRM_Firstcontactform?"+new Constants().USER_N_PASSWORD+"&_sortBy=creationDate%20desc";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.firstContactForm_url, true);

    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:

                try {
                    new ToolbarConfigurer(Home.mToolbar, true);
                    Fragment fragment= new FirstContactForm();
                    Home.firstContactForm = (FirstContactForm) fragment;
                    fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }catch (Exception e){e.printStackTrace();}

            case R.id.save:

                SDocumentNo=DocumentNo.getText().toString();
                SCompanyName=CompanyName.getText().toString();
                SContactName=ContactName.getText().toString();
                SContactNo=ContactNo.getText().toString();
                SEmail=Email.getText().toString();
                SCity=City.getText().toString();
                SRequirementDetails=RequirementDetails.getText().toString();
                SPriority=Priority.getText().toString();
                Home.firstContactForm_url = "GCRM_Firstcontactform?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    if(SCompanyName.length()==0){
                        CompanyName.setError("Company Name Field is Mandatory");
                    }
                    else if(SContactName.length()==0){
                        ContactName.setError("Contact Name Field is Mandatory");
                    }
                    else if(SContactNo.length()==0){
                        ContactNo.setError("Contact Number Field is Mandatory");
                    }
                    else if(SContactNo.length()!=10){
                        ContactNo.setError("Contact Number Should be Length 10");
                    }
                    else if(SEmail.length()==0){
                        Email.setError("Email Field is Mandatory");
                    }
                    else if(SCity.length()==0){
                        City.setError("City Field is Mandatory");
                    }
                    else if(SRequirementDetails.length()==0){
                        RequirementDetails.setError("Requirement Details Field is Mandatory");
                    }
                    else if(SPriority.length()==0){
                        Priority.setError("Priority Field is Mandatory");
                    }
                    else
                    {
                        try
                        {
                            jsonObject.put("data", new JSONObject()
                                    .put("documentno", SDocumentNo)
                                    .put("companyname", SCompanyName)
                                    .put("contactname", SContactName)
                                    .put("phonenumber", SContactNo)
                                    .put("email",SEmail)
                                    .put("cityname", SCity)
                                    .put("descprition",SRequirementDetails)
                                    .put("priority",SPriority));

                            save=1;
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.firstContactForm_url, jsonObject, true);

                        }catch (Exception e){}

                        CompanyName.setText("");
                        ContactName.setText("");
                        ContactNo.setText("");
                        Email.setText("");
                        City.setText("");
                        RequirementDetails.setText("");
                        Priority.setText("");
                    }

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
