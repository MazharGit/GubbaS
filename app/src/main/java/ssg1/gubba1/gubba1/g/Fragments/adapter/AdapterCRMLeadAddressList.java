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

import ssg1.gubba1.gubba1.g.Fragments.CRMLeadAddress;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterCRMLeadAddressList extends RecyclerView.Adapter<AdapterCRMLeadAddressList.ViewHolder> {
    Context context;

    JSONArray jsonArray;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }

    public AdapterCRMLeadAddressList(Activity activity, JSONArray jsonArray) {

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

            holder.list_item.setText("Address Line 1 : "+jsonObject.optString("addressline1")+" - "+"Address Line 2 : "+jsonObject.optString("addressline2")+" - "+"City : "+jsonObject.optString("city")+" - "+"Pincode : "+jsonObject.optString("pincode")+" - "+jsonObject.optString("country$_identifier"));
             }catch (Exception ex){
            ex.printStackTrace();}
        holder.list_item.setMaxLines(4);

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage("Address Line 1        : "+jsonObject.optString("addressline1")
                            +"\n\n"+"Address Line 2        : "+jsonObject.optString("addressline2")
                            +"\n\n"+"City                            : "+jsonObject.optString("city")
                            +"\n\n"+"Pincode                    : "+jsonObject.optString("pincode")
                            +"\n\n"+"Region                      : "+jsonObject.optString("region$_identifier")
                            +"\n\n"+"Country                     : "+jsonObject.optString("country$_identifier")
                            +"\n\n"+"Invoice Address      : "+jsonObject.optString("invoiceaddress")+"\n\n");

                    builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {

                                Bundle args = new Bundle();
                                args.putString("addressid", jsonObject.optString("id"));
                                args.putString("addressline1", jsonObject.optString("addressline1"));
                                args.putString("addressline2", jsonObject.optString("addressline2"));
                                args.putString("city", jsonObject.optString("city"));
                                args.putString("pincode", jsonObject.optString("pincode"));
                                args.putString("region", jsonObject.optString("region$_identifier"));
                                args.putString("country", jsonObject.optString("country$_identifier"));
                                args.putString("invoiceaddress", jsonObject.optString("invoiceaddress"));
                                args.putString("replace", "1");
                                CRMLeadAddress fragment = new CRMLeadAddress();
                                Home.crmLeadAddress = (CRMLeadAddress) fragment;
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