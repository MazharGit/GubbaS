package ssg1.gubba1.gubba1.g.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.github.florent37.tutoshowcase.TutoShowcase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.Fragments.adapter.AdapterCRMLeadList;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.ToolbarConfigurer;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class CRMLeadList extends Fragment {

    View view;

    ArrayAdapter<String> adapter;
    TextView no_record;

    // Search EditText
    EditText inputSearch;

    CallWebService service;

    SharedPref sp;

    int i;

    String ids;

    private AdapterCRMLeadList mAdapter;

    RecyclerView crmRecRecyclerView;
    FragmentTransaction fragmentTransaction;

    JSONArray searchArray = null;

    String documentno;

    int startrow=0,lastrow=6;

    FloatingActionButton previous,next;

    int last=0;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.crm_lead_list, null);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.new1, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new1:

                try {
                    new ToolbarConfigurer(Home.mToolbar, true);
                    Fragment fragment= new CRMLead();
                    Home.crmLead = (CRMLead) fragment;
                    fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }catch (Exception e){e.printStackTrace();}

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        service = new CallWebService(getActivity());
        sp = new SharedPref(getActivity());

        try {
            documentno=getArguments().getString("documentno");
        }catch (Exception e){}

        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.crm_lead_list, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow6").equals("0"))
                {
                    sp.removeData("loginshow6");
                    sp.writeString("loginshow6", "0");

                    displayTuto();

                }
            }
        }, 500);

        try {
            {
                Home.mToolbar.setTitle("CRM Lead List");
                ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        } catch (Exception ee) {
        }

        searchItem();

        crmRecRecyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        crmRecRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        try {
            getCRMList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        previous  = (FloatingActionButton) view.findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startrow<=0)
                {
                    previous.setEnabled(false);
                }
                else
                {
                    startrow-=6;
                    lastrow-=6;
                    getCRMList();
                }
            }
        });

        next  = (FloatingActionButton) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startrow+=6;
                lastrow+=6;
                getCRMList();
            }
        });

        return view;
    }

    public void getCRMList()  {

        Home.crm_lead_list_url = "GCRM_Lead?" + new Constants().USER_N_PASSWORD+"&_sortBy=creationDate%20desc&_startRow="+startrow+"&_endRow="+lastrow;

        service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.crm_lead_list_url, true);

    }

    public void crmLeadListResponse(JSONObject jsonObject) throws JSONException {

        searchArray=jsonObject.getJSONObject("response").getJSONArray("data");
        System.out.println("no of recordss ---"+searchArray.length());

        searchArray=jsonObject.getJSONObject("response").getJSONArray("data");
        System.out.println("no of recordss ---"+searchArray.length());

        int l=searchArray.length();

        if (l==6)
        {
            last=0;
            next.setEnabled(true);
            previous.setEnabled(true);
        }
        else
        {
            last=1;
            next.setEnabled(false);
            previous.setEnabled(true);
        }

        try {
            ids = "(";
            for (i = 0; i < searchArray.length() - 1; i++) {
                ids = ids + "%27" + searchArray.optJSONObject(i).optString("searchkey") + "%27" + ',';
            }
            ids = ids + "%27" + searchArray.optJSONObject(i).optString("searchkey") + "%27" + ")";
        }catch (Exception e){e.printStackTrace();}

        mAdapter = new AdapterCRMLeadList(getActivity(), searchArray);
        crmRecRecyclerView.setAdapter(mAdapter);

    }
    private void searchItem() {

        no_record = (TextView) view.findViewById(R.id.no_recd);
        inputSearch = (EditText) view.findViewById(R.id.inputSearch);


        try {


            inputSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    JSONArray searched_products = new JSONArray();


                    try {
                        for (int j = 0; j < searchArray.length(); j++) {

                            if ((searchArray.optJSONObject(j).optString("searchkey")+""+searchArray.optJSONObject(j).optString("companyname")+searchArray.optJSONObject(j).optString("gcrmContact$_identifier")+""+searchArray.optJSONObject(j).optString("organization$_identifier")).contains(inputSearch.getText().toString().toLowerCase())) {
                                searched_products.put(searchArray.optJSONObject(j));

                            }

                        }

                        if (inputSearch.getText().length() > 0) {
                            mAdapter = new AdapterCRMLeadList(getActivity(), searched_products);
                            crmRecRecyclerView.setAdapter(mAdapter);
                        } else {
                            mAdapter = new AdapterCRMLeadList(getActivity(), searchArray);
                            crmRecRecyclerView.setAdapter(mAdapter);
                        }

                        if (searchArray.length() <= 0 || searched_products.length() <= 0) {
                            crmRecRecyclerView.setVisibility(View.GONE);
                            no_record.setVisibility(View.VISIBLE);
                            no_record.setText("No search result found");
                        } else {
                            no_record.setVisibility(View.GONE);
                            crmRecRecyclerView.setVisibility(View.VISIBLE);
                            no_record.setText("No record found");


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        mAdapter = new AdapterCRMLeadList(getActivity(), searchArray);
                        crmRecRecyclerView.setAdapter(mAdapter);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        } catch (Exception ee) {
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            getCRMList();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void displayTuto() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_crmleadlist)
                .setFitsSystemWindows(true)
                .on(R.id.new1)
                .addRoundRect()
                .withBorder()
                .show();
    }

}
