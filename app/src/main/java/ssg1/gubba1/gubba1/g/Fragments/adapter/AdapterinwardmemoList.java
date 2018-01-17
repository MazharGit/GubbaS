package ssg1.gubba1.gubba1.g.Fragments.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.R;

public class AdapterinwardmemoList extends RecyclerView.Adapter<AdapterinwardmemoList.ViewHolder> {

    JSONArray jsonArray;
    private Context context;

    Dialog dialog;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView list_item;


        public ViewHolder(View convertView) {
            super(convertView);

            list_item = (TextView) convertView.findViewById(R.id.product_name);

        }
    }



    public AdapterinwardmemoList(Activity activity, JSONArray jsonArray) {
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

            holder.list_item.setText("Location : "+jsonObject.optString("organization$_identifier")+ " - " +"Document Number : "+ jsonObject.optString("documentno"));
             }catch (Exception ex){
            ex.printStackTrace();}

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "in", Toast.LENGTH_SHORT).show();

                dialog = new Dialog(context);
                dialog.setContentView(R.layout.farm_reg_confirm);

                TextView organization = (TextView) dialog.findViewById(R.id.organization);
                TextView location = (TextView) dialog.findViewById(R.id.location);
                TextView documenttype = (TextView) dialog.findViewById(R.id.documenttype);
                TextView documentnumber = (TextView) dialog.findViewById(R.id.documentnumber);
                TextView customername = (TextView) dialog.findViewById(R.id.customername);
                TextView customeraddress = (TextView) dialog.findViewById(R.id.customeraddress);
                TextView dockintime = (TextView) dialog.findViewById(R.id.dockintime);
                TextView dockouttime = (TextView) dialog.findViewById(R.id.dockouttime);
                TextView dcno = (TextView) dialog.findViewById(R.id.dcno);
                TextView pestinfected = (TextView) dialog.findViewById(R.id.pestinfected);
                TextView temperature1 = (TextView) dialog.findViewById(R.id.temperature1);
                TextView temperature2 = (TextView) dialog.findViewById(R.id.temperature2);
                TextView packingcondition = (TextView) dialog.findViewById(R.id.packingcondition);
                TextView seasoncode = (TextView) dialog.findViewById(R.id.seasoncode);
                TextView customerrepresentative = (TextView) dialog.findViewById(R.id.customerrepresentative);
                TextView gateentrynumber = (TextView) dialog.findViewById(R.id.gateentrynumber);
                TextView totalpackagingunits = (TextView) dialog.findViewById(R.id.totalpackagingunits);
                TextView totalquantity = (TextView) dialog.findViewById(R.id.totalquantity);
                TextView status = (TextView) dialog.findViewById(R.id.status);
                TextView hamalicontractor = (TextView) dialog.findViewById(R.id.hamalicontractor);
                TextView noofhamali = (TextView) dialog.findViewById(R.id.noofhamali);
                TextView spillagequantity = (TextView) dialog.findViewById(R.id.spillagequantity);
                TextView hamalimamulcharges = (TextView) dialog.findViewById(R.id.hamalimamulcharges);

                organization.setText(":"+jsonObject.optString("sQTOrg$_identifier"));
                location.setText(":"+jsonObject.optString("organization$_identifier"));
                documenttype.setText(":"+jsonObject.optString("documenttype"));
                documentnumber.setText(":"+jsonObject.optString("documentno"));
                customername.setText(":"+jsonObject.optString("sQTBpcustomer$_identifier"));
                customeraddress.setText(":"+jsonObject.optString("bpartnerLocation$_identifier"));
                dockintime.setText(":"+jsonObject.optString("dockintime"));
                dockouttime.setText(":"+jsonObject.optString("dockouttime"));
                dcno.setText(":"+jsonObject.optString("dcno"));
                pestinfected.setText(":"+jsonObject.optString("pestinflected"));
                temperature1.setText(":"+jsonObject.optString("temp1"));
                temperature2.setText(":"+jsonObject.optString("temp2"));
                packingcondition.setText(":"+jsonObject.optString("packingcondition"));
                seasoncode.setText(":"+jsonObject.optString("seasoncode"));
                customerrepresentative.setText(":"+jsonObject.optString("user$_identifier"));
                gateentrynumber.setText(":"+jsonObject.optString("sQTGateentry$_identifier"));
                totalpackagingunits.setText(":"+jsonObject.optString("unitsperdc"));
                totalquantity.setText(":"+jsonObject.optString("qtyperdc"));
                status.setText(":"+jsonObject.optString("status"));
                hamalicontractor.setText(":"+jsonObject.optString("sQTCbpartner$_identifier"));
                noofhamali.setText(":"+jsonObject.optString("noofhamali"));
                spillagequantity.setText(":"+jsonObject.optString("spillageqty"));
                hamalimamulcharges.setText(":"+jsonObject.optString("hamalimamulcharges"));

                Button Edit = (Button) dialog.findViewById(R.id.edit);
                Edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                    }
                });

                Button Product = (Button) dialog.findViewById(R.id.product);
                Edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "product", Toast.LENGTH_SHORT).show();
                    }
                });

                Button Inspection = (Button) dialog.findViewById(R.id.inspection);
                Edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "inspection", Toast.LENGTH_SHORT).show();
                    }
                });

                Button ProductWeight = (Button) dialog.findViewById(R.id.productweight);
                Edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "productweight", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();


                /*try {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("Organization \n: "+jsonObject.optString("sQTOrg$_identifier")
                                   +"\n\n"+"Location \n: "+jsonObject.optString("organization$_identifier")
                                   +"\n\n"+"Document Type \n: "+jsonObject.optString("documenttype")
                                +"\n\n"+"Document Number \n: "+jsonObject.optString("documentno")
                                +"\n\n"+"Customer Name \n: "+jsonObject.optString("sQTBpcustomer$_identifier")
                                +"\n\n"+"Customer Address \n: "+jsonObject.optString("bpartnerLocation$_identifier")
                                +"\n\n"+"Dock In Time \n: "+jsonObject.optString("dockintime")
                                +"\n\n"+"Dock Out Time \n: "+jsonObject.optString("dockouttime")
                                +"\n\n"+"DC No \n: "+jsonObject.optString("dcno")
                                +"\n\n"+"Pest Infected \n: "+jsonObject.optString("pestinflected")
                                +"\n\n"+"Temperature1 \n: "+jsonObject.optString("temp1")
                                +"\n\n"+"Temperature2 \n: "+jsonObject.optString("temp2")
                                +"\n\n"+"Packing Condition \n: "+jsonObject.optString("packingcondition")
                                +"\n\n"+"Season Code \n: "+jsonObject.optString("seasoncode")
                                +"\n\n"+"Customer Representative \n: "+jsonObject.optString("user$_identifier")
                                +"\n\n"+"Gate Entry Number \n: "+jsonObject.optString("sQTGateentry$_identifier")
                                +"\n\n"+"Total Packaging Units \n: "+jsonObject.optString("unitsperdc")
                                +"\n\n"+"Total Quantity \n: "+jsonObject.optString("qtyperdc")
                                +"\n\n"+"Status \n: "+jsonObject.optString("status")
                                +"\n\n"+"Hamali Contractor \n: "+jsonObject.optString("sQTCbpartner$_identifier")
                                +"\n\n"+"No of Hamali \n: "+jsonObject.optString("noofhamali")
                                +"\n\n"+"Spillage Quantity \n: "+jsonObject.optString("spillageqty")
                                +"\n\n"+"Hamali Mamul Charges \n: "+jsonObject.optString("hamalimamulcharges")+"\n\n");


                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bundle args = new Bundle();
                                args.putString("Organization", jsonObject.optString("sQTOrg$_identifier"));
                                args.putString("Organizationid", jsonObject.optString("sQTOrg"));
                                args.putString("Location", jsonObject.optString("sQTOrg$_identifier"));
                                args.putString("Locationid", jsonObject.optString("sQTOrg"));
                                args.putString("DocumentNo", jsonObject.optString("documentno"));
                                args.putString("DocumentType", jsonObject.optString("documenttype"));
                                args.putString("CustomerName", jsonObject.optString("sQTBpcustomer$_identifier"));
                                args.putString("Customerid", jsonObject.optString("sQTBpcustomer"));
                                args.putString("CustomerAddress", jsonObject.optString("bpartnerLocation$_identifier"));
                                args.putString("CustomerAddressid", jsonObject.optString("bpartnerLocation"));
                                args.putString("DockInTime", jsonObject.optString("dockintime"));
                                args.putString("DockOutTime", jsonObject.optString("dockouttime"));
                                args.putString("DCNo", jsonObject.optString("dcno"));
                                args.putString("PestInfected", jsonObject.optString("pestinflected"));
                                args.putString("Temperature1", jsonObject.optString("temp1"));
                                args.putString("Temperature2", jsonObject.optString("temp2"));
                                args.putString("PackingCondition", jsonObject.optString("packingcondition"));
                                args.putString("SeasonCode", jsonObject.optString("seasoncode"));
                                args.putString("CustomerRepresentative", jsonObject.optString("user$_identifier"));
                                args.putString("CustomerRepresentativeid", jsonObject.optString("user"));
                                args.putString("GateEntryNumber", jsonObject.optString("sQTGateentry$_identifier"));
                                args.putString("GateEntryNumberid", jsonObject.optString("sQTGateentry"));
                                args.putString("TotalPackagingUnits", jsonObject.optString("unitsperdc"));
                                args.putString("TotalQuantity", jsonObject.optString("qtyperdc"));
                                args.putString("Status", jsonObject.optString("status"));
                                args.putString("HamaliContractor", jsonObject.optString("sQTCbpartner$_identifier"));
                                args.putString("Hamaliid", jsonObject.optString("sQTCbpartner"));
                                args.putString("NoofHamali", jsonObject.optString("noofhamali"));
                                args.putString("SpillageQuantity", jsonObject.optString("spillageqty"));
                                args.putString("vehicalno", jsonObject.optString("vehicalno"));
                                args.putString("HamaliMamulCharges", jsonObject.optString("hamalimamulcharges"));
                                args.putString("id", jsonObject.optString("id"));
                                InwardMemo fragment = new InwardMemo();
                                Home.inwardMemo = (InwardMemo) fragment;
                                fragment.setArguments(args);
                                FragmentTransaction fragmentTransaction = Home.getInstance().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                fragmentTransaction.replace(R.id.frame, fragment, "Home").addToBackStack("Home");
                                fragmentTransaction.commitAllowingStateLoss();
                                ((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


                            }
                        });

                        builder.show();



                }catch (Exception e){e.printStackTrace();}*/

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