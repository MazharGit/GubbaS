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

import ssg1.gubba1.gubba1.g.Fragments.InwardMemoProductBayWiseDenomination;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterinwardmemoproductbaywisedenomationList extends RecyclerView.Adapter<AdapterinwardmemoproductbaywisedenomationList.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdapterinwardmemoproductbaywisedenomationList(Activity activity, JSONArray jsonArray) {
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

            holder.list_item.setText("Product : "+jsonObject.optString("product$_identifier")+ " - " +"No. of Layers : "+ jsonObject.optString("nooflayers"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Chamber \n: "+jsonObject.optString("sQTChamber")
                                   +"\n\n"+"Bay \n: "+jsonObject.optString("locator")
                                   +"\n\n"+"Product \n: "+jsonObject.optString("product$_identifier")
                                +"\n\n"+"No. of Layers \n: "+jsonObject.optString("nooflayers")
                                +"\n\n"+"No. of rows/Matrix 1 \n: "+jsonObject.optString("noofrows")
                                +"\n\n"+"No. of rows/Matrix 1 \n: "+jsonObject.optString("noofcolumns")
                                +"\n\n"+"No. of columns/Matrix 2 \n: "+jsonObject.optString("noofrows2")
                                +"\n\n"+"No. of columns/Matrix 2 \n: "+jsonObject.optString("noofcolumns2")
                                +"\n\n"+"Loose Bags \n: "+jsonObject.optString("loosebags")
                                +"\n\n"+"Total No. of Handling Units \n: "+jsonObject.optString("handlinguints")
                                +"\n\n"+"Total No. of Handling Units UOM \n: "+jsonObject.optString("uom")+"\n\n");


                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle args = new Bundle();
                                args.putString("chamber", jsonObject.optString("sQTChamber"));
                                args.putString("bay", jsonObject.optString("locator"));
                                args.putString("product", jsonObject.optString("product$_identifier"));
                                args.putString("productid", jsonObject.optString("product"));
                                args.putString("nooflayers", jsonObject.optString("nooflayers"));
                                args.putString("noofrowsm1", jsonObject.optString("noofrows"));
                                args.putString("noofcolumnsm1", jsonObject.optString("noofcolumns"));
                                args.putString("noofrowsm2", jsonObject.optString("noofrows2"));
                                args.putString("noofcolumnsm2", jsonObject.optString("noofcolumns2"));
                                args.putString("loosebags", jsonObject.optString("loosebags"));
                                args.putString("hsndlingunits", jsonObject.optString("handlinguints"));
                                args.putString("hsndlingunitsuom", jsonObject.optString("uom"));
                                args.putString("replace", jsonObject.optString("1"));
                                args.putString("id", jsonObject.optString("id"));
                                InwardMemoProductBayWiseDenomination fragment = new InwardMemoProductBayWiseDenomination();
                                Home.inwardMemoProductBayWiseDenomination = (InwardMemoProductBayWiseDenomination) fragment;
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