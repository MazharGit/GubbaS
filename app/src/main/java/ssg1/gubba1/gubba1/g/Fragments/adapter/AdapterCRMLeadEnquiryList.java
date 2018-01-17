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

import ssg1.gubba1.gubba1.g.Fragments.CRMLeadEnquiry;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterCRMLeadEnquiryList extends RecyclerView.Adapter<AdapterCRMLeadEnquiryList.ViewHolder> {
    Context context;

    JSONArray jsonArray;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }

    public AdapterCRMLeadEnquiryList(Activity activity, JSONArray jsonArray) {

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

            holder.list_item.setText("Product Name : "+jsonObject.optString("product$_identifier")+" - "+"UOM : "+jsonObject.optString("uom$_identifier")+" - "+"Quantity : "+jsonObject.optString("quantity"));
             }catch (Exception ex){
            ex.printStackTrace();}
        holder.list_item.setMaxLines(4);

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage("Product Name             : "+jsonObject.optString("product$_identifier")
                            +"\n\n"+"UOM                              : "+jsonObject.optString("uom$_identifier")
                            +"\n\n"+"Quantity                        : "+jsonObject.optString("quantity")
                            +"\n\n"+"From Temperature      : "+jsonObject.optString("fromtemp")
                            +"\n\n"+"To Temperature           : "+jsonObject.optString("totemp")
                            +"\n\n"+"Requirement Details   : "+jsonObject.optString("descprition")
                            +"\n\n"+"Location                       : "+jsonObject.optString("gcrmLocator$_identifier")+"\n\n");

                    builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {

                                Bundle args = new Bundle();
                                args.putString("enquiryid", jsonObject.optString("id"));
                                args.putString("searchkey", jsonObject.optString("searchkey"));
                                args.putString("productname", jsonObject.optString("product$_identifier"));
                                args.putString("uom", jsonObject.optString("uom$_identifier"));
                                args.putString("quantity", jsonObject.optString("quantity"));
                                args.putString("fromtemperature", jsonObject.optString("fromtemp"));
                                args.putString("totemperature", jsonObject.optString("totemp"));
                                args.putString("requirementdetails", jsonObject.optString("descprition"));
                                args.putString("location", jsonObject.optString("gcrmLocator$_identifier"));
                                args.putString("locationid", jsonObject.optString("gcrmLocator"));
                                args.putString("replace", "1");

                                CRMLeadEnquiry fragment = new CRMLeadEnquiry();
                                Home.crmLeadEnquiry = (CRMLeadEnquiry) fragment;
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