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

public class CRMLeadActivity extends Fragment {

    View view;


    EditText Subject,ActivityType,Contact;
    Button Save;
    String SSubject,SActivityType,SContact;
    String SSSubject,SSActivityType,SSContact,SSContactid;
    TextInputLayout LSubject,LActivityType,LContact;
    public String activityId,id,replaceId,searchkey,crmLeadId;

    CallWebService service;
    JSONArray jsonContactsArray,jsonActivityAllRecords;

    FragmentTransaction fragmentTransaction;

    String replace="0";

    SharedPref sp;

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

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow7").equals("0"))
                {
                    sp.removeData("loginshow7");
                    sp.writeString("loginshow7", "0");

                    displayTuto();

                }
            }
        }, 500);


        service=new CallWebService(getActivity());

        CRMLeadActivityList crmLeadActivityList=new CRMLeadActivityList();
        crmLeadId=crmLeadActivityList.crmLeadId;
        searchkey=crmLeadActivityList.searchkey;

        Subject = (EditText) view.findViewById(R.id.input_subject);
        ActivityType = (EditText) view.findViewById(R.id.input_activetype);
        Contact = (EditText) view.findViewById(R.id.input_contact);
        Save=(Button) view.findViewById(R.id.next) ;

        jsonActivityAllRecords=new JSONArray();

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LSubject = (TextInputLayout) view.findViewById(R.id.input_layout_subject);
        LActivityType = (TextInputLayout) view.findViewById(R.id.input_layout_activetype);
        LContact = (TextInputLayout) view.findViewById(R.id.input_layout_contact);

        try {
            SSContactid=getArguments().getString("contactid");
            SSSubject=getArguments().getString("subject");
            SSActivityType=getArguments().getString("activitytype");
            SSContact=getArguments().getString("contact");
            activityId=getArguments().getString("activityId");
            replace=getArguments().getString("replace");
        }catch (Exception e){}

        Subject.setText(SSSubject);
        ActivityType.setText(SSActivityType);
        Contact.setText(SSContact);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Subject.setTypeface(UbuntuFont);
        ActivityType.setTypeface(UbuntuFont);
        Contact.setTypeface(UbuntuFont);

        LSubject.setTypeface(UbuntuFont);
        LActivityType.setTypeface(UbuntuFont);
        LContact.setTypeface(UbuntuFont);

        try {
            crmLeadContactsRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ActivityType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiveTypeDialogbox();
            }
        });

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsDialogBox();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SSubject=Subject.getText().toString();
                SActivityType=ActivityType.getText().toString();
                SContact=Contact.getText().toString();

                    if(TextUtils.isEmpty(SSubject)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Subject is Mandatory",Snackbar.LENGTH_LONG).show();
                        Subject.setError("Subject is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SActivityType)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Activity Type is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if(TextUtils.isEmpty(SContact)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Contact is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if (!(TextUtils.isEmpty(SSubject)) && !(TextUtils.isEmpty(SActivityType)) && !(TextUtils.isEmpty(SContact)))
                    {

                        Home.crmLeadActivity_url = "GCRM_Activity?" + new Constants().USER_N_PASSWORD;
                        JSONObject jsonObject = new JSONObject();

                        try {
                            if (replace.equalsIgnoreCase("0"))
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("gcrmLeadcontact", SSContactid)
                                        .put("activitytype", SActivityType)
                                        .put("subject", SSubject)
                                        .put("gcrmLead",crmLeadId));
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadActivity_url, jsonObject, true);

                            }
                            else
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("gcrmLeadcontact", SSContactid)
                                        .put("activitytype", SActivityType)
                                        .put("subject", SSubject)
                                        .put("id",replaceId)
                                        .put("gcrmLead",crmLeadId));
                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadActivity_url, jsonObject, true);

                                replace="0";
                            }

                        }catch (Exception e){}
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
            {
                Subject.setText("");
                ActivityType.setText("");
                Contact.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void crmLeadContactsRequest() throws Exception {

        Home.getCrmLeadContacts_url = "GCRM_Leadcontact?"+new Constants().USER_N_PASSWORD+"&_sortBy=updated%20";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.getCrmLeadContacts_url, true);

    }

    public void contactsResponse(JSONObject json){
        try {
            jsonContactsArray=json.optJSONObject("response").optJSONArray("data");
            System.out.println("jsonContactsArray"+jsonContactsArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void activityAllRecords(JSONObject json){
        try {
            jsonActivityAllRecords=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contactsDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> contactsSpinner = new ArrayList<String>();

        final HashMap<String,String> contactsHp=new HashMap<>();

        for(int i=0;i<jsonContactsArray.length();i++)
        {
            contactsSpinner.add(jsonContactsArray.optJSONObject(i).optString("contactname"));
            contactsHp.put(jsonContactsArray.optJSONObject(i).optString("contactname"),jsonContactsArray.optJSONObject(i).optString("id"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, contactsSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Contact.setText(str);
                SSContactid=contactsHp.get(str);
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

    public void activityResponse(JSONObject jsonObject)
    {
        try {

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n\nSearch Key :"+searchkey+"\n\n");

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {

                        CRMLeadActivityList fragment = new CRMLeadActivityList();
                        Home.crmLeadActivityList = (CRMLeadActivityList) fragment;
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
