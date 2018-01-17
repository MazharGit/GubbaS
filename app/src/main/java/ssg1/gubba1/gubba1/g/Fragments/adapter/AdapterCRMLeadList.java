package ssg1.gubba1.gubba1.g.Fragments.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.Fragments.CRMLead;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadActivityList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadAddressList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadContactsList;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadEnquiryList;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterCRMLeadList extends RecyclerView.Adapter<AdapterCRMLeadList.ViewHolder> {
    Context context;

    JSONArray jsonArray;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }

    public AdapterCRMLeadList(Activity activity, JSONArray jsonArray) {

        context=activity;
        this.jsonArray=jsonArray;
        System.out.println("JSON ARRAY DATA : "+jsonArray);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        v.setTag(vh);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final JSONObject jsonObject = jsonArray.optJSONObject(position);
        holder.setIsRecyclable(false);

        try {

            holder.list_item.setText("Searchkey : "+jsonObject.optString("searchkey")+" - " + "Company name : "+jsonObject.optString("companyname")+ " - " + "Location : "+jsonObject.optString("organization$_identifier")+" - "+ "Priority : "+jsonObject.optString("priority")+" - "+ "Assigned To : "+jsonObject.optString("user$_identifier")+"Document number : "+" - "+jsonObject.optString("gcrmContact$_identifier"));
             }catch (Exception ex){
            ex.printStackTrace();}
        holder.list_item.setMaxLines(6);

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                try {

                    final CharSequence options[] = new CharSequence[] {"Enquiry", "Address", "Contacts", "Activity"};

                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setCancelable(true);

                    if (true)
                    {

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (options[which].equals("Enquiry"))
                                {
                                    String idSend=jsonObject.optString("id");
                                    String searchkey=jsonObject.optString("searchkey");
                                    Bundle args = new Bundle();
                                    args.putString("crmLeadId", idSend);
                                    args.putString("searchkey", searchkey);
                                    CRMLeadEnquiryList fragment = new CRMLeadEnquiryList();
                                    Home.crmLeadEnquiryList = (CRMLeadEnquiryList) fragment;
                                    fragment.setArguments(args);
                                    FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                    fragmentTransaction.commitAllowingStateLoss();
                                    ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                }
                                else if (options[which].equals("Address"))
                                {
                                    String idSend=jsonObject.optString("id");
                                    String searchkey=jsonObject.optString("searchkey");
                                    Bundle args = new Bundle();
                                    args.putString("crmLeadId", idSend);
                                    args.putString("searchkey", searchkey);
                                    CRMLeadAddressList fragment = new CRMLeadAddressList();
                                    Home.crmLeadAddressList = (CRMLeadAddressList) fragment;
                                    fragment.setArguments(args);
                                    FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                    fragmentTransaction.commitAllowingStateLoss();
                                    ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                }
                                else if (options[which].equals("Contacts"))
                                {
                                    String idSend=jsonObject.optString("id");
                                    String searchkey=jsonObject.optString("searchkey");
                                    Bundle args = new Bundle();
                                    args.putString("crmLeadId", idSend);
                                    args.putString("searchkey", searchkey);
                                    CRMLeadContactsList fragment = new CRMLeadContactsList();
                                    Home.crmLeadContactsList = (CRMLeadContactsList) fragment;
                                    fragment.setArguments(args);
                                    FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                    fragmentTransaction.commitAllowingStateLoss();
                                    ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                }
                                else if (options[which].equals("Activity"))
                                {
                                    String crmLeadId=jsonObject.optString("id");
                                    String searchkey=jsonObject.optString("searchkey");
                                    Bundle args = new Bundle();
                                    args.putString("crmLeadId", crmLeadId);
                                    args.putString("searchkey", searchkey);
                                    CRMLeadActivityList fragment = new CRMLeadActivityList();
                                    Home.crmLeadActivityList = (CRMLeadActivityList) fragment;
                                    fragment.setArguments(args);
                                    FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                    fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                    fragmentTransaction.commitAllowingStateLoss();
                                    ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                }
                            }
                        });

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Bundle args = new Bundle();
                                String priority=jsonObject.optString("priority");
                                String bpid=jsonObject.optString("bpartner");
                                String fcid=jsonObject.optString("gcrmContact$_identifier");
                                String assignedto=jsonObject.optString("user$_identifier");
                                String assignedtoo=jsonObject.optString("user");
                                String companyname=jsonObject.optString("companyname");
                                String searchkey=jsonObject.optString("searchkey");
                                String location=jsonObject.optString("organization$_identifier");
                                String locationid=jsonObject.optString("organization");
                                String id=jsonObject.optString("id");

                                args.putString("priority", priority);
                                args.putString("bpid", bpid);
                                args.putString("fcid", fcid);
                                args.putString("assignedto", assignedto);
                                args.putString("assignedto0", assignedtoo);
                                args.putString("assignedto", assignedto);
                                args.putString("companyname", companyname);
                                args.putString("searchkey", searchkey);
                                args.putString("location", location);
                                args.putString("locationid", locationid);
                                args.putString("id", id);
                                args.putString("replace","1");

                                CRMLead fragment = new CRMLead();
                                Home.crmLead = (CRMLead) fragment;
                                fragment.setArguments(args);
                                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                fragmentTransaction.commitAllowingStateLoss();
                                ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            }
                        });
                        builder.show();
                    }

                }catch (Exception e){e.printStackTrace();}

            }
        });

    }

    @Override
    public int getItemCount() {


        return jsonArray.length();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}