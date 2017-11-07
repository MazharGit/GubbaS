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

public class CRMLeadContacts extends Fragment {

    View view;


    EditText ContactName,ContactNumber,Email,Position,Address,CreationDone;
    Button Next;
    String SContactName,SContactNumber,SEmail,SPosition,SAddress,SCreationDone;

    FragmentTransaction fragmentTransaction;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_crmleadcontacts, null);

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
        view = inflater.inflate(R.layout.activity_crmleadcontacts, container, false);

        ContactName = (EditText) view.findViewById(R.id.input_contactname);
        ContactNumber = (EditText) view.findViewById(R.id.input_contactnumber);
        Email = (EditText) view.findViewById(R.id.input_email);
        Position = (EditText) view.findViewById(R.id.input_position);
        Address = (EditText) view.findViewById(R.id.input_address);
        CreationDone = (EditText) view.findViewById(R.id.input_creationdone);
        Next=(Button) view.findViewById(R.id.next) ;

        try{
            Home.mToolbar.setTitle("CRM - Lead Contacts");
        }catch (Exception e){}

        TextInputLayout LContactName = (TextInputLayout) view.findViewById(R.id.input_layout_contactname);
        TextInputLayout LContactNumber = (TextInputLayout) view.findViewById(R.id.input_layout_contactnumber);
        TextInputLayout LEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        TextInputLayout LPosition = (TextInputLayout) view.findViewById(R.id.input_layout_position);
        TextInputLayout LAddress = (TextInputLayout) view.findViewById(R.id.input_layout_address);
        TextInputLayout LCreationDone = (TextInputLayout) view.findViewById(R.id.input_layout_creationdone);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Next.setTypeface(UbuntuFont);
        ContactName.setTypeface(UbuntuFont);
        ContactNumber.setTypeface(UbuntuFont);
        Email.setTypeface(UbuntuFont);
        Position.setTypeface(UbuntuFont);
        Address.setTypeface(UbuntuFont);
        CreationDone.setTypeface(UbuntuFont);

        LContactName.setTypeface(UbuntuFont);
        LContactNumber.setTypeface(UbuntuFont);
        LEmail.setTypeface(UbuntuFont);
        LPosition.setTypeface(UbuntuFont);
        LAddress.setTypeface(UbuntuFont);
        LCreationDone.setTypeface(UbuntuFont);

        CreationDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDilagbox();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SContactName=ContactName.getText().toString();
                SContactNumber=ContactNumber.getText().toString();
                SEmail=Email.getText().toString();
                SPosition=Position.getText().toString();
                SAddress=Address.getText().toString();
                SCreationDone=CreationDone.getText().toString();
                    if(DataValidation.isNullString(SContactName)){
                        ContactName.setError("Contact Name Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SContactNumber)){
                        ContactNumber.setError("Contact Number Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SEmail)){
                        Email.setError("Email Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SPosition)){
                        Position.setError("Position Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SAddress)){
                        Address.setError("Address Field is Mandatory");
                        return;
                    }
                    else
                    {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        CRMLeadAddress llf = new CRMLeadAddress();
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
                CreationDone.setText(str);
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
