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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class InwardMemoProductBayWiseDenomination extends Fragment {

    View view;

    EditText Chamber,Bay,Product,NoOfLayers,NoOfRowsM1,NoOfColumnsM1,NoOfRowsM2,NoOfColumnsM2,LooseBags,HandlingUnits,HandlingUnitsUOM;
    TextInputLayout LChamber,LBay,LProduct,LNoOfLayers,LNoOfRowsM1,LNoOfColumnsM1,LNoOfRowsM2,LNoOfColumnsM2,LLooseBags,LHandlingUnits,LHandlingUnitsUOM;
    Button Save;
    String SChamber,SBay,SProduct,SNoOfLayers,SNoOfRowsM1,SNoOfColumnsM1,SNoOfRowsM2,SNoOfColumnsM2,SLooseBags,SHandlingUnits,SHandlingUnitsUOM;
    String SRChamber,SRBay,SRProduct,SRNoOfLayers,SRNoOfRowsM1,SRNoOfColumnsM1,SRNoOfRowsM2,SRNoOfColumnsM2,SRLooseBags,SRHandlingUnits,SRHandlingUnitsUOM;

    CallWebService service;

    FragmentTransaction fragmentTransaction;

    SharedPref sp;

    JSONArray jsonArray;

    String id;

    String replace="0",productid;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_inwardmemoproductbaywisedenomination, null);

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
        view = inflater.inflate(R.layout.activity_inwardmemoproductbaywisedenomination, container, false);

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        jsonArray=new JSONArray();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1111").equals("0"))
                {
                    sp.removeData("loginshow1111");
                    sp.writeString("loginshow1111", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow11111").equals("0"))
                {
                    sp.removeData("loginshow11111");
                    sp.writeString("loginshow11111", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Chamber=(EditText) view.findViewById(R.id.input_chamber);
        Bay= (EditText) view.findViewById(R.id.input_bay);
        Product= (EditText) view.findViewById(R.id.input_product);
        NoOfLayers= (EditText) view.findViewById(R.id.input_nooflayers);
        NoOfRowsM1 = (EditText) view.findViewById(R.id.input_noofrowsm1);
        NoOfRowsM2 = (EditText) view.findViewById(R.id.input_noofrowsm2);
        NoOfColumnsM1 = (EditText) view.findViewById(R.id.input_noofcolumnsm1);
        NoOfColumnsM2 = (EditText) view.findViewById(R.id.input_noofcolumnsm2);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LChamber=(TextInputLayout) view.findViewById(R.id.input_layout_chamber);
        LBay= (TextInputLayout) view.findViewById(R.id.input_layout_bay);
        LProduct= (TextInputLayout) view.findViewById(R.id.input_layout_product);
        LNoOfLayers= (TextInputLayout) view.findViewById(R.id.input_layout_nooflayers);
        LNoOfRowsM1 = (TextInputLayout) view.findViewById(R.id.input_layout_noofrowsm1);
        LNoOfRowsM2 = (TextInputLayout) view.findViewById(R.id.input_layout_noofrowsm2);
        LNoOfColumnsM1 = (TextInputLayout) view.findViewById(R.id.input_layout_noofcolumnsm1);
        LNoOfColumnsM2 = (TextInputLayout) view.findViewById(R.id.input_layout_noofcolumnsm2);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Chamber.setTypeface(UbuntuFont);
        Bay.setTypeface(UbuntuFont);
        Product.setTypeface(UbuntuFont);
        NoOfLayers.setTypeface(UbuntuFont);
        NoOfRowsM1.setTypeface(UbuntuFont);
        NoOfColumnsM1.setTypeface(UbuntuFont);
        NoOfRowsM2.setTypeface(UbuntuFont);
        NoOfColumnsM2.setTypeface(UbuntuFont);
        LooseBags.setTypeface(UbuntuFont);
        HandlingUnits.setTypeface(UbuntuFont);
        HandlingUnitsUOM.setTypeface(UbuntuFont);

        LChamber.setTypeface(UbuntuFont);
        LBay.setTypeface(UbuntuFont);
        LProduct.setTypeface(UbuntuFont);
        LNoOfLayers.setTypeface(UbuntuFont);
        LNoOfRowsM1.setTypeface(UbuntuFont);
        LNoOfColumnsM1.setTypeface(UbuntuFont);
        LNoOfRowsM2.setTypeface(UbuntuFont);
        LNoOfColumnsM2.setTypeface(UbuntuFont);
        LLooseBags.setTypeface(UbuntuFont);
        LHandlingUnits.setTypeface(UbuntuFont);
        LHandlingUnitsUOM.setTypeface(UbuntuFont);
        try
        {

            SRChamber=getArguments().getString("chamber");
            SRBay=getArguments().getString("bay");
            SRProduct=getArguments().getString("product");
            productid=getArguments().getString("productid");
            SRNoOfLayers=getArguments().getString("nooflayers");
            SRNoOfRowsM1=getArguments().getString("noofrowsm1");
            SRNoOfColumnsM1=getArguments().getString("noofcolumnsm1");
            SRNoOfRowsM2=getArguments().getString("noofrowsm2");
            SRNoOfColumnsM2=getArguments().getString("noofcolumnsm2");
            SRLooseBags=getArguments().getString("loosebags");
            SRHandlingUnits=getArguments().getString("hsndlingunits");
            SRHandlingUnitsUOM=getArguments().getString("hsndlingunitsuom");
            replace= getArguments().getString("replace");
            id=getArguments().getString("id");


        }catch (Exception e){}

        Chamber.setText(SRChamber);
        Bay.setText(SRBay);
        Product.setText(SRProduct);
        NoOfLayers.setText(SRNoOfLayers);
        NoOfRowsM1.setText(SRNoOfRowsM1);
        NoOfColumnsM1.setText(SRNoOfColumnsM1);
        NoOfRowsM2.setText(SRNoOfRowsM2);
        NoOfColumnsM2.setText(SRNoOfColumnsM2);
        LooseBags.setText(SRLooseBags);
        HandlingUnits.setText(SRHandlingUnits);
        HandlingUnitsUOM.setText(SRHandlingUnitsUOM);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SChamber=Chamber.getText().toString();
                SBay=Bay.getText().toString();
                SProduct=Product.getText().toString();
                SNoOfLayers=NoOfLayers.getText().toString();
                SNoOfRowsM1=NoOfRowsM1.getText().toString();
                SNoOfColumnsM1=NoOfColumnsM1.getText().toString();
                SNoOfRowsM2=NoOfRowsM2.getText().toString();
                SNoOfColumnsM2=NoOfColumnsM2.getText().toString();
                SLooseBags=LooseBags.getText().toString();
                SHandlingUnits=HandlingUnits.getText().toString();
                SHandlingUnitsUOM=HandlingUnitsUOM.getText().toString();

                if(TextUtils.isEmpty(SProduct)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Product is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SNoOfRowsM1)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"No. of Rows/Matrix 1 is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SNoOfColumnsM1)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"No. of Columns/Matrix 1 is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SNoOfRowsM2)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"No. of Rows/Matrix 2 is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SNoOfColumnsM2)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"No. of Columns/Matrix 2 is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(SLooseBags)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Loose Bags is Mandatory",Snackbar.LENGTH_LONG).show();
                }
                else if (!(TextUtils.isEmpty(SProduct)) && !(TextUtils.isEmpty(SNoOfRowsM1)) && !(TextUtils.isEmpty(SNoOfColumnsM1)) && !(TextUtils.isEmpty(SNoOfRowsM2)) && !(TextUtils.isEmpty(SNoOfColumnsM2)) && !(TextUtils.isEmpty(SLooseBags)))
                {

                    Home.inwardmemoproductweight_url = "sqt_inwd_pdt_bay?" + new Constants().USER_N_PASSWORD;
                    JSONObject jsonObject = new JSONObject();

                    try {

                        if (replace.equalsIgnoreCase("0"))
                        {
                            jsonObject.put("data", new JSONObject()
                                .put("sQTChamber", SChamber)
                                .put("locator",SBay)
                                .put("product",productid)
                                .put("nooflayers", SNoOfLayers)
                                .put("noofrows", SNoOfRowsM1)
                                .put("noofcolumns", SNoOfColumnsM1)
                                .put("noofrows2",SNoOfRowsM2)
                                .put("noofcolumns2", SNoOfColumnsM2)
                                .put("loosebags", SLooseBags)
                                .put("handlinguints", SHandlingUnits)
                                .put("uom", SHandlingUnitsUOM));
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.inwardmemoproductweight_url, jsonObject, true);

                        }
                        else
                        {
                            jsonObject.put("data", new JSONObject()
                                    .put("sQTChamber", SChamber)
                                    .put("locator",SBay)
                                    .put("product",productid)
                                    .put("nooflayers", SNoOfLayers)
                                    .put("noofrows", SNoOfRowsM1)
                                    .put("noofcolumns", SNoOfColumnsM1)
                                    .put("noofrows2",SNoOfRowsM2)
                                    .put("noofcolumns2", SNoOfColumnsM2)
                                    .put("loosebags", SLooseBags)
                                    .put("handlinguints", SHandlingUnits)
                                    .put("uom", SHandlingUnitsUOM)
                                    .put("id", id));
                            service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.inwardmemoproductweight_url, jsonObject, true);

                            replace="0";
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
                Chamber.setText("");
                Bay.setText("");
                Product.setText("");
                NoOfLayers.setText("");
                NoOfRowsM1.setText("");
                NoOfColumnsM1.setText("");
                NoOfRowsM2.setText("");
                NoOfColumnsM2.setText("");
                LooseBags.setText("");
                HandlingUnits.setText("");
                HandlingUnitsUOM.setText("");
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

            Chamber.setText("");
            Bay.setText("");
            Product.setText("");
            NoOfLayers.setText("");
            NoOfRowsM1.setText("");
            NoOfColumnsM1.setText("");
            NoOfRowsM2.setText("");
            NoOfColumnsM2.setText("");
            LooseBags.setText("");
            HandlingUnits.setText("");
            HandlingUnitsUOM.setText("");

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
}