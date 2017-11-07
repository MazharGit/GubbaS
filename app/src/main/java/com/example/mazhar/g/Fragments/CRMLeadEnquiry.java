package com.example.mazhar.g.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mazhar.g.DataValidation;
import com.example.mazhar.g.Home;
import com.example.mazhar.g.R;

import java.util.ArrayList;

public class CRMLeadEnquiry extends Fragment {

    View view;


    EditText ProductName,UOM,Quantity,FromTemperature,ToTemperature,Location,RequirementDetails;
    Button Next;
    String SProductName,SUOM,SQuantity,SFromTemperature,SToTemperature,SLocation,SRequirementDetails;

    FragmentTransaction fragmentTransaction;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_crmleadenquiry, null);

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
        view = inflater.inflate(R.layout.activity_crmleadenquiry, container, false);

        ProductName = (EditText) view.findViewById(R.id.input_productname);
        UOM = (EditText) view.findViewById(R.id.input_uom);
        Quantity = (EditText) view.findViewById(R.id.input_quantity);
        FromTemperature = (EditText) view.findViewById(R.id.input_fromtemperature);
        ToTemperature = (EditText) view.findViewById(R.id.input_totemperature);
        Location = (EditText) view.findViewById(R.id.input_location);
        RequirementDetails = (EditText) view.findViewById(R.id.input_requirementdetails);
        Next=(Button) view.findViewById(R.id.next) ;

        try{
            Home.mToolbar.setTitle("CRM - Lead Enquiry");
        }catch (Exception e){}

        TextInputLayout LProductName = (TextInputLayout) view.findViewById(R.id.input_layout_productname);
        TextInputLayout LUOM = (TextInputLayout) view.findViewById(R.id.input_layout_uom);
        TextInputLayout LQuantity = (TextInputLayout) view.findViewById(R.id.input_layout_quantity);
        TextInputLayout LFromTemperature = (TextInputLayout) view.findViewById(R.id.input_layout_fromtemperature);
        TextInputLayout LToTemperature = (TextInputLayout) view.findViewById(R.id.input_layout_totemperature);
        TextInputLayout LLocation = (TextInputLayout) view.findViewById(R.id.input_layout_location);
        TextInputLayout LRequirementDetails = (TextInputLayout) view.findViewById(R.id.input_layout_requirementdetails);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Next.setTypeface(UbuntuFont);
        ProductName.setTypeface(UbuntuFont);
        UOM.setTypeface(UbuntuFont);
        Quantity.setTypeface(UbuntuFont);
        FromTemperature.setTypeface(UbuntuFont);
        ToTemperature.setTypeface(UbuntuFont);
        Location.setTypeface(UbuntuFont);
        RequirementDetails.setTypeface(UbuntuFont);

        LProductName.setTypeface(UbuntuFont);
        LUOM.setTypeface(UbuntuFont);
        LQuantity.setTypeface(UbuntuFont);
        LFromTemperature.setTypeface(UbuntuFont);
        LToTemperature.setTypeface(UbuntuFont);
        LLocation.setTypeface(UbuntuFont);
        LRequirementDetails.setTypeface(UbuntuFont);

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDilagbox();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SProductName=ProductName.getText().toString();
                SUOM=UOM.getText().toString();
                SQuantity=Quantity.getText().toString();
                SFromTemperature=FromTemperature.getText().toString();
                SToTemperature=ToTemperature.getText().toString();
                SLocation=Location.getText().toString();
                SRequirementDetails=RequirementDetails.getText().toString();
                    if(DataValidation.isNullString(SProductName)){
                        ProductName.setError("Product Name Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SUOM)){
                        UOM.setError("UOM Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SQuantity)){
                        Quantity.setError("Quantity Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SFromTemperature)){
                        FromTemperature.setError("From Temperature Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SToTemperature)){
                        ToTemperature.setError("To Temperature Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SLocation)){
                        Location.setError("Location Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SRequirementDetails)){
                        RequirementDetails.setError("Requirement Details is Mandatory");
                        return;
                    }
                    else
                    {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        CRMLeadContacts llf = new CRMLeadContacts();
                        ft.replace(R.id.frame, llf);
                        ft.commit();
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
        Items.add("no data");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Location.setText(str);
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
}
