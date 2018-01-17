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

public class CRMLeadAddress extends Fragment {

    View view;


    EditText AddressLine1,AddressLine2,City,Pincode,Region,Country,InvoiceAddress;
    Button Next;
    String SAddressLine1,SAddressLine2,SCity,SPincode,SRegion,SCountry,SInvoiceAddress,CCountry,CRegion;
    String SSAddressLine1,SSAddressLine2,SSCity,SSPincode,SSRegion,SSCountry,SSInvoiceAddress;

    JSONArray jsonArray,jsonAddressAllRecords,jsonArrayregion;
    String[] country;

    FragmentTransaction fragmentTransaction;

    String orgIddd,orgIdd,crmLeadId,productId,uomId,location,replaceid,searchkey,addressid;

    int matched=0;

    CallWebService service;

    int countryy=208;

    String regioncode;

    String replace="0";

    SharedPref sp;

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

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        jsonArray=new JSONArray();
        jsonArrayregion=new JSONArray();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow14").equals("0"))
                {
                    sp.removeData("loginshow14");
                    sp.writeString("loginshow14", "0");

                    displayTuto();

                }
            }
        }, 500);

        CRMLeadAddressList crmLeadAddressList=new CRMLeadAddressList();
        crmLeadId=crmLeadAddressList.crmLeadId;
        searchkey=crmLeadAddressList.searchkey;

        AddressLine1 = (EditText) view.findViewById(R.id.input_addressline1);
        AddressLine2 = (EditText) view.findViewById(R.id.input_addressline2);
        City = (EditText) view.findViewById(R.id.input_city);
        Pincode = (EditText) view.findViewById(R.id.input_pincode);
        Region = (EditText) view.findViewById(R.id.input_region);
        Country = (EditText) view.findViewById(R.id.input_country);
        InvoiceAddress = (EditText) view.findViewById(R.id.input_invoiceaddress);
        Next=(Button) view.findViewById(R.id.next) ;

        jsonAddressAllRecords=new JSONArray();

        try{
            Home.mToolbar.setTitle(" ");
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

        countryrequest();
        regionrequest();

        try {
            addressid=getArguments().getString("addressid");
            SSAddressLine1=getArguments().getString("addressline1");
            SSAddressLine2=getArguments().getString("addressline2");
            SSCity=getArguments().getString("city");
            SSPincode=getArguments().getString("pincode");
            SSRegion=getArguments().getString("region");
            SSCountry=getArguments().getString("country");
            SSInvoiceAddress=getArguments().getString("invoiceaddress");
            replace=getArguments().getString("replace");

        }catch (Exception e){}

        AddressLine1.setText(SSAddressLine1);
        AddressLine2.setText(SSAddressLine2);
        City.setText(SSCity);
        Pincode.setText(SSPincode);
        Region.setText(SSRegion);
        Country.setText(SSCountry);
        InvoiceAddress.setText(SSInvoiceAddress);

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
                CRegion=Region.getText().toString();
                CCountry=Country.getText().toString();
                SInvoiceAddress=InvoiceAddress.getText().toString();
                    if(TextUtils.isEmpty(SAddressLine1)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Address Line 1 is Mandatory",Snackbar.LENGTH_LONG).show();
                        AddressLine1.setError("Address Line 1 is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SAddressLine2)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Address Line 2 is Mandatory",Snackbar.LENGTH_LONG).show();
                        AddressLine2.setError("Address Line 2 is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SCity)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"City is Mandatory",Snackbar.LENGTH_LONG).show();
                        City.setError("City is Mandatory");
                    }
                    else if(TextUtils.isEmpty(SPincode)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Pincode is Mandatory",Snackbar.LENGTH_LONG).show();
                        Pincode.setError("Pincode is Mandatory");
                    }
                    else if(TextUtils.isEmpty(CRegion)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Region is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if(TextUtils.isEmpty(CCountry)){
                        Snackbar.make(getActivity().findViewById(android.R.id.content),"Country is Mandatory",Snackbar.LENGTH_LONG).show();
                    }
                    else if (!(TextUtils.isEmpty(SAddressLine1)) && !(TextUtils.isEmpty(SAddressLine2)) && !(TextUtils.isEmpty(SCity)) && !(TextUtils.isEmpty(SPincode)) && !(TextUtils.isEmpty(CRegion)) && !(TextUtils.isEmpty(CCountry)) && !(TextUtils.isEmpty(SInvoiceAddress)))
                    {

                        Home.crmLeadAddressSending_url = "GCRM_Address?" + new Constants().USER_N_PASSWORD;
                        JSONObject jsonObject = new JSONObject();

                        try {

                            if (replace.equalsIgnoreCase("0"))
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("gcrmLead", crmLeadId)
                                        .put("organization", location)
                                        .put("invoiceaddress", SInvoiceAddress)
                                        .put("country", SCountry)
                                        .put("region", SRegion)
                                        .put("pincode",SPincode)
                                        .put("city", SCity)
                                        .put("addressline1", SAddressLine1)
                                        .put("addressline2", SAddressLine2));

                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadAddressSending_url, jsonObject, true);

                            }
                            else
                            {
                                jsonObject.put("data", new JSONObject()
                                        .put("gcrmLead", crmLeadId)
                                        .put("city", SCity)
                                        .put("organization", location)
                                        .put("invoiceaddress", SInvoiceAddress)
                                        .put("country", SCountry)
                                        .put("region", SRegion)
                                        .put("pincode",SPincode)
                                        .put("addressline1", SAddressLine1)
                                        .put("id",replaceid)
                                        .put("addressline2", SAddressLine2));

                                replace="0";

                                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.crmLeadAddressSending_url, jsonObject, true);


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

    public void InvoiceAddressDialogbox()
    {
        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("true");
        Items.add("false");

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

    public void countryResponse(JSONObject jsonObject)
    {
        try {
            jsonArray=jsonObject.optJSONObject("response").optJSONArray("data");
            System.out.println(jsonArray.length()+"json length");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void regionResponse(JSONObject jsonObject)
    {
        try {
            jsonArrayregion=jsonObject.optJSONObject("response").optJSONArray("data");
            System.out.println(jsonArrayregion.length()+"json length");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crmLeadAddressAllRecordRequest() throws Exception {

        Home.getCrmLeadAddressAllRecords_url = "GCRM_Address?"+new Constants().USER_N_PASSWORD+"&_sortBy=updated%20desc";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.getCrmLeadAddressAllRecords_url, true);

    }

    public void addressAllRecords(JSONObject json){
        try {
            jsonAddressAllRecords=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void countryrequest()
    {
        Home.crmLeadCountry_url = "Country?"+new Constants().USER_N_PASSWORD+"&_sortBy=creationDate%20desc";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.crmLeadCountry_url, true);
    }

    public void regionrequest()
    {
        Home.crmLeadRegion_url = "Region?"+new Constants().USER_N_PASSWORD+"&_where=country=%27" + countryy+"%27%20";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.crmLeadRegion_url, true);
    }

    public void CountryDilagbox()
    {

        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        ArrayList<String> countrySpinner = new ArrayList<String>();

        final HashMap<String,String> countryHp=new HashMap<>();

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        System.out.println(jsonArray.length()+"json length");
        for (int i=0;i<jsonArray.length();i++)
        {
            countrySpinner.add(jsonArray.optJSONObject(i).optString("_identifier"));
            countryHp.put(jsonArray.optJSONObject(i).optString("_identifier"),jsonArray.optJSONObject(i).optString("id"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, countrySpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Country.setText(str);
                regioncode=countryHp.get(str);
                countryy=Integer.valueOf(regioncode);
                SCountry=regioncode;
                regionrequest();
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

        ArrayList<String> regionSpinner = new ArrayList<String>();

        final HashMap<String,String> regionHp=new HashMap<>();

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        System.out.println(jsonArrayregion.length()+"json length");
        for (int i=0;i<jsonArrayregion.length();i++)
        {
            regionSpinner.add(jsonArrayregion.optJSONObject(i).optString("_identifier"));
            regionHp.put(jsonArrayregion.optJSONObject(i).optString("_identifier"),jsonArrayregion.optJSONObject(i).optString("id"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, regionSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Region.setText(str);
                SRegion=regionHp.get(str);
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

                        CRMLeadAddressList fragment = new CRMLeadAddressList();
                        Home.crmLeadAddressList = (CRMLeadAddressList) fragment;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
            {
                AddressLine1.setText("");
                AddressLine2.setText("");
                City.setText("");
                Pincode.setText("");
                Region.setText("");
                Country.setText("");
                InvoiceAddress.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
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
