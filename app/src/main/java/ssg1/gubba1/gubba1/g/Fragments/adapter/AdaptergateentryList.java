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

import ssg1.gubba1.gubba1.g.Fragments.Gateentry;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdaptergateentryList extends RecyclerView.Adapter<AdaptergateentryList.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdaptergateentryList(Activity activity, JSONArray jsonArray) {
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

            holder.list_item.setText("Location : "+jsonObject.optString("organization$_identifier")+ " - " +"Pre Alert Slot Number : "+ jsonObject.optString("sQTPrealert$_identifier"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Organization \n: "+jsonObject.optString("sQTOrg$_identifier")
                                   +"\n\n"+"Location \n: "+jsonObject.optString("organization$_identifier")
                                   +"\n\n"+"Pre Alert Slot No. \n: "+jsonObject.optString("sQTPrealert$_identifier")
                                +"\n\n"+"Transaction Type \n: "+jsonObject.optString("transactiontype")
                                +"\n\n"+"Schedule From Time \n: "+jsonObject.optString("scheduledfromtime")
                                +"\n\n"+"Pre Alert Scheduled In Time \n: "+jsonObject.optString("scheduledintime")
                                +"\n\n"+"Customer Name \n: "+jsonObject.optString("sQTCustomer$_identifier")
                                +"\n\n"+"Vehicle Number \n: "+jsonObject.optString("vehicleno")
                                +"\n\n"+"Driver Name \n: "+jsonObject.optString("sQTDriver$_identifier")
                                +"\n\n"+"Driver Phone Number \n: "+jsonObject.optString("phone")
                                +"\n\n"+"Driver License Number \n: "+jsonObject.optString("licenseno")
                                +"\n\n"+"DC Number \n: "+jsonObject.optString("dcnumber")
                                +"\n\n"+"Total no of Packaging Units as per DC \n: "+jsonObject.optString("unitperdc")
                                +"\n\n"+"Total Quantity as per DC \n: "+jsonObject.optString("qtyperdc")
                                +"\n\n"+"Pren Alert Resceduled In Time \n: "+jsonObject.optString("rescheduledintime")
                                +"\n\n"+"Dock in Time \n: "+jsonObject.optString("dockintime")
                                +"\n\n"+"Gate Entry Number \n: "+jsonObject.optString("documentno")
                                +"\n\n"+"Gate Entry Date \n: "+jsonObject.optString("gateentrydate")
                                +"\n\n"+"Status \n: "+jsonObject.optString("status")
                                +"\n\n"+"Dock in Time \n: "+jsonObject.optString("dockintime")
                                +"\n\n"+"Dock out Time \n: "+jsonObject.optString("dockouttime")
                                +"\n\n"+"Gate out Time \n: "+jsonObject.optString("gateouttime")+"\n\n");


                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle args = new Bundle();
                                args.putString("sqtorganization", jsonObject.optString("sQTOrg$_identifier"));
                                args.putString("sqtorganizationid", jsonObject.optString("sQTOrg"));
                                args.putString("organization", jsonObject.optString("documentno"));
                                args.putString("location", jsonObject.optString("organization$_identifier"));
                                args.putString("locationid", jsonObject.optString("organization"));
                                args.putString("slotno", jsonObject.optString("sQTPrealert$_identifier"));
                                args.putString("slotnoid", jsonObject.optString("sQTPrealert"));
                                args.putString("transactiontype", jsonObject.optString("transactiontype"));
                                args.putString("schedulefromtime", jsonObject.optString("scheduledfromtime"));
                                args.putString("scheduledintime", jsonObject.optString("scheduledintime"));
                                args.putString("customername", jsonObject.optString("sQTCustomer$_identifier"));
                                args.putString("customernameid", jsonObject.optString("sQTCustomer"));
                                args.putString("vehiclenumber", jsonObject.optString("vehicleno"));
                                args.putString("drivername", jsonObject.optString("sQTDriver$_identifier"));
                                args.putString("drivernameid", jsonObject.optString("sQTDriver"));
                                args.putString("driverphonenumber", jsonObject.optString("phone"));
                                args.putString("driverlicensenumber", jsonObject.optString("licenseno"));
                                args.putString("dcnumber", jsonObject.optString("dcnumber"));
                                args.putString("packagingunits", jsonObject.optString("unitperdc"));
                                args.putString("quantitydc", jsonObject.optString("qtyperdc"));
                                args.putString("rescheduledintime", jsonObject.optString("rescheduledintime"));
                                args.putString("dockintime", jsonObject.optString("dockintime"));
                                args.putString("gateentrynumber", jsonObject.optString("documentno"));
                                args.putString("gateentrydate", jsonObject.optString("gateentrydate"));
                                args.putString("status", jsonObject.optString("status"));
                                args.putString("dockintime", jsonObject.optString("dockintime"));
                                args.putString("dockouttime", jsonObject.optString("dockouttime"));
                                args.putString("gateouttime", jsonObject.optString("gateouttime"));
                                args.putString("id", jsonObject.optString("id"));
                                args.putString("replace", "1");
                                Gateentry fragment = new Gateentry();
                                Home.gateentry = (Gateentry) fragment;
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