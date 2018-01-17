package ssg1.gubba1.gubba1.g.Fragments;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ListView;

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class CRMLeadContacts extends Fragment {

    View view;


    EditText ContactName,ContactNumber,Email,Position,Address;
    Button Next;
    String SContactName,SContactNumber,SEmail,SPosition,SAddress;
    String SSContactName,SSContactNumber,SSEmail,SSPosition,SSAddress;

    String crmLeadId,replaceId,searchkey,addressid,contactsid;

    JSONArray jsonAddressArray,jsonContactsAllRecords;

    FragmentTransaction fragmentTransaction;

    CallWebService service;

    String replace="0";

    SharedPref sp;

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
        service=new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow19").equals("0"))
                {
                    sp.removeData("loginshow19");
                    sp.writeString("loginshow19", "0");

                    displayTuto();

                }
            }
        }, 500);

        ContactName = (EditText) view.findViewById(R.id.input_contactname);
        ContactNumber = (EditText) view.findViewById(R.id.input_contactnumber);
        Email = (EditText) view.findViewById(R.id.input_email);
        Position = (EditText) view.findViewById(R.id.input_position);
        Address = (EditText) view.findViewById(R.id.input_address);
        Next=(Button) view.findViewById(R.id.next) ;

        jsonAddressArray=new JSONArray();
        jsonContactsAllRecords=new JSONArray();
        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        TextInputLayout LContactName = (TextInputLayout) view.findViewById(R.id.input_layout_contactname);
        TextInputLayout LContactNumber = (TextInputLayout) view.findViewById(R.id.input_layout_contactnumber);
        TextInputLayout LEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        TextInputLayout LPosition = (TextInputLayout) view.findViewById(R.id.input_layout_position);
        TextInputLayout LAddress = (TextInputLayout) view.findViewById(R.id.input_layout_address);
        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Next.setTypeface(UbuntuFont);
        ContactName.setTypeface(UbuntuFont);
        ContactNumber.setTypeface(UbuntuFont);
        Email.setTypeface(UbuntuFont);
        Position.setTypeface(UbuntuFont);
        Address.setTypeface(UbuntuFont);

        LContactName.setTypeface(UbuntuFont);
        LContactNumber.setTypeface(UbuntuFont);
        LEmail.setTypeface(UbuntuFont);
        LPosition.setTypeface(UbuntuFont);
        LAddress.setTypeface(UbuntuFont);

        CRMLeadContactsList crmLeadContactsList=new CRMLeadContactsList();
        crmLeadId=crmLeadContactsList.crmLeadId;
        searchkey=crmLeadContactsList.searchkey;

        try {
            SSContactName=getArguments().getString("contactname");
            SSContactNumber=getArguments().getString("contactnumber");
            SSEmail=getArguments().getString("email");
            SSPosition=getArguments().getString("position");
            SSAddress=getArguments().getString("address");
            addressid=getArguments().getString("addressid");
            contactsid=getArguments().getString("contactsid");
            replace=getArguments().getString("replace");

        }catch (Exception e){}

        ContactName.setText(SSContactName);
        ContactNumber.setText(SSContactNumber);
        Email.setText(SSEmail);
        Position.setText(SSPosition);
        Address.setText(SSAddress);

        try {
            crmLeadAddressRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressDialogBox();
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

                    if(TextUtils.isEmpty(SContactName)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact Name is Mandatory",Snackbar.LENGTH_LONG).show();
                        ContactName.setError("Contact Name is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SContactNumber)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact Number is Mandatory",Snackbar.LENGTH_LONG).show();
                        ContactNumber.setError("Contact Number is Mandatory");
                    }
                    else if(SContactNumber.length()!=10){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact Number should be 10 digits",Snackbar.LENGTH_LONG).show();
                        ContactNumber.setError("Contact Number should be 10 digits");
                    }
                    else if(TextUtils.isEmpty(SEmail)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Email is Mandatory",Snackbar.LENGTH_LONG).show();
                        Email.setError("Email is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SPosition)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Position is Mandatory",Snackbar.LENGTH_LONG).show();
                        Position.setError("Position is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SAddress)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Address is Mandatory",Snackbar.LENGTH_LONG).show();
                        Address.setError("Address is Mandatory");
                    }
                    else if (!(TextUtils.isEmpty(SContactName)) && !(TextUtils.isEmpty(SContactNumber)) && !(TextUtils.isEmpty(SEmail)) && !(TextUtils.isEmpty(SPosition)) && !(TextUtils.isEmpty(SAddress)))
                    {
                        Home.crmLeadContactsSending_url = "GCRM_Leadcontact?" + new Constants().USER_N_PASSWORD;
                        JSONObject jsonObject = new JSONObject();

                        try {

                            if (replace.equalsIgnoreCase("0"))
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("gcrmAddress", addressid)
                                        .put("position1", SPosition)
                                        .put("email", SEmail)
                                        .put("phonenumber", SContactNumber)
                                        .put("contactname", SContactName)
                                        .put("gcrmLead",crmLeadId));

                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadContactsSending_url, jsonObject, true);

                            }
                            else
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("id", replaceId)
                                        .put("gcrmAddress", addressid)
                                        .put("position1", SPosition)
                                        .put("email", SEmail)
                                        .put("phonenumber", SContactNumber)
                                        .put("contactname", SContactName)
                                        .put("gcrmLead",crmLeadId));

                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadContactsSending_url, jsonObject, true);

                                replace="0";
                            }

                        }catch (Exception e){}

                    }
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
            {
                ContactName.setText("");
                ContactNumber.setText("");
                Email.setText("");
                Position.setText("");
                Address.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText

    private void crmLeadAddressRequest() throws Exception {

        Home.crmLeadAddress_contacts_url = "GCRM_Address?"+new Constants().USER_N_PASSWORD;

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.crmLeadAddress_contacts_url, true);

    }

    public void createLeadAddressResponse(JSONObject json){
        try {

            jsonAddressArray=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contactAllRecords(JSONObject json){
        try {
            jsonContactsAllRecords=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    EditText inputSearch2;

    public void addressDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> addressSpinner = new ArrayList<String>();

        final HashMap<String,String> addresshp=new HashMap<>();

        for(int i=0;i<jsonAddressArray.length();i++)
        {
            addressSpinner.add(jsonAddressArray.optJSONObject(i).optString("addressline1")+" "
                    +jsonAddressArray.optJSONObject(i).optString("addressline2")+" "
                    +jsonAddressArray.optJSONObject(i).optString("pincode")+" "
                    +jsonAddressArray.optJSONObject(i).optString("region")+" "
                    +jsonAddressArray.optJSONObject(i).optString("country"));
            addresshp.put(jsonAddressArray.optJSONObject(i).optString("addressline1")+" "
                    +jsonAddressArray.optJSONObject(i).optString("addressline2")+" "
                    +jsonAddressArray.optJSONObject(i).optString("pincode")+" "
                    +jsonAddressArray.optJSONObject(i).optString("region")+" "
                    +jsonAddressArray.optJSONObject(i).optString("country"),jsonAddressArray.optJSONObject(i).optString("id"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, addressSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Address.setText(str);
                addressid=addresshp.get(str);
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

    public void finalResponse(JSONObject jsonObject)
    {
        try {

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n\nSearch Key :"+searchkey+"\n\n");

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {

                        CRMLeadContactsList fragment = new CRMLeadContactsList();
                        Home.crmLeadContactsList = (CRMLeadContactsList) fragment;
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

    protected void displayTuto() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_firstcontactform)
                .setFitsSystemWindows(true)
                .on(R.id.add)
                .addRoundRect()
                .withBorder()
                .show();
    }

}
