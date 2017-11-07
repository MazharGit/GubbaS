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

public class CRMLeadActivity extends Fragment {

    View view;


    EditText Subject,ActivityType,Contact;
    Button Next;
    String SSubject,SActivityType,SContact;

    FragmentTransaction fragmentTransaction;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_crmleadactivity, null);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.activity_crmleadactivity, container, false);

        Subject = (EditText) view.findViewById(R.id.input_subject);
        ActivityType = (EditText) view.findViewById(R.id.input_activetype);
        Contact = (EditText) view.findViewById(R.id.input_contact);
        Next=(Button) view.findViewById(R.id.next) ;

        try{
            Home.mToolbar.setTitle("CRM - Lead Activity");
        }catch (Exception e){}

        TextInputLayout LSubject = (TextInputLayout) view.findViewById(R.id.input_layout_subject);
        TextInputLayout LActivityType = (TextInputLayout) view.findViewById(R.id.input_layout_activetype);
        TextInputLayout LContact = (TextInputLayout) view.findViewById(R.id.input_layout_contact);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Next.setTypeface(UbuntuFont);
        Subject.setTypeface(UbuntuFont);
        ActivityType.setTypeface(UbuntuFont);
        Contact.setTypeface(UbuntuFont);

        LSubject.setTypeface(UbuntuFont);
        LActivityType.setTypeface(UbuntuFont);
        LContact.setTypeface(UbuntuFont);

        ActivityType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiveTypeDialogbox();
            }
        });

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactDialogbox();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SSubject=Subject.getText().toString();
                SActivityType=ActivityType.getText().toString();
                SContact=Contact.getText().toString();
                    if(DataValidation.isNullString(SSubject)){
                        Subject.setError("Subject Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SActivityType)){
                        ActivityType.setError("Active Type is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SContact)){
                        Contact.setError("Contact Field is Mandatory");
                        return;
                    }
                    else
                    {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        CRMLeadActivityMOM llf = new CRMLeadActivityMOM();
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

    public void ActiveTypeDialogbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("Creation");
        Items.add("Call");
        Items.add("Meeting");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                ActivityType.setText(str);
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

    public void ContactDialogbox()
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
                Contact.setText(str);
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
