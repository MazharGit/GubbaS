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
import android.widget.CheckBox;
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

public class InwardMemoInspection extends Fragment {

    View view;

    EditText Sequence,Name;
    CheckBox Result,ImageRequired;
    TextInputLayout LSequence,LName;
    Button Save;
    String SSequence,SName;
    boolean r,i;
    String SRSequence,SRName;

    CallWebService service;

    FragmentTransaction fragmentTransaction;

    SharedPref sp;

    JSONArray jsonArray;

    String id;

    String replace="0",snameid,rr,ii;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_inwardmemoinspection, null);

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
        view = inflater.inflate(R.layout.activity_inwardmemoinspection, container, false);

        service = new CallWebService(getActivity());

        sp = new SharedPref(getActivity());

        jsonArray=new JSONArray();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1138").equals("0"))
                {
                    sp.removeData("loginshow1138");
                    sp.writeString("loginshow1138", "0");

                    displayTuto();

                }
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow1139").equals("0"))
                {
                    sp.removeData("loginshow1139");
                    sp.writeString("loginshow1139", "0");

                    displayTutoo();

                }
            }
        }, 10000);

        Sequence=(EditText) view.findViewById(R.id.input_sequence);
        Name= (EditText) view.findViewById(R.id.input_name);
        Result= (CheckBox) view.findViewById(R.id.result);
        ImageRequired= (CheckBox) view.findViewById(R.id.imagerequired);
        Save=(Button) view.findViewById(R.id.save) ;

        try{
            Home.mToolbar.setTitle(" ");
        }catch (Exception e){}

        LSequence=(TextInputLayout) view.findViewById(R.id.input_layout_sequence);
        LName= (TextInputLayout) view.findViewById(R.id.input_layout_name);

        Typeface UbuntuFont = Typeface.createFromAsset(getContext().getAssets(),
                "Ubuntu-M.ttf");


        Save.setTypeface(UbuntuFont);
        Sequence.setTypeface(UbuntuFont);
        Name.setTypeface(UbuntuFont);

        LSequence.setTypeface(UbuntuFont);
        LName.setTypeface(UbuntuFont);

        try
        {

            SRSequence=getArguments().getString("sequence");
            SRName= getArguments().getString("name");
            snameid= getArguments().getString("nameid");
            rr=getArguments().getString("result");
            ii= getArguments().getString("imagerequired");
            replace= getArguments().getString("replace");
            id=getArguments().getString("id");


        }catch (Exception e){}

        Sequence.setText(SRSequence);
        Name.setText(SRName);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SSequence=Sequence.getText().toString();
                SName=Name.getText().toString();

                Home.inwardmemoinspection_url = "sqt_inwd_inspection?" + new Constants().USER_N_PASSWORD;
                JSONObject jsonObject = new JSONObject();

                try {

                    if (replace.equalsIgnoreCase("0"))
                    {
                        if (Result.isChecked()) {
                            r=true;
                        }
                        else
                        {
                            r=false;
                        }

                        if (ImageRequired.isChecked()) {
                            i=true;
                        }
                        else
                        {
                            i=false;
                        }

                        jsonObject.put("data", new JSONObject()
                                .put("sequence", SSequence)
                                .put("sQTInspection",snameid)
                                .put("result", rr)
                                .put("imagerequired", ii));
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.inwardmemoinspection_url, jsonObject, true);

                    }
                    else
                    {
                        jsonObject.put("data", new JSONObject()
                                .put("sequence", SSequence)
                                .put("sQTInspection",snameid)
                                .put("result", rr)
                                .put("id", id)
                                .put("imagerequired", ii));
                        service.makeJsonObjectRequestPost(Constants.BASE_RIL_URL + Home.inwardmemoinspection_url, jsonObject, true);

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
                Sequence.setText("");
                Name.setText("");

                Snackbar.make(getActivity().findViewById(android.R.id.content),"All Fields are Cleared",Snackbar.LENGTH_LONG).show();
                return true;
            }

            case R.id.list: {
                Bundle args = new Bundle();
                Fragment fragment = new InwardmemoInspectionList();
                Home.inwardmemoInspectionList = (InwardmemoInspectionList) fragment;
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


    public void inwardmemoinspectionResponse(JSONObject json){
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

            Sequence.setText("");
            Result.setText("");

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