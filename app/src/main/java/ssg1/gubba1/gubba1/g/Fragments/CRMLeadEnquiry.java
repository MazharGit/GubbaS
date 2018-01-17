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

public class CRMLeadEnquiry extends Fragment {

    View view;


    EditText ProductName,UOM,Quantity,FromTemperature,ToTemperature,CLocation,RequirementDetails;
    Button Save;
    String SProductName,SUOM,SQuantity,SFromTemperature,SToTemperature,SCLocation,SRequirementDetails;
    String SSProductName,SSUOM,SSQuantity,SSFromTemperature,SSToTemperature,SSCLocation,SSRequirementDetails;

    FragmentTransaction fragmentTransaction;

    JSONArray jsonEnquiryAllRecords;

    CallWebService service;

    JSONArray jsonArray,jsonProductArray;

    String orgIddd,orgIdd,crmLeadId,productId,uomId,location,replaceid,searchkey,enquiryid;

    String replace="0";

    SharedPref sp;

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

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow11").equals("0"))
                {
                    sp.removeData("loginshow11");
                    sp.writeString("loginshow11", "0");

                    displayTuto();

                }
            }
        }, 500);

        CRMLeadEnquiryList crmLeadEnquiryList=new CRMLeadEnquiryList();
        crmLeadId=crmLeadEnquiryList.crmLeadId;
        searchkey=crmLeadEnquiryList.searchkey;

        ProductName = (EditText) view.findViewById(R.id.input_productname);
        UOM = (EditText) view.findViewById(R.id.input_uom);
        Quantity = (EditText) view.findViewById(R.id.input_quantity);
        FromTemperature = (EditText) view.findViewById(R.id.input_fromtemperature);
        ToTemperature = (EditText) view.findViewById(R.id.input_totemperature);
        CLocation = (EditText) view.findViewById(R.id.input_location);
        RequirementDetails = (EditText) view.findViewById(R.id.input_requirementdetails);
        Save=(Button) view.findViewById(R.id.save) ;

        try {
            productnameRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        jsonEnquiryAllRecords=new JSONArray();
        jsonProductArray=new JSONArray();

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        final TextInputLayout LProductName = (TextInputLayout) view.findViewById(R.id.input_layout_productname);
        final TextInputLayout LUOM = (TextInputLayout) view.findViewById(R.id.input_layout_uom);
        final TextInputLayout LQuantity = (TextInputLayout) view.findViewById(R.id.input_layout_quantity);
        final TextInputLayout LFromTemperature = (TextInputLayout) view.findViewById(R.id.input_layout_fromtemperature);
        final TextInputLayout LToTemperature = (TextInputLayout) view.findViewById(R.id.input_layout_totemperature);
        final TextInputLayout LLocation = (TextInputLayout) view.findViewById(R.id.input_layout_location);
        final TextInputLayout LRequirementDetails = (TextInputLayout) view.findViewById(R.id.input_layout_requirementdetails);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        ProductName.setTypeface(UbuntuFont);
        UOM.setTypeface(UbuntuFont);
        Quantity.setTypeface(UbuntuFont);
        FromTemperature.setTypeface(UbuntuFont);
        ToTemperature.setTypeface(UbuntuFont);
        CLocation.setTypeface(UbuntuFont);
        RequirementDetails.setTypeface(UbuntuFont);

        LProductName.setTypeface(UbuntuFont);
        LUOM.setTypeface(UbuntuFont);
        LQuantity.setTypeface(UbuntuFont);
        LFromTemperature.setTypeface(UbuntuFont);
        LToTemperature.setTypeface(UbuntuFont);
        LLocation.setTypeface(UbuntuFont);
        LRequirementDetails.setTypeface(UbuntuFont);

        try {
            enquiryid=getArguments().getString("enquiryid");
            SSProductName=getArguments().getString("productname");
            SSUOM=getArguments().getString("uom");
            SSQuantity=getArguments().getString("quantity");
            SSFromTemperature=getArguments().getString("fromtemperature");
            SSToTemperature=getArguments().getString("totemperature");
            SSCLocation=getArguments().getString("location");
            SSRequirementDetails=getArguments().getString("requirementdetails");
            orgIdd=getArguments().getString("locationid");
            replace=getArguments().getString("replace");

        }catch (Exception e){}

        ProductName.setText(SSProductName);
        UOM.setText(SSUOM);
        Quantity.setText(SSQuantity);
        FromTemperature.setText(SSFromTemperature);
        ToTemperature.setText(SSToTemperature);
        CLocation.setText(SSCLocation);
        RequirementDetails.setText(SSRequirementDetails);


        CLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationDialogBox();
            }
        });

        ProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDialogBox();
            }
        });

        UOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"UOM is not Editable",Snackbar.LENGTH_LONG).show();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SProductName=ProductName.getText().toString();
                SUOM=UOM.getText().toString();
                SQuantity=Quantity.getText().toString();
                SFromTemperature=FromTemperature.getText().toString();
                SToTemperature=ToTemperature.getText().toString();
                SRequirementDetails=RequirementDetails.getText().toString();
                SCLocation=CLocation.getText().toString();

                if (TextUtils.isEmpty(SProductName)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Product name is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(SUOM)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"UOM is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(SQuantity)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Quantity is Mandatory",Snackbar.LENGTH_LONG).show();
                    Quantity.setError("Quantity is Mandatory");
                }
                else if (TextUtils.isEmpty(SFromTemperature)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"From Temperature is Mandatory",Snackbar.LENGTH_LONG).show();
                    FromTemperature.setError("From Temperature is Mandatory");
                }
                else if (TextUtils.isEmpty(SToTemperature)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"To Temperature is Mandatory",Snackbar.LENGTH_LONG).show();
                    ToTemperature.setError("To Temperature is Mandatory");
                }
                else if (TextUtils.isEmpty(SRequirementDetails)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Requirement Details is Mandatory",Snackbar.LENGTH_LONG).show();
                    RequirementDetails.setError("Requirement Details is Mandatory");
                }
                else if (TextUtils.isEmpty(SCLocation)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Location is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SProductName)) && !(TextUtils.isEmpty(SUOM)) && !(TextUtils.isEmpty(SQuantity)) && !(TextUtils.isEmpty(SFromTemperature)) && !(TextUtils.isEmpty(SToTemperature)) && !(TextUtils.isEmpty(SRequirementDetails)) && !(TextUtils.isEmpty(SCLocation))) {

                    if (TextUtils.isEmpty(SQuantity))
                    {
                        SQuantity="0";
                    }

                    if (TextUtils.isEmpty(SFromTemperature))
                    {
                        SFromTemperature="0";
                    }

                    if (TextUtils.isEmpty(SToTemperature))
                    {
                        SToTemperature="0";
                    }

                    Home.crmLeadEnquirySending_url = "GCRM_Enquiry?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();


                    try {

                        if (replace.equalsIgnoreCase("0"))
                        {
                            jsonObject.put("data", new JSONObject()
                                    .put("gcrmLead", crmLeadId)
                                    .put("gcrmLocator",orgIddd)
                                    .put("organization", location)
                                    .put("quantity", Double.parseDouble(SQuantity))
                                    .put("fromtemp", Double.parseDouble(SFromTemperature))
                                    .put("totemp", Double.parseDouble(SToTemperature))
                                    .put("product",productId)
                                    .put("uom", uomId)
                                    .put("descprition",SRequirementDetails));

                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadEnquirySending_url, jsonObject, true);

                        }
                        else
                        {
                            jsonObject.put("data", new JSONObject()
                                    .put("gcrmLead", crmLeadId)
                                    .put("gcrmLocator",orgIddd)
                                    .put("organization", location)
                                    .put("quantity", Double.parseDouble(SQuantity))
                                    .put("fromtemp", Double.parseDouble(SFromTemperature))
                                    .put("totemp", Double.parseDouble(SToTemperature))
                                    .put("product",productId)
                                    .put("uom", uomId)
                                    .put("id",replaceid)
                                    .put("descprition",SRequirementDetails));

                            replace="0";

                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadEnquirySending_url, jsonObject, true);

                        }

                    }catch (Exception e){}
                }
            }
        });
        return view;
    }

    private void productnameRequest() throws Exception {

        Home.crmLeadEnquiryProduct_url = "Product?"+new Constants().USER_N_PASSWORD+"&_sortBy=name%20desc";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.crmLeadEnquiryProduct_url, true);

    }

    private void crmLeadEnquiryAllRecordRequest() throws Exception {

        Home.getCrmLeadEnquiryAllRecords_url= "GCRM_Enquiry?"+new Constants().USER_N_PASSWORD+"&_sortBy=updated%20desc";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.getCrmLeadEnquiryAllRecords_url, true);

    }

    public void enquiryAllRecords(JSONObject json){
        try {
            jsonEnquiryAllRecords=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                ProductName.setText("");
                UOM.setText("");
                Quantity.setText("");
                FromTemperature.setText("");
                ToTemperature.setText("");
                RequirementDetails.setText("");
                CLocation.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void productResponse(JSONObject json){
        try {
            jsonProductArray=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enquiry_final_response(JSONObject json){

        try {

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n\nSearch Key :"+searchkey+"\n\n");

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {

                        CRMLeadEnquiryList fragment = new CRMLeadEnquiryList();
                        Home.crmLeadEnquiryList = (CRMLeadEnquiryList) fragment;
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

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText
    EditText inputSearch2;

    HashMap<String,String> locationHp=new HashMap<>();

    public void locationDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> locationSpinner = new ArrayList<String>();
        JSONObject jsonObject= Constants.jsonLoginResponse;
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
                CLocation.setText(str);
                orgIddd=locationHp.get(str);
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

    public void productDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> productSpinner = new ArrayList<String>();
        final HashMap<String,String> productIdHp=new HashMap<>();
        final HashMap<String,String> productIdHp2=new HashMap<>();
        final HashMap<String,String> uomIdHp=new HashMap<>();

        JSONArray jsonP=new JSONArray();
        jsonP=jsonProductArray;

        for(int i=0;i<jsonP.length();i++)
        {
            productSpinner.add(jsonP.optJSONObject(i).optString("name"));
            productIdHp.put(jsonP.optJSONObject(i).optString("name"),jsonP.optJSONObject(i).optString("uOM$_identifier"));
            productIdHp2.put(jsonP.optJSONObject(i).optString("name"),jsonP.optJSONObject(i).optString("id"));
            uomIdHp.put(jsonP.optJSONObject(i).optString("name"),jsonP.optJSONObject(i).optString("uOM"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, productSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                ProductName.setText(str);
                orgIdd=productIdHp.get(str);
                productId=productIdHp2.get(str);
                uomId=uomIdHp.get(str);
                UOM.setText(orgIdd);
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
