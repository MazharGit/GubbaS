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

import ssg1.gubba1.gubba1.g.Fragments.SlotBookingProduct;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterProductList extends RecyclerView.Adapter<AdapterProductList.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdapterProductList(Activity activity, JSONArray jsonArray) {
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

            holder.list_item.setText("Batch Number : "+jsonObject.optString("batchno")+ " - " +"Product : "+ jsonObject.optString("product$_identifier"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Location                                : "+jsonObject.optString("organization$_identifier")
                                +"\n\n"+"Product                                  : "+jsonObject.optString("product$_identifier")
                                +"\n\n"+"Hybrid                                    : "+jsonObject.optString("bpproductHybrid")
                                +"\n\n"+"Batchnumber                        : "+jsonObject.optString("batchno")
                                +"\n\n"+"Customer Department         : "+jsonObject.optString("sQTCustomerdepartment")
                                +"\n\n"+"Quantity                                  : "+jsonObject.optString("quantity")
                                +"\n\n"+"packagingunits                      : "+jsonObject.optString("packagingunits")+"\n\n");


                        /*builder.setPositiveButton("Product List", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                    Bundle args = new Bundle();
                                    args.putString("organization", jsonObject.optString("organization$_identifier"));
                                    args.putString("organizationid", jsonObject.optString("organization"));
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
                        });*/

                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle args = new Bundle();
                                args.putString("organization", jsonObject.optString("organization$_identifier"));
                                args.putString("organizationid", jsonObject.optString("organization"));
                                args.putString("product", jsonObject.optString("product$_identifier"));
                                args.putString("productid", jsonObject.optString("product"));
                                args.putString("hybrid", jsonObject.optString("bpproductHybrid"));
                                args.putString("batchno", jsonObject.optString("batchno"));
                                args.putString("customerdepartment", jsonObject.optString("sQTCustomerdepartment$_identifier"));
                                args.putString("customerdepartmentid", jsonObject.optString("sQTCustomerdepartment"));
                                args.putString("quantity", jsonObject.optString("quantity"));
                                args.putString("packagingunits", jsonObject.optString("packagingunits"));
                                args.putString("id", jsonObject.optString("id"));
                                args.putString("replace", "1");
                                SlotBookingProduct fragment = new SlotBookingProduct();
                                Home.slotBookingProduct = (SlotBookingProduct) fragment;
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