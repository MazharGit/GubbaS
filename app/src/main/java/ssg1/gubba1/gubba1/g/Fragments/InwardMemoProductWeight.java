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

public class InwardMemoProductWeight extends Fragment {

    View view;

    EditText Weight,Product;
    TextInputLayout LWeight,LProduct;
    Button Save;
    String SWeight,SProduct;
    String SRWeight,SRProduct;

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
        view = inflater2.inflate(R.layout.activity_inwardmemoproductweight, null);

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
        view = inflater.inflate(R.layout.activity_inwardmemoproductweight, container, false);

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        jsonArray=new JSONArray();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1142").equals("0"))
                {
                    sp.removeData("loginshow1142");
                    sp.writeString("loginshow1142", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1143").equals("0"))
                {
                    sp.removeData("loginshow1143");
                    sp.writeString("loginshow1143", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Weight=(EditText) view.findViewById(R.id.input_weight);
        Product= (EditText) view.findViewById(R.id.input_product);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LWeight=(TextInputLayout) view.findViewById(R.id.input_layout_weight);
        LProduct= (TextInputLayout) view.findViewById(R.id.input_layout_product);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Weight.setTypeface(UbuntuFont);
        Product.setTypeface(UbuntuFont);

        LWeight.setTypeface(UbuntuFont);
        LProduct.setTypeface(UbuntuFont);

        try
        {

            SRWeight=getArguments().getString("weight");
            SRProduct= getArguments().getString("product");
            productid= getArguments().getString("productid");
            replace= getArguments().getString("replace");
            id=getArguments().getString("id");


        }catch (Exception e){}

        Weight.setText(SRWeight);
        Product.setText(SRProduct);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SWeight=Weight.getText().toString();
                SProduct=Product.getText().toString();

                Home.inwardmemoproductweight_url = "sqt_inwd_productweight?" + new Constants().USER_N_PASSWORD;
                JSONObject jsonObject = new JSONObject();

                try {

                    if (replace.equalsIgnoreCase("0"))
                    {

                        jsonObject.put("data", new JSONObject()
                                .put("weight", SWeight)
                                .put("sQTProduct",productid));
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.inwardmemoproductweight_url, jsonObject, true);

                    }
                    else
                    {
                        jsonObject.put("data", new JSONObject()
                                .put("weight", SWeight)
                                .put("sQTProduct",productid)
                                .put("id", id));
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.inwardmemoproductweight_url, jsonObject, true);

                        replace="0";
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
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
                Weight.setText("");
                Product.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Bundle args = new Bundle();
                Fragment fragment = new InwardmemoProductWeightList();
                Home.inwardmemoProductWeightList = (InwardmemoProductWeightList) fragment;
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


    public void inwardmemoproductweightResponse(JSONObject json){
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

            Weight.setText("");
            Product.setText("");

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