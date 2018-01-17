package ssg1.gubba1.gubba1.g.Fragments;

import android.app.AlertDialog;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class SlotBookingProduct extends Fragment {

    View view;

    EditText Location,Product,Hybrid,Batchnumber,CustomerDepartment,Quantity,NoofPackages;
    TextInputLayout LLocation,LProduct,LHybrid,LBatchnumber,LCustomerDepartment,LQuantity,LNoofPackages;
    Button Save;
    String SProduct,SHybrid,SBatchnumber,SCustomerDepartment,SQuantity,SNoofPackages;

    CallWebService service;
    FragmentTransaction fragmentTransaction;

    String locationstring,locationid,id,customerdepartmentid,productId;

    JSONArray jsonCustomerDepartmentArray,jsonProductArray,jsonHybridArray,jsonslottimeArray;

    String replace="0",prealertid,bagtype,fromtime,updateid;

    SharedPref sp;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_product, null);

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
        view = inflater.inflate(R.layout.activity_product, container, false);

        service = new CallWebService(getActivity());
        jsonProductArray=new JSONArray();
        jsonHybridArray=new JSONArray();
        jsonslottimeArray=new JSONArray();

        sp = new SharedPref(getActivity());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow").equals("0"))
                {
                    sp.removeData("loginshow");
                    sp.writeString("loginshow", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow2").equals("0"))
                {
                    sp.removeData("loginshow2");
                    sp.writeString("loginshow2", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Location= (EditText) view.findViewById(R.id.input_location);
        Product= (EditText) view.findViewById(R.id.input_product);
        Hybrid= (EditText) view.findViewById(R.id.input_hybrid);
        Batchnumber = (EditText) view.findViewById(R.id.input_batch_number);
        CustomerDepartment = (EditText) view.findViewById(R.id.input_customer_department);
        Quantity = (EditText) view.findViewById(R.id.input_quantity);
        NoofPackages = (EditText) view.findViewById(R.id.input_no_of_packages);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LLocation = (TextInputLayout) view.findViewById(R.id.input_layout_location);
        LProduct = (TextInputLayout) view.findViewById(R.id.input_layout_product);
        LHybrid = (TextInputLayout) view.findViewById(R.id.input_layout_hybrid);
        LBatchnumber = (TextInputLayout) view.findViewById(R.id.input_layout_batch_number);
        LCustomerDepartment = (TextInputLayout) view.findViewById(R.id.input_layout_customer_department);
        LQuantity = (TextInputLayout) view.findViewById(R.id.input_layout_quantity);
        LNoofPackages = (TextInputLayout) view.findViewById(R.id.input_layout_no_of_packages);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Location.setTypeface(UbuntuFont);
        Product.setTypeface(UbuntuFont);
        Hybrid.setTypeface(UbuntuFont);
        Batchnumber.setTypeface(UbuntuFont);
        CustomerDepartment.setTypeface(UbuntuFont);
        Quantity.setTypeface(UbuntuFont);
        NoofPackages.setTypeface(UbuntuFont);

        LLocation.setTypeface(UbuntuFont);
        LProduct.setTypeface(UbuntuFont);
        LHybrid.setTypeface(UbuntuFont);
        LBatchnumber.setTypeface(UbuntuFont);
        LCustomerDepartment.setTypeface(UbuntuFont);
        LQuantity.setTypeface(UbuntuFont);
        LNoofPackages.setTypeface(UbuntuFont);

        try {
            customerdepartmentrequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            productnameRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SlotBookingProductList slotBookingProductList=new SlotBookingProductList();
        locationstring=slotBookingProductList.locationstring;
        locationid=slotBookingProductList.locationid;
        prealertid=slotBookingProductList.prealertid;

        try
        {
            Product.setText(getArguments().getString("product"));
            productId=getArguments().getString("productid");
            Hybrid.setText(getArguments().getString("hybrid"));
            Batchnumber.setText(getArguments().getString("batchno"));
            CustomerDepartment.setText(getArguments().getString("customerdepartment"));
            customerdepartmentid=getArguments().getString("customerdepartmentid");
            Quantity.setText(getArguments().getString("quantity"));
            NoofPackages.setText(getArguments().getString("packagingunits"));
            replace=getArguments().getString("replace");
            id=getArguments().getString("id");
            bagtype=getArguments().getString("bagtype");
        }catch (Exception e){}

        try
        {
            locationstring=getArguments().getString("organization");
            replace=getArguments().getString("replace");
            locationid=getArguments().getString("organizationid");
            prealertid=getArguments().getString("productid");
            String fromtimee=getArguments().getString("fromtime");
            String[] parts = fromtimee.split(":");
            String part1 = parts[0]; // 004
            String part2 = parts[1];
            fromtime=part1+":"+part2;
            updateid=getArguments().getString("updateid");

        }catch (Exception e){}

        Location.setText(locationstring);

        CustomerDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerdepartmentdialogbox();
            }
        });

        Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDialogBox();
            }
        });

        Hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hybridDialogBox();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SProduct=Product.getText().toString();
                SHybrid=Hybrid.getText().toString();
                SBatchnumber=Batchnumber.getText().toString();
                SCustomerDepartment=CustomerDepartment.getText().toString();
                SQuantity=Quantity.getText().toString();
                SNoofPackages=NoofPackages.getText().toString();

                if(TextUtils.isEmpty(SProduct)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Product is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SBatchnumber)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Batch number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCustomerDepartment)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Customer Department is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SQuantity)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Quantity is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SNoofPackages)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"No of Packages is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SProduct)) && !(TextUtils.isEmpty(SBatchnumber)) && !(TextUtils.isEmpty(SCustomerDepartment)) && !(TextUtils.isEmpty(SQuantity)) && !(TextUtils.isEmpty(SNoofPackages)))
                {

                    if (TextUtils.isEmpty(SQuantity))
                    {
                        SQuantity="0";
                    }

                    if (TextUtils.isEmpty(SNoofPackages))
                    {
                        SNoofPackages="0";
                    }

                    Home.slotbookingProductlast_url = "sqt_prealert_product?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    if (replace.equalsIgnoreCase("0"))
                    {
                        try {
                            jsonObject.put("data", new JSONObject()
                                    .put("organization", locationid)
                                    .put("product", productId)
                                    .put("bpproductHybrid", SHybrid)
                                    .put("batchno", SBatchnumber)
                                    .put("sQTCustomerdepartment", customerdepartmentid)
                                    .put("quantity", Double.parseDouble(SQuantity))
                                    .put("sQTPrealert", prealertid)
                                    .put("packagingunits", Double.parseDouble(SNoofPackages)));

                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.slotbookingProductlast_url, jsonObject, true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(replace.equalsIgnoreCase("1"))
                    {

                        try {
                            jsonObject.put("data", new JSONObject()
                                    .put("organization", locationid)
                                    .put("product", productId)
                                    .put("bpproductHybrid", SHybrid)
                                    .put("batchno", SBatchnumber)
                                    .put("sQTCustomerdepartment", customerdepartmentid)
                                    .put("quantity", Double.parseDouble(SQuantity))
                                    .put("sQTPrealert", prealertid)
                                    .put("id",id)
                                    .put("packagingunits", Double.parseDouble(SNoofPackages)));

                            replace="0";

                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.slotbookingProductlast_url, jsonObject, true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    }

            }
        });
        return view;
    }

    private void productnameRequest() throws Exception {

        Home.slotbookingproductProduct_url = "Product?"+new Constants().USER_N_PASSWORD+"&_sortBy=updatedBy%20desc";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.slotbookingproductProduct_url, true);

    }

    private void hybridRequest() throws Exception {

        SlotBookingProductList slotBookingProductList=new SlotBookingProductList();
        String customernameid=slotBookingProductList.customernameid;

        Home.slotbookinghybrid_url = "sqt_bpartner_product?"+new Constants().USER_N_PASSWORD+"&_where=product=%27" + productId+"%27%20and%20bpartner=%27"+customernameid+"%27%20";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.slotbookinghybrid_url, true);

    }

    public void productResponse(JSONObject json){
        try {
            jsonProductArray=json.optJSONObject("response").optJSONArray("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hybridresponse(JSONObject json){
        try {
            jsonHybridArray=json.optJSONObject("response").optJSONArray("data");
            System.out.println("hybrid"+jsonHybridArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.addbutton, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
            {
                Location.setText("");
                Product.setText("");
                Hybrid.setText("");
                Batchnumber.setText("");
                CustomerDepartment.setText("");
                Quantity.setText("");
                NoofPackages.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Fragment fragment = new SlotBookingProductList();
                Home.slotBookingProductList = (SlotBookingProductList) fragment;
                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                fragmentTransaction.commitAllowingStateLoss();
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void customerdepartmentrequest() throws Exception {

        Home.getcustomerdepartmentrecords_url = "sqt_customerdepartment?"+new Constants().USER_N_PASSWORD;

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.getcustomerdepartmentrecords_url, true);

    }

    private void totaltimerequest() throws Exception {
        SlotBooking slotBooking=new SlotBooking();
        bagtype=slotBooking.bagtype;

        Home.slottimerecords_url = "SQT_Slottime?"+new Constants().USER_N_PASSWORD+"&_where=containertype=%27"+bagtype+"%27";

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.slottimerecords_url, true);

    }

    public void customerdepartmentresponse(JSONObject json){
        try {
            jsonCustomerDepartmentArray=json.optJSONObject("response").optJSONArray("data");
            System.out.println("jsonCustomerDepartmentArray"+jsonCustomerDepartmentArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void slottimeresponse(JSONObject json){
        try {
            jsonslottimeArray=json.optJSONObject("response").optJSONArray("data");
            System.out.println("jsonslottimeArray"+jsonslottimeArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void slotbookingproductresponse(JSONObject json) {

        /*try {
            totaltimerequest();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*try {
            String totime=jsonslottimeArray.optJSONObject(0).optString("buffertime");
            String finalll=SlotBooking.finall;
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date d = df.parse(fromtime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, Integer.valueOf(totime)*Integer.valueOf(SQuantity));
            String newTime = df.format(cal.getTime());
            System.out.println("timee"+newTime);
            String totaltime=Integer.valueOf(totime)*Integer.valueOf(SQuantity)+"";

            Home.slotbookingUpdate_url = "sqt_prealert?" + new Constants().USER_N_PASSWORD;
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("data", new JSONObject()
                            .put("rescheduledfromtime", newTime+":00")
                            .put("totaltime", totaltime)
                            .put("id", id));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.slotbookingUpdate_url, jsonObject, true);

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n");
            builder.setCancelable(false);

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();

        }catch (Exception e){e.printStackTrace();}*/

        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n");
            builder.setCancelable(false);

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Location.setText("");
        Product.setText("");
        Hybrid.setText("");
        Batchnumber.setText("");
        CustomerDepartment.setText("");
        Quantity.setText("");
        NoofPackages.setText("");

    }



    public void customerdepartmentdialogbox() {

        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> contactsSpinner = new ArrayList<String>();

        final HashMap<String,String> customerdepartmentHp=new HashMap<>();

        for(int i=0;i<jsonCustomerDepartmentArray.length();i++)
        {
            contactsSpinner.add(jsonCustomerDepartmentArray.optJSONObject(i).optString("name"));
            customerdepartmentHp.put(jsonCustomerDepartmentArray.optJSONObject(i).optString("name"),jsonCustomerDepartmentArray.optJSONObject(i).optString("id"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, contactsSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                CustomerDepartment.setText(str);
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

    HashMap<String,String> locationHp=new HashMap<>();

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText
    EditText inputSearch2;

    public void productDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> productSpinner = new ArrayList<String>();
        final HashMap<String,String> productIdHp2=new HashMap<>();

        JSONArray jsonP=new JSONArray();
        jsonP=jsonProductArray;

        for(int i=0;i<jsonP.length();i++)
        {
            productSpinner.add(jsonP.optJSONObject(i).optString("name"));
            productIdHp2.put(jsonP.optJSONObject(i).optString("name"),jsonP.optJSONObject(i).optString("id"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, productSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Product.setText(str);
                productId=productIdHp2.get(str);
                dialog2.dismiss();
                try {
                    hybridRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

    public void hybridDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> hybridSpinner = new ArrayList<String>();

        JSONArray jsonP=new JSONArray();
        jsonP=jsonHybridArray;

        for(int i=0;i<jsonP.length();i++)
        {
            hybridSpinner.add(jsonP.optJSONObject(i).optString("hybrid"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, hybridSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Hybrid.setText(str);
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

    protected void displayTutoo() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_slotbookingproductt)
                .setFitsSystemWindows(true)
                .on(R.id.list)
                .addRoundRect()
                .withBorder()
                .show();
    }

}
