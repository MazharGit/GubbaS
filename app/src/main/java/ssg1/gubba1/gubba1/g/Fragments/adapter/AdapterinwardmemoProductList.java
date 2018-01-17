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

import ssg1.gubba1.gubba1.g.Fragments.InwardMemoProduct;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterinwardmemoProductList extends RecyclerView.Adapter<AdapterinwardmemoProductList.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdapterinwardmemoProductList(Activity activity, JSONArray jsonArray) {
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

            holder.list_item.setText("Product Name : "+jsonObject.optString("organization$_identifier")+ " - " +"Batch Number : "+ jsonObject.optString("documentno"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("product Category \n: "+jsonObject.optString("sQTOrg$_identifier")
                                   +"\n\n"+"Product Name \n: "+jsonObject.optString("organization$_identifier")
                                   +"\n\n"+"Hybrid \n: "+jsonObject.optString("documenttype")
                                +"\n\n"+"Customer Department \n: "+jsonObject.optString("documentno")
                                +"\n\n"+"Batch Number \n: "+jsonObject.optString("sQTBpcustomer$_identifier")
                                +"\n\n"+"Moisture Percent \n: "+jsonObject.optString("bpartnerLocation$_identifier")
                                +"\n\n"+"Handling Quantity(Actual) \n: "+jsonObject.optString("dockintime")
                                +"\n\n"+"Handling Quantity(DC) \n: "+jsonObject.optString("dockouttime")
                                +"\n\n"+"Handling UOM \n: "+jsonObject.optString("dcno")
                                +"\n\n"+"Billing Quantity \n: "+jsonObject.optString("pestinflected")
                                +"\n\n"+"Billing UOM \n: "+jsonObject.optString("temp1")
                                +"\n\n"+"Chamber \n: "+jsonObject.optString("temp2")
                                +"\n\n"+"Bay Number \n: "+jsonObject.optString("packingcondition")
                                +"\n\n"+"Manufacturing Date \n: "+jsonObject.optString("seasoncode")
                                +"\n\n"+"Grill In/Out \n: "+jsonObject.optString("user$_identifier")
                                +"\n\n"+"Expiry Date \n: "+jsonObject.optString("sQTGateentry$_identifier") +"\n\n");


                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle args = new Bundle();
                                args.putString("ProductCategory", jsonObject.optString("sQTOrg$_identifier"));
                                args.putString("ProductCategoryid", jsonObject.optString("sQTOrg$_identifier"));
                                args.putString("ProductName", jsonObject.optString("sQTOrg"));
                                args.putString("ProductNameid", jsonObject.optString("sQTOrg"));
                                args.putString("Hybrid", jsonObject.optString("sQTOrg$_identifier"));
                                args.putString("Hybridid", jsonObject.optString("sQTOrg$_identifier"));
                                args.putString("CustomerDepartment", jsonObject.optString("sQTOrg"));
                                args.putString("CustomerDepartmentid", jsonObject.optString("sQTOrg"));
                                args.putString("BatchNumber", jsonObject.optString("documentno"));
                                args.putString("HandlingQtyActual", jsonObject.optString("documenttype"));
                                args.putString("HandlingQuantityDC", jsonObject.optString("sQTBpcustomer$_identifier"));
                                args.putString("HandlingUOM", jsonObject.optString("sQTBpcustomer"));
                                args.putString("BillingQuantity", jsonObject.optString("sQTBpcustomer"));
                                args.putString("BillingUOM", jsonObject.optString("sQTBpcustomer"));
                                args.putString("Chamber", jsonObject.optString("bpartnerLocation$_identifier"));
                                args.putString("Chamberid", jsonObject.optString("bpartnerLocation$_identifier"));
                                args.putString("BayNumber", jsonObject.optString("bpartnerLocation"));
                                args.putString("BayNumberid", jsonObject.optString("bpartnerLocation"));
                                args.putString("ManufacturingDate", jsonObject.optString("dockintime"));
                                args.putString("Grill", jsonObject.optString("dockouttime"));
                                args.putString("ExpiryDate", jsonObject.optString("dcno"));
                                args.putString("replace", jsonObject.optString("1"));
                                args.putString("id", jsonObject.optString("id"));
                                InwardMemoProduct fragment = new InwardMemoProduct();
                                Home.inwardMemoProduct = (InwardMemoProduct) fragment;
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