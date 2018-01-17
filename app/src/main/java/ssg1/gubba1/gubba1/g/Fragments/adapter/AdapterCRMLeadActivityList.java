package ssg1.gubba1.gubba1.g.Fragments.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.Fragments.CRMLeadActivity;
import ssg1.gubba1.gubba1.g.Fragments.CRMLeadActivityMOMList;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterCRMLeadActivityList extends RecyclerView.Adapter<AdapterCRMLeadActivityList.ViewHolder> {
    Context context;

    JSONArray jsonArray;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }

    public AdapterCRMLeadActivityList(Activity activity, JSONArray jsonArray) {

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

            holder.list_item.setText("Contact : "+jsonObject.optString("gcrmLeadcontact$_identifier")+" - "+"Activity Type : "+jsonObject.optString("activitytype")+" - "+"Subject : "+jsonObject.optString("subject"));
             }catch (Exception ex){
            ex.printStackTrace();}
        holder.list_item.setMaxLines(5);

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage("Subject           : "+jsonObject.optString("subject")
                               +"\n\n"+"Active Type   : "+jsonObject.optString("activitytype")
                               +"\n\n"+"Contact          : "+jsonObject.optString("gcrmLeadcontact$_identifier")+"\n\n");

                    builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Bundle args = new Bundle();
                                args.putString("activityId", jsonObject.optString("id"));
                                args.putString("subject", jsonObject.optString("subject"));
                                args.putString("activitytype", jsonObject.optString("activitytype"));
                                args.putString("contact", jsonObject.optString("gcrmLeadcontact$_identifier"));
                                args.putString("contactid", jsonObject.optString("gcrmLeadcontact"));
                                args.putString("replace", "1");
                                CRMLeadActivity fragment = new CRMLeadActivity();
                                Home.crmLeadActivity = (CRMLeadActivity) fragment;
                                fragment.setArguments(args);
                                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                fragmentTransaction.commitAllowingStateLoss();
                                ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            }catch (Exception e){
                            }
                        }
                    });

                    builder.setPositiveButton("mom", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Bundle args = new Bundle();
                                args.putString("activityId", jsonObject.optString("id"));
                                CRMLeadActivityMOMList fragment = new CRMLeadActivityMOMList();
                                Home.crmLeadActivityMOMList = (CRMLeadActivityMOMList) fragment;
                                fragment.setArguments(args);
                                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                fragmentTransaction.commitAllowingStateLoss();
                                ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                            }catch (Exception e){
                            }
                        }
                    });

                    builder.show();

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