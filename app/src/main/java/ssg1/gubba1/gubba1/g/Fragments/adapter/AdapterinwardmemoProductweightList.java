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

import ssg1.gubba1.gubba1.g.Fragments.InwardMemoProductWeight;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterinwardmemoProductweightList extends RecyclerView.Adapter<AdapterinwardmemoProductweightList.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdapterinwardmemoProductweightList(Activity activity, JSONArray jsonArray) {
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

            holder.list_item.setText("Weight : "+jsonObject.optString("weight")+ " - " +"Product : "+ jsonObject.optString("sQTProduct$_identifier"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Weight \n: "+jsonObject.optString("weight")
                                   +"\n\n"+"Product \n: "+jsonObject.optString("sQTProduct$_identifier") +"\n\n");


                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle args = new Bundle();
                                args.putString("weight", jsonObject.optString("weight"));
                                args.putString("product", jsonObject.optString("sQTProduct$_identifier"));
                                args.putString("productid", jsonObject.optString("sQTProduct"));
                                args.putString("replace", jsonObject.optString("1"));
                                args.putString("id", jsonObject.optString("id"));
                                InwardMemoProductWeight fragment = new InwardMemoProductWeight();
                                Home.inwardMemoProductWeight = (InwardMemoProductWeight) fragment;
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