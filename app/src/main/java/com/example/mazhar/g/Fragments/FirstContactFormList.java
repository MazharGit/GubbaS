package com.example.mazhar.g.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.example.mazhar.g.Home;
import com.example.mazhar.g.R;
import com.example.mazhar.g.ToolbarConfigurer;

import org.json.JSONArray;

public class FirstContactFormList extends android.support.v4.app.Fragment {

    View view;

    ArrayAdapter<String> adapter;
    TextView no_record;

    // Search EditText
    EditText inputSearch;

    RecyclerView crmRecRecyclerView;
    FragmentTransaction fragmentTransaction;

    JSONArray searchArray = null;

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setHasOptionsMenu(true);

        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater2.inflate(R.layout.activity_first_contact_form_list, null);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.add, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save:

                try {
                    new ToolbarConfigurer(Home.mToolbar, true);
                    Fragment fragment= new FirstContactForm();
                    Home.firstContactForm = (FirstContactForm) fragment;
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

        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.activity_first_contact_form_list, container, false);

        try {
            {
                Home.mToolbar.setTitle("First Contact Form list");
                ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        } catch (Exception ee) {
        }


        crmRecRecyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        crmRecRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        searchItem();
        /*try {
            getFarmerissueList();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return view;
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

                            /*if ((searchArray.optJSONObject(j).optString("documentNo") + "--" + searchArray.optJSONObject(j).optString("ddlFarmer$_identifier")).toString().toLowerCase().contains(inputSearch.getText().toString().toLowerCase())) {
                                searched_products.put(searchArray.optJSONObject(j));

                            }*/

                        }

                        if (inputSearch.getText().length() > 0) {
                            /*mAdapter = new AdapterFarmerIssuevoucherList(getActivity(), searched_products);
                            milkRecRecyclerView.setAdapter(mAdapter);*/
                        } else {
                            /*mAdapter = new AdapterFarmerIssuevoucherList(getActivity(), searchArray);
                            milkRecRecyclerView.setAdapter(mAdapter);*/
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
                        /*mAdapter = new AdapterFarmerIssuevoucherList(getActivity(), searchArray);
                        milkRecRecyclerView.setAdapter(mAdapter);*/
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        } catch (Exception ee) {
        }

    }

}
