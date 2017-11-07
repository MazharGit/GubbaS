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

import com.example.mazhar.g.DataValidation;
import com.example.mazhar.g.Home;
import com.example.mazhar.g.R;
import com.example.mazhar.g.ToolbarConfigurer;

import java.util.ArrayList;

public class CRMLeadActivityMOM extends Fragment {

    View view;


    EditText AssignedTo,DueDate,Task,Type;
    Button Confirm;
    String SAssignedTo,SDueDate,STask,SType;

    FragmentTransaction fragmentTransaction;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            setHasOptionsMenu(true);
            view = inflater.inflate(R.layout.fragment_crmleadactivitymom, container, false);
/*
            service = new CallWebService(getActivity());
*/

            /*FmrRegDBHelper dbHelper = new FmrRegDBHelper(getActivity());
            mDb = dbHelper.getWritableDatabase();*/

            AssignedTo = (EditText) view.findViewById(R.id.input_documentno);
            DueDate = (EditText) view.findViewById(R.id.input_companyname);
            Task = (EditText) view.findViewById(R.id.input_contactname);
            Type = (EditText) view.findViewById(R.id.input_contactno);

            try{
                Home.mToolbar.setTitle("CRM Lead Activity MOM");
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

        Confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SAssignedTo=AssignedTo.getText().toString();
                    SDueDate=DueDate.getText().toString();
                    STask=Task.getText().toString();
                    SType=Type.getText().toString();

                    if(DataValidation.isNullString(SAssignedTo)){
                        AssignedTo.setError("Assigned To Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SDueDate)){
                        DueDate.setError("Due Date Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(STask)){
                        Task.setError("Task Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SType)){
                        Type.setError("Type Field is Mandatory");
                        return;
                    }
                    else
                    {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        CRMLead llf = new CRMLead();
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

    public void assignedToDilagbox()
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
                AssignedTo.setText(str);
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
        Items.add("no data");

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

        inflater.inflate(R.menu.addbutton, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:

                try {
                    new ToolbarConfigurer(Home.mToolbar, true);
                    Fragment fragment= new CRMLeadActivityMOM();
                    Home.crmLeadActivityMOM = (CRMLeadActivityMOM) fragment;
                    fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }catch (Exception e){e.printStackTrace();}

            case R.id.save:

                /*try {
                    new ToolbarConfigurer(Home.mToolbar, true);
                    Fragment fragment= new FirstContactForm();
                    Home.firstContactForm = (FirstContactForm) fragment;
                    fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }catch (Exception e){e.printStackTrace();}*/

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
