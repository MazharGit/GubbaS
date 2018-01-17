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

import ssg1.gubba1.gubba1.g.Fragments.adapter.AdapterCRMLeadActivityList;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;
import ssg1.gubba1.gubba1.g.ToolbarConfigurer;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;

public class CRMLeadActivityList extends Fragment {

    View view;

    ArrayAdapter<String> adapter;
    TextView no_record;

    // Search EditText
    EditText inputSearch;

    CallWebService service;

    public static String crmLeadId,searchkey;

    public static String activityid;

    SharedPref sp;

    int i;

    String ids;

    private AdapterCRMLeadActivityList mAdapter;

    RecyclerView crmRecRecyclerView;
    FragmentTransaction fragmentTransaction;

    JSONArray searchArray = null;

    int startrow=0,lastrow=6;

    FloatingActionButton previous,next;

    int last=0;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.crm_lead_activity_list, null);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.addd, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:

                try {
                    new ToolbarConfigurer(Home.mToolbar, true);
                    CRMLeadActivity fragment= new CRMLeadActivity();
                    Home.crmLeadActivity = (CRMLeadActivity) fragment;
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

        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.crm_lead_activity_list, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!sp.readString("loginshow9").equals("0"))
                {
                    sp.removeData("loginshow9");
                    sp.writeString("loginshow9", "0");

                    displayTuto();

                }
            }
        }, 500);

        try {
            crmLeadId=getArguments().getString("crmLeadId");
            searchkey=getArguments().getString("searchkey");

        }catch (Exception e){}

        try {
            getActivityList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            {
                Home.mToolbar.setTitle("Activity List");
                ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        } catch (Exception ee) {
        }

        searchItem();

        crmRecRecyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        crmRecRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
                    getActivityList();
                }
            }
        });

        next  = (FloatingActionButton) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startrow+=6;
                lastrow+=6;
                getActivityList();
            }
        });

        return view;
    }

    public void getActivityList()  {


            Home.crm_lead_list_activity_url = "GCRM_Activity?" + new Constants().USER_N_PASSWORD+"&_where=gcrmLead=%27" + crmLeadId +"%27%20&_startRow="+startrow+"&_endRow="+lastrow;

            service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL + Home.crm_lead_list_activity_url, true);


    }

    public void crmLeadActivityListResponse(JSONObject jsonObject) throws JSONException {

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
                ids = ids + "%27" + searchArray.optJSONObject(i).optString("_identifier") + "%27" + ',';
            }
            ids = ids + "%27" + searchArray.optJSONObject(i).optString("_identifier") + "%27" + ")";
        }catch (Exception e){e.printStackTrace();}

        mAdapter = new AdapterCRMLeadActivityList(getActivity(), searchArray);
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

                            if ((searchArray.optJSONObject(j).optString("_identifier").contains(inputSearch.getText().toString().toLowerCase()))) {
                                searched_products.put(searchArray.optJSONObject(j));

                            }

                        }

                        if (inputSearch.getText().length() > 0) {
                            mAdapter = new AdapterCRMLeadActivityList(getActivity(), searched_products);
                            crmRecRecyclerView.setAdapter(mAdapter);
                        } else {
                            mAdapter = new AdapterCRMLeadActivityList(getActivity(), searchArray);
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
                        mAdapter = new AdapterCRMLeadActivityList(getActivity(), searchArray);
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

    protected void displayTuto() {
        TutoShowcase.from(getActivity())
                .setContentView(R.layout.tuto_showcase_firstcontactformlist)
                .setFitsSystemWindows(true)
                .on(R.id.add)
                .addRoundRect()
                .withBorder()
                .show();
    }

}
