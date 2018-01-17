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

import ssg1.gubba1.gubba1.g.Fragments.SlotBooking;
import ssg1.gubba1.gubba1.g.Fragments.SlotBookingProductList;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterSlotbookingList extends RecyclerView.Adapter<AdapterSlotbookingList.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdapterSlotbookingList(Activity activity, JSONArray jsonArray) {
        this.context=activity;
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

            holder.list_item.setText("Location : "+jsonObject.optString("organization$_identifier")+ " ,  " +"Customer Name : "+ jsonObject.optString("bpartner$_identifier"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Slotnumber                   : "+jsonObject.optString("documentno")
                                +"\n\n"+"Location                        : "+jsonObject.optString("organization$_identifier")
                                +"\n\n"+"Customer Name          : "+jsonObject.optString("bpartner$_identifier")
                                +"\n\n"+"Transaction Type          : "+jsonObject.optString("transactiontype")
                                +"\n\n"+"Packaging Type           : "+jsonObject.optString("bagtype")
                                +"\n\n"+"Schedule From Time   : "+jsonObject.optString("scheduledfromtime")
                                +"\n\n"+"Re Schedule From Time   : "+jsonObject.optString("rescheduledfromtime")
                                +"\n\n"+"To Time                          : "+jsonObject.optString("totime")
                                +"\n\n"+"Total Time                     : "+jsonObject.optString("totaltime")+"\n\n");


                        builder.setPositiveButton("Product List", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                    Bundle args = new Bundle();
                                    args.putString("prealertid", jsonObject.optString("id"));
                                    args.putString("organization", jsonObject.optString("organization$_identifier"));
                                    args.putString("organizationid", jsonObject.optString("organization"));
                                    args.putString("customernameid", jsonObject.optString("bpartner"));
                                    args.putString("bagtype", jsonObject.optString("bagtype"));
                                    SlotBookingProductList fragment = new SlotBookingProductList();
                                    Home.slotBookingProductList = (SlotBookingProductList) fragment;
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

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle args = new Bundle();
                                args.putString("slotnumber", jsonObject.optString("documentno"));
                                args.putString("organization", jsonObject.optString("organization$_identifier"));
                                args.putString("organizationid", jsonObject.optString("organization"));
                                args.putString("customername", jsonObject.optString("bpartner$_identifier"));
                                args.putString("customernameid", jsonObject.optString("bpartner"));
                                args.putString("transactiontype", jsonObject.optString("transactiontype"));
                                args.putString("packagingtype", jsonObject.optString("bagtype"));
                                args.putString("schedulefromtime", jsonObject.optString("scheduledfromtime"));
                                args.putString("reschedulefromtime", jsonObject.optString("rescheduledfromtime"));
                                args.putString("id", jsonObject.optString("id"));
                                args.putString("replace", "1");
                                SlotBooking fragment = new SlotBooking();
                                Home.slotBooking = (SlotBooking) fragment;
                                fragment.setArguments(args);
                                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                fragmentTransaction.commitAllowingStateLoss();
                                ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


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