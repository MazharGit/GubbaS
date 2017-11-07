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

public class CRMLeadAddress extends Fragment {

    View view;


    EditText AddressLine1,AddressLine2,City,Pincode,Region,Country,InvoiceAddress;
    Button Next;
    String SAddressLine1,SAddressLine2,SCity,SPincode,SRegion,SCountry,SInvoiceAddress;

    FragmentTransaction fragmentTransaction;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_crmleadaddress, null);

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
        view = inflater.inflate(R.layout.activity_crmleadaddress, container, false);

        AddressLine1 = (EditText) view.findViewById(R.id.input_addressline1);
        AddressLine2 = (EditText) view.findViewById(R.id.input_addressline2);
        City = (EditText) view.findViewById(R.id.input_city);
        Pincode = (EditText) view.findViewById(R.id.input_pincode);
        Region = (EditText) view.findViewById(R.id.input_region);
        Country = (EditText) view.findViewById(R.id.input_country);
        InvoiceAddress = (EditText) view.findViewById(R.id.input_invoiceaddress);
        Next=(Button) view.findViewById(R.id.next) ;

        try{
            Home.mToolbar.setTitle("CRM - Lead Address");
        }catch (Exception e){}

        TextInputLayout LAddressLine1 = (TextInputLayout) view.findViewById(R.id.input_layout_addressline1);
        TextInputLayout LAddressLine2 = (TextInputLayout) view.findViewById(R.id.input_layout_addressline2);
        TextInputLayout LCity = (TextInputLayout) view.findViewById(R.id.input_layout_city);
        TextInputLayout LPincode = (TextInputLayout) view.findViewById(R.id.input_layout_pincode);
        TextInputLayout LRegion = (TextInputLayout) view.findViewById(R.id.input_layout_region);
        TextInputLayout LCountry = (TextInputLayout) view.findViewById(R.id.input_layout_country);
        TextInputLayout LInvoiceAddress = (TextInputLayout) view.findViewById(R.id.input_layout_invoiceaddress);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Next.setTypeface(UbuntuFont);
        AddressLine1.setTypeface(UbuntuFont);
        AddressLine2.setTypeface(UbuntuFont);
        City.setTypeface(UbuntuFont);
        Pincode.setTypeface(UbuntuFont);
        Region.setTypeface(UbuntuFont);
        Country.setTypeface(UbuntuFont);
        InvoiceAddress.setTypeface(UbuntuFont);

        LAddressLine1.setTypeface(UbuntuFont);
        LAddressLine2.setTypeface(UbuntuFont);
        LCity.setTypeface(UbuntuFont);
        LPincode.setTypeface(UbuntuFont);
        LRegion.setTypeface(UbuntuFont);
        LCountry.setTypeface(UbuntuFont);
        LInvoiceAddress.setTypeface(UbuntuFont);

        InvoiceAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvoiceAddressDialogbox();
            }
        });

        Country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryDilagbox();
            }
        });

        Region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegionDilagbox();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SAddressLine1=AddressLine1.getText().toString();
                SAddressLine2=AddressLine2.getText().toString();
                SCity=City.getText().toString();
                SPincode=Pincode.getText().toString();
                SRegion=Region.getText().toString();
                SCountry=Country.getText().toString();
                SInvoiceAddress=InvoiceAddress.getText().toString();
                    if(DataValidation.isNullString(SAddressLine1)){
                        AddressLine1.setError("Address Line 1 Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SAddressLine2)){
                        AddressLine2.setError("Address Line 2 is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SCity)){
                        City.setError("City Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SPincode)){
                        Pincode.setError("Pincode Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SRegion)){
                        Region.setError("Region Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SCountry)){
                        Country.setError("Country Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SInvoiceAddress)){
                        InvoiceAddress.setError("Invoice Address Field is Mandatory");
                        return;
                    }
                    else
                    {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        CRMLeadActivity llf = new CRMLeadActivity();
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

    public void InvoiceAddressDialogbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("Yes");
        Items.add("No");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                InvoiceAddress.setText(str);
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

    public void CountryDilagbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("No data");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Country.setText(str);
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

    public void RegionDilagbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("No data");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Region.setText(str);
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
