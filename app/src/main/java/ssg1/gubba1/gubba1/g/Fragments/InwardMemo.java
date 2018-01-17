package ssg1.gubba1.gubba1.g.Fragments;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class InwardMemo extends Fragment {

    View view;

    EditText Organization,Location,DocumentType,DocumentNo,Customername,CustomerAddress,DockInTime,DockOutTime,DCNo,PestInfected,Temperature1,Temperature2,PackingCondition,SeasonCode,CustomerRepresentative,GateEntryNo,TotalPackagingUnits,TotalQuantity,Status,HamaliContractor,NoofHamali,SpillageQuantity,VehicleNumber,HamaliMamulCharges;
    TextInputLayout LOrganization,LLocation,LDocumentType,LDocumentNo,LCustomername,LCustomerAddress,LDockInTime,LDockOutTime,LDCNo,LPestInfected,LTemperature1,LTemperature2,LPackingCondition,LSeasonCode,LCustomerRepresentative,LGateEntryNo,LTotalPackagingUnits,LTotalQuantity,LStatus,LHamaliContractor,LNoofHamali,LSpillageQuantity,LVehicleNumber,LHamaliMamulCharges;
    Button Save;
    String SOrganization,SLocation,SDocumentType,SDocumentNo,SCustomername,SCustomerAddress,SDockInTime,SDockOutTime,SDCNo,SPestInfected,STemperature1,STemperature2,SPackingCondition,SSeasonCode,SCustomerRepresentative,SGateEntryNo,STotalPackagingUnits,STotalQuantity,SStatus,SHamaliContractor,SNoofHamali,SSpillageQuantity,SVehicleNumber,SHamaliMamulCharges;
    String SROrganization,SRLocation,SRDocumentType,SRDocumentNo,SRCustomername,SRCustomerAddress,SRDockInTime,SRDockOutTime,SRDCNo,SRPestInfected,SRTemperature1,SRTemperature2,SRPackingCondition,SRSeasonCode,SRCustomerRepresentative,SRGateEntryNo,SRTotalPackagingUnits,SRTotalQuantity,SRStatus,SRHamaliContractor,SRNoofHamali,SRSpillageQuantity,SRVehicleNumber,SRHamaliMamulCharges;

    CallWebService service;

    String orgIdd;

    FragmentTransaction fragmentTransaction;

    SharedPref sp;

    JSONArray jsonArray,jsonhamali;

    String id;

    String userId,sqtid,customernameid,customeraddressid,customerrepresentativeid,gateentrynumberid,haalicontractorid,vehicalno;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_inwardmemo, null);

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
        view = inflater.inflate(R.layout.activity_inwardmemo, container, false);

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        jsonArray=new JSONArray();

        jsonhamali=new JSONArray();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1123").equals("0"))
                {
                    sp.removeData("loginshow1123");
                    sp.writeString("loginshow1123", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1124").equals("0"))
                {
                    sp.removeData("loginshow1124");
                    sp.writeString("loginshow1124", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Organization=(EditText) view.findViewById(R.id.input_organization);
        Location= (EditText) view.findViewById(R.id.input_location);
        DocumentType= (EditText) view.findViewById(R.id.input_documenttype);
        DocumentNo= (EditText) view.findViewById(R.id.input_documentno);
        Customername = (EditText) view.findViewById(R.id.input_customername);
        CustomerAddress = (EditText) view.findViewById(R.id.input_customeraddress);
        DockInTime = (EditText) view.findViewById(R.id.input_dockintime);
        DockOutTime = (EditText) view.findViewById(R.id.input_dockouttime);
        DCNo = (EditText) view.findViewById(R.id.input_dcnumber);
        PestInfected = (EditText) view.findViewById(R.id.input_pestinfected);
        Temperature1=(EditText) view.findViewById(R.id.input_temperature1);
        Temperature2=(EditText) view.findViewById(R.id.input_temperature2);
        PackingCondition= (EditText) view.findViewById(R.id.input_packingcondition);
        SeasonCode = (EditText) view.findViewById(R.id.input_seasoncode);
        CustomerRepresentative = (EditText) view.findViewById(R.id.input_customerrepresentatitve);
        GateEntryNo = (EditText) view.findViewById(R.id.input_gateentryno);
        TotalPackagingUnits = (EditText) view.findViewById(R.id.input_totalpackagingunits);
        TotalQuantity = (EditText) view.findViewById(R.id.input_totalqty);
        Status = (EditText) view.findViewById(R.id.input_status);
        HamaliContractor = (EditText) view.findViewById(R.id.input_hamalicontractor);
        NoofHamali = (EditText) view.findViewById(R.id.input_noofhamali);
        SpillageQuantity = (EditText) view.findViewById(R.id.input_spillagequantity);
        VehicleNumber = (EditText) view.findViewById(R.id.input_vehicleno);
        HamaliMamulCharges = (EditText) view.findViewById(R.id.input_hamalimamulcharges);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LOrganization=(TextInputLayout) view.findViewById(R.id.input_layout_organization);
        LLocation= (TextInputLayout) view.findViewById(R.id.input_layout_location);
        LDocumentType= (TextInputLayout) view.findViewById(R.id.input_layout_documenttype);
        LDocumentNo= (TextInputLayout) view.findViewById(R.id.input_layout_documentno);
        LCustomername = (TextInputLayout) view.findViewById(R.id.input_layout_customername);
        LCustomerAddress = (TextInputLayout) view.findViewById(R.id.input_layout_customeraddress);
        LDockInTime = (TextInputLayout) view.findViewById(R.id.input_layout_dockintime);
        LDockOutTime = (TextInputLayout) view.findViewById(R.id.input_layout_dockouttime);
        LDCNo = (TextInputLayout) view.findViewById(R.id.input_layout_dcnumber);
        LPestInfected = (TextInputLayout) view.findViewById(R.id.input_layout_pestinfected);
        LTemperature1=(TextInputLayout) view.findViewById(R.id.input_layout_temperature1);
        LTemperature2=(TextInputLayout) view.findViewById(R.id.input_layout_temperature2);
        LPackingCondition= (TextInputLayout) view.findViewById(R.id.input_layout_packingcondition);
        LSeasonCode = (TextInputLayout) view.findViewById(R.id.input_layout_seasoncode);
        LCustomerRepresentative = (TextInputLayout) view.findViewById(R.id.input_layout_customerrepresentatitve);
        LGateEntryNo = (TextInputLayout) view.findViewById(R.id.input_layout_gateentryno);
        LTotalPackagingUnits = (TextInputLayout) view.findViewById(R.id.input_layout_totalpackagingunits);
        LTotalQuantity = (TextInputLayout) view.findViewById(R.id.input_layout_totalqty);
        LStatus = (TextInputLayout) view.findViewById(R.id.input_layout_status);
        LHamaliContractor = (TextInputLayout) view.findViewById(R.id.input_layout_hamalicontractor);
        LNoofHamali = (TextInputLayout) view.findViewById(R.id.input_layout_noofhamali);
        LSpillageQuantity = (TextInputLayout) view.findViewById(R.id.input_layout_spillagequantity);
        LVehicleNumber = (TextInputLayout) view.findViewById(R.id.input_layout_vehicleno);
        LHamaliMamulCharges = (TextInputLayout) view.findViewById(R.id.input_layout_hamalimamulcharges);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Location.setTypeface(UbuntuFont);
        DocumentType.setTypeface(UbuntuFont);
        DocumentNo.setTypeface(UbuntuFont);
        Organization.setTypeface(UbuntuFont);
        Customername.setTypeface(UbuntuFont);
        CustomerAddress.setTypeface(UbuntuFont);
        DockInTime.setTypeface(UbuntuFont);
        DockOutTime.setTypeface(UbuntuFont);
        DCNo.setTypeface(UbuntuFont);
        PestInfected.setTypeface(UbuntuFont);
        Temperature1.setTypeface(UbuntuFont);
        Temperature2.setTypeface(UbuntuFont);
        PackingCondition.setTypeface(UbuntuFont);
        SeasonCode.setTypeface(UbuntuFont);
        CustomerRepresentative.setTypeface(UbuntuFont);
        GateEntryNo.setTypeface(UbuntuFont);
        TotalPackagingUnits.setTypeface(UbuntuFont);
        TotalQuantity.setTypeface(UbuntuFont);
        Status.setTypeface(UbuntuFont);
        HamaliContractor.setTypeface(UbuntuFont);
        NoofHamali.setTypeface(UbuntuFont);
        SpillageQuantity.setTypeface(UbuntuFont);
        VehicleNumber.setTypeface(UbuntuFont);
        HamaliMamulCharges.setTypeface(UbuntuFont);

        LLocation.setTypeface(UbuntuFont);
        LDocumentType.setTypeface(UbuntuFont);
        LOrganization.setTypeface(UbuntuFont);
        LCustomername.setTypeface(UbuntuFont);
        LCustomerAddress.setTypeface(UbuntuFont);
        LDockInTime.setTypeface(UbuntuFont);
        LDockOutTime.setTypeface(UbuntuFont);
        LDCNo.setTypeface(UbuntuFont);
        LPestInfected.setTypeface(UbuntuFont);
        LTemperature1.setTypeface(UbuntuFont);
        LTemperature2.setTypeface(UbuntuFont);
        LPackingCondition.setTypeface(UbuntuFont);
        LSeasonCode.setTypeface(UbuntuFont);
        LCustomerRepresentative.setTypeface(UbuntuFont);
        LGateEntryNo.setTypeface(UbuntuFont);
        LTotalPackagingUnits.setTypeface(UbuntuFont);
        LTotalQuantity.setTypeface(UbuntuFont);
        LStatus.setTypeface(UbuntuFont);
        LHamaliContractor.setTypeface(UbuntuFont);
        LDocumentNo.setTypeface(UbuntuFont);
        LNoofHamali.setTypeface(UbuntuFont);
        LSpillageQuantity.setTypeface(UbuntuFont);
        LVehicleNumber.setTypeface(UbuntuFont);
        LHamaliMamulCharges.setTypeface(UbuntuFont);

        try
        {

            SROrganization=getArguments().getString("Organization");
            sqtid=getArguments().getString("sQTOrOrganizationidg");
            SRLocation=getArguments().getString("Location");
            orgIdd=getArguments().getString("Locationid");
            SRDocumentType=getArguments().getString("DocumentType");
            SRDocumentNo=getArguments().getString("DocumentNo");
            SRCustomername =getArguments().getString("CustomerName");
            customernameid =getArguments().getString("Customerid");
            SRCustomerAddress =getArguments().getString("CustomerAddress");
            customeraddressid =getArguments().getString("CustomerAddressid");
            SRDockInTime =getArguments().getString("DockInTime");
            SRDockOutTime =getArguments().getString("DockOutTime");
            SRDCNo =getArguments().getString("DCNo");
            SRPestInfected =getArguments().getString("PestInfected");
            SRTemperature1=getArguments().getString("Temperature1");
            SRTemperature2=getArguments().getString("Temperature2");
            SRPackingCondition=getArguments().getString("PackingCondition");
            SRSeasonCode =getArguments().getString("SeasonCode");
            SRCustomerRepresentative =getArguments().getString("CustomerRepresentative");
            customerrepresentativeid =getArguments().getString("CustomerRepresentativeid");
            SRGateEntryNo =getArguments().getString("GateEntryNumber");
            gateentrynumberid =getArguments().getString("GateEntryNumberid");
            SRTotalPackagingUnits =getArguments().getString("TotalPackagingUnits");
            SRTotalQuantity =getArguments().getString("TotalQuantity");
            SRStatus =getArguments().getString("Status");
            SRHamaliContractor =getArguments().getString("HamaliContractor");
            SRSpillageQuantity =getArguments().getString("SpillageQuantity");
            haalicontractorid =getArguments().getString("Hamaliid");
            SRNoofHamali =getArguments().getString("NoofHamali");
            vehicalno =getArguments().getString("vehicalno");
            SRHamaliMamulCharges =getArguments().getString("HamaliMamulCharges");
            id=getArguments().getString("id");


        }catch (Exception e){}

        Organization.setText(SROrganization);
        Location.setText(SRLocation);
        DocumentType.setText(SRDocumentType);
        Organization.setText(SROrganization);
        Customername.setText(SRCustomername);
        CustomerAddress.setText(SRCustomerAddress);
        DockInTime.setText(SRDockInTime);
        DockOutTime.setText(SRDockOutTime);
        DCNo.setText(SRDCNo);
        PestInfected.setText(SRPestInfected);
        Temperature1.setText(SRTemperature1);
        Temperature2.setText(SRTemperature2);
        PackingCondition.setText(SRPackingCondition);
        SeasonCode.setText(SRSeasonCode);
        CustomerRepresentative.setText(SRCustomerRepresentative);
        GateEntryNo.setText(SRGateEntryNo);
        TotalPackagingUnits.setText(SRTotalPackagingUnits);
        TotalQuantity.setText(SRTotalQuantity);
        Status.setText(SRStatus);
        HamaliContractor.setText(SRHamaliContractor);
        NoofHamali.setText(SRNoofHamali);
        SpillageQuantity.setText(SRSpillageQuantity);
        DocumentNo.setText(SRDocumentNo);
        VehicleNumber.setText(vehicalno);
        HamaliMamulCharges.setText(SRHamaliMamulCharges);

        /*Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = mdformat.format(calendar.getTime());
        GateEntryDate.setText(strDate);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        String currentDateandTime = df.format(date);
        System.out.println("datee"+currentDateandTime);
        GateEntryTime.setText(currentDateandTime);*/

        Organization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Organization Field is not User Input", Snackbar.LENGTH_LONG).show();
            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationDialogBox();
            }
        });

        HamaliMamulCharges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hamalimamulchargesdialogbox();
            }
        });

        HamaliContractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hamalicontractorchargesdialogbox();
            }
        });

        VehicleNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Vehicle Number is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        GateEntryNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Gate Entry Number is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        TotalPackagingUnits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Total Packaging Units is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        TotalQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Total Quantity is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        CustomerRepresentative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerrepresentativeDilagbox();
            }
        });

        PackingCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packagingconditiondialogbox();
            }
        });

        PestInfected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pestinfecteddialoguebox();
            }
        });

        DCNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"DC Number is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        DockOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Dock Out Time is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        DockInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Dock in Time is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        DocumentNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Document Number is not user input",Snackbar.LENGTH_LONG).show();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SOrganization=Organization.getText().toString();
                SLocation=Location.getText().toString();
                SDocumentType=DocumentType.getText().toString();
                SCustomername = Customername.getText().toString();
                SCustomerAddress =CustomerAddress.getText().toString();
                SDockInTime =DockInTime.getText().toString();
                SDockOutTime =DockOutTime.getText().toString();
                SDCNo =DCNo.getText().toString();
                SPestInfected =PestInfected.getText().toString();
                STemperature1=Temperature1.getText().toString();
                STemperature2=Temperature2.getText().toString();
                SPackingCondition=PackingCondition.getText().toString();
                SSeasonCode =SeasonCode.getText().toString();
                SCustomerRepresentative =CustomerRepresentative.getText().toString();
                SGateEntryNo =GateEntryNo.getText().toString();
                STotalPackagingUnits =TotalPackagingUnits.getText().toString();
                STotalQuantity =TotalQuantity.getText().toString();
                SStatus =Status.getText().toString();
                SHamaliContractor =HamaliContractor.getText().toString();
                SNoofHamali =NoofHamali.getText().toString();
                SSpillageQuantity =SpillageQuantity.getText().toString();
                SHamaliMamulCharges =HamaliMamulCharges.getText().toString();

                if(TextUtils.isEmpty(SLocation)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Location is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SDocumentType)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Document Type is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCustomername)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Customer Name is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SCustomerRepresentative)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content)," Customer Representative is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SHamaliMamulCharges)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Hamali Mamul Charges is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SLocation)) && !(TextUtils.isEmpty(SDocumentType)) && !(TextUtils.isEmpty(SCustomername)) && !(TextUtils.isEmpty(SCustomerRepresentative)) && !(TextUtils.isEmpty(SHamaliMamulCharges)))
                {

                    if (STemperature1.equalsIgnoreCase("null"))
                    {
                        STemperature1="0";
                    }

                    if (STemperature2.equalsIgnoreCase("null"))
                    {
                        STemperature2="0";
                    }

                    if (SSeasonCode.equalsIgnoreCase("null"))
                    {
                        SSeasonCode="0";
                    }

                    if (SNoofHamali.equalsIgnoreCase("null"))
                    {
                        SNoofHamali="0";
                    }

                    if (SSpillageQuantity.equalsIgnoreCase("null"))
                    {
                        SSpillageQuantity="0";
                    }

                    if (SPackingCondition.equalsIgnoreCase("null"))
                    {
                        SPackingCondition="";
                    }

                    if (SPestInfected.equalsIgnoreCase("null"))
                    {
                        SPestInfected="";
                    }

                    if (SCustomerAddress.equalsIgnoreCase("null"))
                    {
                        SCustomerAddress="";
                    }

                    Home.inwardmemo_url = "sqt_inwardmemo?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    try {

                            jsonObject.put("data", new JSONObject()
                                    .put("organization", orgIdd)
                                    .put("documenttype",SDocumentType)
                                    .put("sQTBpcustomer", customernameid)
                                    /*.put("bpartnerLocation", customeraddressid)*/
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
                    }

                    }
            }
        });
        return view;
    }

    public void hamaliContractorResponse(JSONObject json){
        try {
            jsonhamali=json.optJSONObject("response").optJSONArray("data");
            System.out.println("jsonhamali"+jsonhamali);

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
                DocumentType.setText("");
                Organization.setText("");
                Customername.setText("");
                CustomerAddress.setText("");
                DockInTime.setText("");
                DockOutTime.setText("");
                DCNo.setText("");
                PestInfected.setText("");
                Temperature1.setText("");
                Temperature2.setText("");
                PackingCondition.setText("");
                SeasonCode.setText("");
                CustomerRepresentative.setText("");
                GateEntryNo.setText("");
                TotalPackagingUnits.setText("");
                TotalQuantity.setText("");
                Status.setText("");
                HamaliContractor.setText("");
                NoofHamali.setText("");
                SpillageQuantity.setText("");
                HamaliMamulCharges.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Bundle args = new Bundle();
                Fragment fragment = new InwardmemoList();
                Home.inwardmemoList = (InwardmemoList) fragment;
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


    public void inwardmemoResponse(JSONObject json){
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

            Location.setText("");
            DocumentType.setText("");
            Organization.setText("");
            Customername.setText("");
            CustomerAddress.setText("");
            DockInTime.setText("");
            DockOutTime.setText("");
            DCNo.setText("");
            PestInfected.setText("");
            Temperature1.setText("");
            Temperature2.setText("");
            PackingCondition.setText("");
            SeasonCode.setText("");
            CustomerRepresentative.setText("");
            GateEntryNo.setText("");
            TotalPackagingUnits.setText("");
            TotalQuantity.setText("");
            Status.setText("");
            HamaliContractor.setText("");
            NoofHamali.setText("");
            SpillageQuantity.setText("");
            HamaliMamulCharges.setText("");

        }catch (Exception e){e.printStackTrace();}
    }

    HashMap<String,String> locationHp=new HashMap<>();

    public void locationDialogBox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> locationSpinner = new ArrayList<String>();
        JSONObject jsonObject=Constants.jsonLoginResponse;
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
                Location.setText(str);
                orgIdd=locationHp.get(str);
                Home.hamalicontractorrequest_url = "sqt_hamalipricelist?"+new Constants().USER_N_PASSWORD+"&_where=organization=%27" + orgIdd+"%27";

                service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.hamalicontractorrequest_url, true);

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

    public void hamalimamulchargesdialogbox() {

        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("MamulNotApplicable");
        Items.add("PaidByGubbaonBehalfofCustomer");
        Items.add("PaidtoGubba");
        Items.add("DirectltyPaidtoHamali");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                HamaliMamulCharges.setText(str);
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

    public void packagingconditiondialogbox() {

        dialog2 = new Dialog(getActivity());

        //setting custom layout to dialog
        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> Items = new ArrayList<String>();
        Items.add("Damaged");
        Items.add("Good");

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, Items);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                PackingCondition.setText(str);
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

    HashMap<String,String> hamalicontractorHp=new HashMap<>();

    public void hamalicontractorchargesdialogbox() {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> hamaliSpinner = new ArrayList<String>();

        for(int i=0;i<jsonhamali.length();i++)
        {
            hamaliSpinner.add(jsonhamali.optJSONObject(i).optString("sQTBpartner$_identifier"));
            hamalicontractorHp.put(jsonhamali.optJSONObject(i).optString("sQTBpartner$_identifier"),jsonhamali.optJSONObject(i).optString("sQTBpartner"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, hamaliSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                HamaliContractor.setText(str);
                orgIdd=locationHp.get(str);
                Home.hamalicontractorrequest_url = "sqt_hamalipricelist?"+new Constants().USER_N_PASSWORD+"&_where=organization=%27" + orgIdd+"%27";

                service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.hamalicontractorrequest_url, true);

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

    HashMap<String,String> assignedToHp=new HashMap<>();
    HashMap<String,String> assignedToHp3=new HashMap<>();

    public void customerrepresentativeDilagbox()
    {
        dialog2 = new Dialog(getActivity());

        dialog2.setContentView(R.layout.activity_searchagent);

        lv2 = (ListView)dialog2. findViewById(R.id.list_view);
        inputSearch2 = (EditText) dialog2.findViewById(R.id.inputSearch);

        ArrayList<String> assignedToSpinner = new ArrayList<String>();
        JSONObject jsonObject= Constants.jsonLoginResponse;
        jsonArray=jsonObject.optJSONObject("response").optJSONObject("data").optJSONArray("Users");

        for(int i=0;i<jsonArray.length();i++)
        {
            assignedToSpinner.add(jsonArray.optJSONObject(i).optString("userName"));
            assignedToHp3.put(jsonArray.optJSONObject(i).optString("userName"),jsonArray.optJSONObject(i).optString("userId"));
        }

        adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.product_name, assignedToSpinner);
        lv2.setAdapter(adapter2);
        lv2.setClickable(true);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv2.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                CustomerRepresentative.setText(str);
                userId=assignedToHp3.get(str);

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

    public void pestinfecteddialoguebox() {

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
                PestInfected.setText(str);
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