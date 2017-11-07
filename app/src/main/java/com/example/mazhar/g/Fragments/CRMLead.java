package com.example.mazhar.g.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CRMLead extends Fragment {

    View view;

    EditText SearchKey,CompanyName,AssignedTo,FirstContactID,BusinessPartnerID,Priority;
    Button Next;
    String SSearchKey,SCompanyName,SAssignedTo,SFirstContactID,SBusinessPartnerID,SPriority;

    FragmentTransaction fragmentTransaction;

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

        SearchKey = (EditText) view.findViewById(R.id.input_searchkey);
        CompanyName = (EditText) view.findViewById(R.id.input_companyname);
        AssignedTo = (EditText) view.findViewById(R.id.input_assignedto);
        FirstContactID = (EditText) view.findViewById(R.id.input_firstcontactid);
        BusinessPartnerID = (EditText) view.findViewById(R.id.input_businesspartnerid);
        Priority = (EditText) view.findViewById(R.id.input_priority);
        Next=(Button) view.findViewById(R.id.next) ;

        try{
            Home.mToolbar.setTitle("CRM - Lead");
        }catch (Exception e){}

        TextInputLayout LSearchKey = (TextInputLayout) view.findViewById(R.id.input_layout_searchkey);
        TextInputLayout LCompanyName = (TextInputLayout) view.findViewById(R.id.input_layout_companyname);
        TextInputLayout LAssignedTo = (TextInputLayout) view.findViewById(R.id.input_layout_assignedto);
        TextInputLayout LFirstContactID = (TextInputLayout) view.findViewById(R.id.input_layout_firstcontactid);
        TextInputLayout LBusinessPartnerID = (TextInputLayout) view.findViewById(R.id.input_layout_businesspartnerid);
        TextInputLayout LPriority = (TextInputLayout) view.findViewById(R.id.input_layout_priority);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Next.setTypeface(UbuntuFont);
        SearchKey.setTypeface(UbuntuFont);
        CompanyName.setTypeface(UbuntuFont);
        AssignedTo.setTypeface(UbuntuFont);
        FirstContactID.setTypeface(UbuntuFont);
        BusinessPartnerID.setTypeface(UbuntuFont);
        Priority.setTypeface(UbuntuFont);

        LSearchKey.setTypeface(UbuntuFont);
        LCompanyName.setTypeface(UbuntuFont);
        LAssignedTo.setTypeface(UbuntuFont);
        LFirstContactID.setTypeface(UbuntuFont);
        LBusinessPartnerID.setTypeface(UbuntuFont);
        LPriority.setTypeface(UbuntuFont);

        Priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsDilagbox();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SSearchKey=SearchKey.getText().toString();
                SCompanyName=CompanyName.getText().toString();
                SAssignedTo=AssignedTo.getText().toString();
                SFirstContactID=FirstContactID.getText().toString();
                SBusinessPartnerID=BusinessPartnerID.getText().toString();
                SPriority=Priority.getText().toString();
                    if(DataValidation.isNullString(SCompanyName)){
                        CompanyName.setError("Company Name Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SAssignedTo)){
                        AssignedTo.setError("Assigned To Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SFirstContactID)){
                        FirstContactID.setError("First Contact ID Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SBusinessPartnerID)){
                        BusinessPartnerID.setError("Business Partner ID Field is Mandatory");
                        return;
                    }
                    else if(DataValidation.isNullString(SPriority)){
                        Priority.setError("Business Partner ID Field is Mandatory");
                        return;
                    }
                    else
                    {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        CRMLeadEnquiry llf = new CRMLeadEnquiry();
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
}
