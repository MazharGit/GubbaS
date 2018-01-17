package ssg1.gubba1.gubba1.g.Fragments;

import android.app.DatePickerDialog;
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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class InwardMemoProduct extends Fragment {

    View view;

    EditText ProductCategory,ProductName,Hybrid,CustomerDepartment,BatchNo,MoisturePercent,HandlingQuantityDC,HandlingQuantityActual,HandlingUOM,BillingQuantity,BillingUOM,Chamber,BayNumber,ManufacturingDate,Grill,ExpiryDate;
    TextInputLayout LProductCategory,LProductName,LHybrid,LCustomerDepartment,LBatchNo,LMoisturePercent,LHandlingQuantityDC,LHandlingQuantityActual,LHandlingUOM,LBillingQuantity,LBillingUOM,LChamber,LBayNumber,LManufacturingDate,LGrill,LExpiryDate;
    Button Save;
    String SProductCategory,SProductName,SHybrid,SCustomerDepartment,SBatchNo,SMoisturePercent,SHandlingQuantityDC,SHandlingQuantityActual,SHandlingUOM,SBillingQuantity,SBillingUOM,SChamber,SBayNumber,SManufacturingDate,SGrill,SExpiryDate;
    String SRProductCategory,SRProductName,SRHybrid,SRCustomerDepartment,SRBatchNo,SRMoisturePercent,SRHandlingQuantityDC,SRHandlingQuantityActual,SRHandlingUOM,SRBillingQuantity,SRBillingUOM,SRChamber,SRBayNumber,SRManufacturingDate,SRGrill,SRExpiryDate;

    CallWebService service;

    FragmentTransaction fragmentTransaction;

    SharedPref sp;

    JSONArray jsonArray;

    String id;

    String replace="0",outputString;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_inwardmemoproduct, null);

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
        view = inflater.inflate(R.layout.activity_inwardmemoproduct, container, false);

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        jsonArray=new JSONArray();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1128").equals("0"))
                {
                    sp.removeData("loginshow1128");
                    sp.writeString("loginshow1128", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1129").equals("0"))
                {
                    sp.removeData("loginshow1129");
                    sp.writeString("loginshow1129", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        ProductCategory=(EditText) view.findViewById(R.id.input_productcategory);
        ProductName= (EditText) view.findViewById(R.id.input_productname);
        Hybrid= (EditText) view.findViewById(R.id.input_hybrid);
        CustomerDepartment= (EditText) view.findViewById(R.id.input_customerdepartment);
        BatchNo = (EditText) view.findViewById(R.id.input_batchno);
        MoisturePercent = (EditText) view.findViewById(R.id.input_moisturepercent);
        HandlingQuantityDC = (EditText) view.findViewById(R.id.input_handlingqtydc);
        HandlingQuantityActual = (EditText) view.findViewById(R.id.input_handlingqtyactual);
        HandlingUOM = (EditText) view.findViewById(R.id.input_handlinguom);
        BillingQuantity = (EditText) view.findViewById(R.id.input_billingqty);
        BillingUOM=(EditText) view.findViewById(R.id.input_billinguom);
        Chamber=(EditText) view.findViewById(R.id.input_chamber);
        BayNumber= (EditText) view.findViewById(R.id.input_baynumber);
        ManufacturingDate = (EditText) view.findViewById(R.id.input_manufacturingdate);
        Grill = (EditText) view.findViewById(R.id.input_grill);
        ExpiryDate = (EditText) view.findViewById(R.id.input_expirydate);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LProductCategory=(TextInputLayout) view.findViewById(R.id.input_layout_productcategory);
        LProductName= (TextInputLayout) view.findViewById(R.id.input_layout_productname);
        LHybrid= (TextInputLayout) view.findViewById(R.id.input_layout_hybrid);
        LCustomerDepartment= (TextInputLayout) view.findViewById(R.id.input_layout_customerdepartment);
        LBatchNo = (TextInputLayout) view.findViewById(R.id.input_layout_batchno);
        LMoisturePercent = (TextInputLayout) view.findViewById(R.id.input_layout_moisturepercent);
        LHandlingQuantityDC = (TextInputLayout) view.findViewById(R.id.input_layout_handlingqtydc);
        LHandlingQuantityActual = (TextInputLayout) view.findViewById(R.id.input_layout_handlingqtyactual);
        LHandlingUOM = (TextInputLayout) view.findViewById(R.id.input_layout_handlinguom);
        LBillingQuantity = (TextInputLayout) view.findViewById(R.id.input_layout_billingqty);
        LBillingUOM=(TextInputLayout) view.findViewById(R.id.input_layout_billinguom);
        LChamber=(TextInputLayout) view.findViewById(R.id.input_layout_chamber);
        LBayNumber= (TextInputLayout) view.findViewById(R.id.input_layout_baynumber);
        LManufacturingDate = (TextInputLayout) view.findViewById(R.id.input_layout_manufacturingdate);
        LGrill = (TextInputLayout) view.findViewById(R.id.input_layout_grill);
        LExpiryDate = (TextInputLayout) view.findViewById(R.id.input_layout_expirydate);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        ProductCategory.setTypeface(UbuntuFont);
        ProductName.setTypeface(UbuntuFont);
        Hybrid.setTypeface(UbuntuFont);
        CustomerDepartment.setTypeface(UbuntuFont);
        BatchNo.setTypeface(UbuntuFont);
        MoisturePercent.setTypeface(UbuntuFont);
        HandlingQuantityDC.setTypeface(UbuntuFont);
        HandlingQuantityActual.setTypeface(UbuntuFont);
        HandlingUOM.setTypeface(UbuntuFont);
        BillingQuantity.setTypeface(UbuntuFont);
        BillingUOM.setTypeface(UbuntuFont);
        Chamber.setTypeface(UbuntuFont);
        BayNumber.setTypeface(UbuntuFont);
        ManufacturingDate.setTypeface(UbuntuFont);
        Grill.setTypeface(UbuntuFont);
        ExpiryDate.setTypeface(UbuntuFont);

        LProductCategory.setTypeface(UbuntuFont);
        LProductName.setTypeface(UbuntuFont);
        LHybrid.setTypeface(UbuntuFont);
        LCustomerDepartment.setTypeface(UbuntuFont);
        LBatchNo.setTypeface(UbuntuFont);
        LMoisturePercent.setTypeface(UbuntuFont);
        LHandlingQuantityDC.setTypeface(UbuntuFont);
        LHandlingQuantityActual.setTypeface(UbuntuFont);
        LHandlingUOM.setTypeface(UbuntuFont);
        LBillingQuantity.setTypeface(UbuntuFont);
        LBillingUOM.setTypeface(UbuntuFont);
        LChamber.setTypeface(UbuntuFont);
        LBayNumber.setTypeface(UbuntuFont);
        LManufacturingDate.setTypeface(UbuntuFont);
        LGrill.setTypeface(UbuntuFont);
        LExpiryDate.setTypeface(UbuntuFont);

        try
        {

            SRProductCategory=getArguments().getString("ProductCategory");
            SRProductName= getArguments().getString("ProductName");
            SRHybrid= getArguments().getString("Hybrid");
            SRCustomerDepartment= getArguments().getString("CustomerDepartment");
            SRBatchNo = getArguments().getString("BatchNumber");
            SRMoisturePercent = getArguments().getString("MoistureDepartment");
            SRHandlingQuantityDC = getArguments().getString("HandlingQtyDC");
            SRHandlingQuantityActual = getArguments().getString("HandlingQtyActual");
            SRHandlingUOM = getArguments().getString("HandlingUOM");
            SRBillingQuantity = getArguments().getString("BillingQuantity");
            SRBillingUOM= getArguments().getString("BillingUOM");
            SRChamber= getArguments().getString("Chamber");
            SRBayNumber= getArguments().getString("BayNumber");
            SRManufacturingDate = getArguments().getString("ManufacturingDate");
            SRGrill = getArguments().getString("Grill");
            SRExpiryDate = getArguments().getString("ExpiryDate");
            replace= getArguments().getString("replace");
            id=getArguments().getString("id");


        }catch (Exception e){}

        ProductCategory.setText(SRProductCategory);
        ProductName.setText(SRProductName);
        Hybrid.setText(SRHybrid);
        CustomerDepartment.setText(SRCustomerDepartment);
        BatchNo.setText(SRBatchNo);
        MoisturePercent.setText(SRMoisturePercent);
        HandlingQuantityDC.setText(SRHandlingQuantityDC);
        HandlingQuantityActual.setText(SRHandlingQuantityActual);
        HandlingUOM.setText(SRHandlingUOM);
        BillingQuantity.setText(SRBillingQuantity);
        BillingUOM.setText(SRBillingUOM);
        Chamber.setText(SRChamber);
        BayNumber.setText(SRBayNumber);
        ManufacturingDate.setText(SRManufacturingDate);
        Grill.setText(SRGrill);
        ExpiryDate.setText(SRExpiryDate);

        Grill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Grill In/Out is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        ExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Expiry Date is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        ManufacturingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
            }
        });


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SProductCategory=ProductCategory.getText().toString();
                SProductName=ProductName.getText().toString();
                SHybrid=Hybrid.getText().toString();
                SCustomerDepartment = CustomerDepartment.getText().toString();
                SBatchNo =BatchNo.getText().toString();
                SMoisturePercent =MoisturePercent.getText().toString();
                SHandlingQuantityDC =HandlingQuantityDC.getText().toString();
                SHandlingQuantityActual =HandlingQuantityActual.getText().toString();
                SHandlingUOM =HandlingUOM.getText().toString();
                SBillingQuantity=BillingQuantity.getText().toString();
                SBillingUOM=BillingUOM.getText().toString();
                SChamber=Chamber.getText().toString();
                SBayNumber =BayNumber.getText().toString();
                SManufacturingDate =ManufacturingDate.getText().toString();
                SGrill =Grill.getText().toString();
                SExpiryDate =ExpiryDate.getText().toString();

                if(TextUtils.isEmpty(SProductCategory)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Product Category is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SProductName)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Product Name Type is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SHybrid)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Hybrid is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCustomerDepartment)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Customer Department is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SBatchNo)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Batch Number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SBillingQuantity)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Billing Quantity is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SHandlingQuantityDC)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Handling Quantity DC is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SHandlingQuantityActual)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Handling Quantity Actual is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SBayNumber)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Bay Number is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SProductCategory)) && !(TextUtils.isEmpty(SProductName)) && !(TextUtils.isEmpty(SHybrid)) && !(TextUtils.isEmpty(SCustomerDepartment)) && !(TextUtils.isEmpty(SBatchNo)) && !(TextUtils.isEmpty(SHandlingQuantityDC)) && !(TextUtils.isEmpty(SHandlingQuantityActual)) && !(TextUtils.isEmpty(SBayNumber)))
                {

                    if (SBatchNo.equalsIgnoreCase("null"))
                    {
                        SBatchNo="0";
                    }

                    if (SMoisturePercent.equalsIgnoreCase("null"))
                    {
                        SMoisturePercent="0";
                    }

                    if (SHandlingQuantityActual.equalsIgnoreCase("null"))
                    {
                        SHandlingQuantityActual="";
                    }

                    if (SHandlingQuantityDC.equalsIgnoreCase("null"))
                    {
                        SHandlingQuantityDC="";
                    }

                    if (SBillingQuantity.equalsIgnoreCase("null"))
                    {
                        SBillingQuantity="";
                    }

                    /*Home.inwardmemoproduct_url = "sqt_inwdproduct?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    try {

                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("documenttype",SDocumentType)
                                    .put("sQTBpcustomer", customernameid)
                                    .put("pestinflected", SPestInfected)
                                    .put("temp1", Double.parseDouble(STemperature1))
                                    .put("temp2", Double.parseDouble(STemperature2))
                                    .put("packingcondition", SPackingCondition)
                                    .put("seasoncode",Double.parseDouble(SSeasonCode))
                                    .put("sQTGateentry",gateentrynumberid)
                                    .put("user", customerrepresentativeid)
                                    .put("status", SStatus)
                                    .put("sQTCbpartner", haalicontractorid)
                                    .put("noofhamali", Double.parseDouble(SNoofHamali))
                                    .put("spillageqty", Double.parseDouble(SSpillageQuantity))
                                    .put("vehicalno", vehicalno)
                                    .put("id",id)
                                    .put("hamalimamulcharges", SHamaliMamulCharges));
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.inwardmemo_url, jsonObject, true);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/

                    }
            }
        });
        return view;
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
                ProductCategory.setText("");
                ProductName.setText("");
                Hybrid.setText("");
                CustomerDepartment.setText("");
                BatchNo.setText("");
                MoisturePercent.setText("");
                HandlingQuantityDC.setText("");
                HandlingQuantityActual.setText("");
                HandlingUOM.setText("");
                BillingQuantity.setText("");
                BillingUOM.setText("");
                Chamber.setText("");
                BayNumber.setText("");
                ManufacturingDate.setText("");
                Grill.setText("");
                ExpiryDate.setText("");
                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Bundle args = new Bundle();
                Fragment fragment = new InwardmemoProductList();
                Home.inwardmemoProductList = (InwardmemoProductList) fragment;
                fragment.setArguments(args);
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


    public void inwardmemoproductResponse(JSONObject json){
        try {

            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setMessage("Successfully Inserted into ERP\n\n");

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {

                        }catch (Exception e){
                    }
                }
            });

            builder.show();

            ProductCategory.setText("");
            ProductName.setText("");
            Hybrid.setText("");
            CustomerDepartment.setText("");
            BatchNo.setText("");
            MoisturePercent.setText("");
            HandlingQuantityDC.setText("");
            HandlingQuantityActual.setText("");
            HandlingUOM.setText("");
            BillingQuantity.setText("");
            BillingUOM.setText("");
            Chamber.setText("");
            BayNumber.setText("");
            ManufacturingDate.setText("");
            Grill.setText("");
            ExpiryDate.setText("");

        }catch (Exception e){e.printStackTrace();}
    }

    Dialog dialog2;
    private ListView lv2;

    // Listview Adapter
    ArrayAdapter<String> adapter2;

    // Search EditText
    EditText inputSearch2;

    protected void displayTuto() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_firstcontactform)
                .setFitsSystemWindows(true)
                .on(R.id.add)
                .addCircle()
                .withBorder()
                .show();
    }

    protected void displayTutoo() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_inward)
                .setFitsSystemWindows(true)
                .on(R.id.list)
                .addRoundRect()
                .withBorder()
                .show();
    }

    private int mYear, mMonth, mDay, mHour, mMinute, mSec;
    String outputDateForServer = null;

    private void getDate() {
        try {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {


                            String date = Integer.toString(dayOfMonth) + "-" + Integer.toString((monthOfYear + 1)) + "-" + Integer.toString(year);
                            Date date12 = null;
                            DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");

                            try {
                                if (!(date.equalsIgnoreCase(null)) && !(date.equalsIgnoreCase("")))
                                    date12 = inputFormat.parse(date);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                            outputString = null;
                            if (date12 != null)
                                outputString = outputFormat.format(date12);

                            ManufacturingDate.setText(outputString);


                            DateFormat outputFormatForServer = new SimpleDateFormat("yyyy-MM-dd");

                            if (date12 != null)
                                outputDateForServer = outputFormatForServer.format(date12);
                        }
                    }, mYear , mMonth, mDay);
            //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse((mDay)+"/"+(mMonth+1)+"/"+(mYear));
            datePickerDialog.getDatePicker().setMinDate((d.getTime()));

            datePickerDialog.show();
        }catch (Exception e){e.printStackTrace();}


    }

}