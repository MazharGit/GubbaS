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

import ssg1.gubba1.gubba1.g.Fragments.CRMLead;
import ssg1.gubba1.gubba1.g.Fragments.FirstContactForm;
import ssg1.gubba1.gubba1.g.Home;
import ssg1.gubba1.gubba1.g.R;

public class AdapterFirstContactFormList extends RecyclerView.Adapter<AdapterFirstContactFormList.ViewHolder> {

    JSONArray jsonArray;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdapterFirstContactFormList(Activity activity, JSONArray jsonArray) {
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

            holder.list_item.setText("Document Number : "+jsonObject.optString("documentno")+ " - " +"Company Name : "+ jsonObject.optString("companyname")+ " - " +"Location : "+ jsonObject.optString("organization$_identifier"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!jsonObject.optString("leadcreatecheck").equals("false")) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Document Number     : "+jsonObject.optString("documentno")
                                +"\n\n"+"Location                       : "+jsonObject.optString("organization$_identifier")
                                +"\n\n"+"Company Name          : "+jsonObject.optString("companyname")
                                +"\n\n"+"Contact Name             : "+jsonObject.optString("contactname")
                                +"\n\n"+"Contact Number         : "+jsonObject.optString("phonenumber")
                                +"\n\n"+"Email                            : "+jsonObject.optString("email")
                                +"\n\n"+"City                               : "+jsonObject.optString("cityname")
                                +"\n\n"+"Requirement Details  : "+jsonObject.optString("descprition")
                                +"\n\n"+"Priority                         : "+jsonObject.optString("priority")
                                +"\n\n"+"Lead Created              : "+jsonObject.optString("leadcreatecheck")+"\n\n");

                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                }catch (Exception e){
                                }
                            }
                        });
                        builder.show();




                    } else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Document Number     : "+jsonObject.optString("documentno")
                                +"\n\n"+"Location                       : "+jsonObject.optString("organization$_identifier")
                                +"\n\n"+"Company Name          : "+jsonObject.optString("companyname")
                                +"\n\n"+"Contact Name             : "+jsonObject.optString("contactname")
                                +"\n\n"+"Contact Number         : "+jsonObject.optString("phonenumber")
                                +"\n\n"+"Email                            : "+jsonObject.optString("email")
                                +"\n\n"+"City                               : "+jsonObject.optString("cityname")
                                +"\n\n"+"Requirement Details  : "+jsonObject.optString("descprition")
                                +"\n\n"+"Priority                         : "+jsonObject.optString("priority")
                                +"\n\n"+"Lead Created              : "+jsonObject.optString("leadcreatecheck")+"\n\n");

                        builder.setPositiveButton("Create lead", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                    Bundle args = new Bundle();
                                    args.putString("firstcontactid", jsonObject.optString("documentno"));
                                    args.putString("companyname", jsonObject.optString("companyname"));
                                    args.putString("documentno", jsonObject.optString("documentno"));
                                    args.putString("documentnoid", jsonObject.optString("id"));
                                    args.putString("location", jsonObject.optString("organization$_identifier"));
                                    args.putString("locationid", jsonObject.optString("organization"));
                                    args.putString("priority", jsonObject.optString("priority"));
                                    args.putString("iam", "1");
                                    CRMLead fragment = new CRMLead();
                                    Home.crmLead = (CRMLead) fragment;
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
                                args.putString("documentno", jsonObject.optString("documentno"));
                                args.putString("documentnoid", jsonObject.optString("id"));
                                args.putString("organization", jsonObject.optString("organization$_identifier"));
                                args.putString("organizationid", jsonObject.optString("organization"));
                                args.putString("companyname", jsonObject.optString("companyname"));
                                args.putString("contactname", jsonObject.optString("contactname"));
                                args.putString("phonenumber", jsonObject.optString("phonenumber"));
                                args.putString("email", jsonObject.optString("email"));
                                args.putString("cityname", jsonObject.optString("cityname"));
                                args.putString("descprition", jsonObject.optString("descprition"));
                                args.putString("priority", jsonObject.optString("priority"));
                                args.putString("id", jsonObject.optString("id"));
                                args.putString("replace", "1");
                                FirstContactForm fragment = new FirstContactForm();
                                Home.firstContactForm = (FirstContactForm) fragment;
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