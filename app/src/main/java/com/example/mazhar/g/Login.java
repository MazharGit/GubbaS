package com.example.mazhar.g;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mazhar.g.interfaces.IJsonParserInterface;
import com.example.mazhar.g.utils.CallWebService;
import com.example.mazhar.g.utils.CheckNetworkConnctivity;
import com.example.mazhar.g.utils.Constants;
import com.example.mazhar.g.utils.SaveDataInExternalStorage;
import com.example.mazhar.g.utils.SharedPref;

import org.json.JSONObject;


public class Login extends AppCompatActivity implements IJsonParserInterface {

    EditText UserName;
    EditText Password;
    Button Login;
    CheckBox RememberMe;
    TextView Text;

    public static String login_url;
    CallWebService service;

    SharedPref sp;

    public static String user, id, username, password;
    public static String bp;
    public static String bpname;
    public static String vlcc;
    public static String vlccname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new CallWebService(this);
        sp = new SharedPref(this);

        Typeface UbuntuFont = Typeface.createFromAsset(getAssets(),"Ubuntu-M.ttf");

        Login = (Button) findViewById(R.id.login_button);
        Login.setTypeface(UbuntuFont);

        RememberMe = (CheckBox) findViewById(R.id.rememberMe);

        UserName = (EditText) findViewById(R.id.et_uname);
        Password = (EditText) findViewById(R.id.et_passwd);
        Text=(TextView) findViewById(android.support.v4.R.id.text);

        UserName.setTypeface(UbuntuFont);
        Password.setTypeface(UbuntuFont);
        RememberMe.setTypeface(UbuntuFont);

        TextInputLayout LUserName = (TextInputLayout) findViewById(R.id.u);
        TextInputLayout LPassword= (TextInputLayout) findViewById(R.id.p);
        LUserName.setTypeface(UbuntuFont);
        LPassword.setTypeface(UbuntuFont);

        try {
            if (Boolean.parseBoolean(sp.readString("isChecked"))) {
                RememberMe.setChecked(true);
                UserName.setText(sp.readString("User"));
                Password.setText(sp.readString("pswd"));
            } else {
                RememberMe.setChecked(false);
            }
        }catch (Exception e){}

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    username = UserName.getText().toString();
                    password = Password.getText().toString();

                    sp.removeData("Username");
                    sp.removeData("password");

                    sp.writeString("Username", UserName.getText().toString());
                    sp.writeString("password", Password.getText().toString());

                    if (RememberMe.isChecked()) {
                        sp.writeString("isChecked", "true");
                        sp.writeString("User", UserName.getText().toString());
                        sp.writeString("pswd", Password.getText().toString());
                    } else {
                        sp.writeString("isChecked", "false");
                        sp.writeString("User", UserName.getText().toString());
                        sp.writeString("pswd", Password.getText().toString());
                    }
                }
                catch (Exception e){}

                if (Build.VERSION.SDK_INT >= 23) {

                    int permission = ActivityCompat.checkSelfPermission(Login.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        // We don't have permission so prompt the user
                        ActivityCompat.requestPermissions(
                                Login.this,
                                new String[]{Manifest.permission.INTERNET,
                                        Manifest.permission.ACCESS_NETWORK_STATE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.SEND_SMS,
                                        Manifest.permission.CAMERA},
                                2
                        );

                    } else if (permission == PackageManager.PERMISSION_GRANTED) {
                        try {
                            if (CheckNetworkConnctivity.checkConnectivity(getApplicationContext())) {
                                try {
                                    userlogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setMessage("Gubba Welcomes You");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void userlogin() throws Exception {

        login_url = "ws/com.gubba.crm.AppLoginData";
        service.makeJsonObjectRequestPost(Constants.BASE_RIL + login_url, new JSONObject().put("data", new JSONObject().put("username", username)), true);
    }

    @Override
    public void parseJsonResult(JSONObject json, String webserviceName) {

        System.out.println("RESPONSE CHECK : " + json.toString());
        Constants.jsonObject=json;
        if (webserviceName.equalsIgnoreCase(Constants.BASE_RIL + login_url)) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {

                String fileName = Login.class.getName();
                new SaveDataInExternalStorage().deleteFile(fileName);
                new SaveDataInExternalStorage().writeToFile(json.toString(), fileName+".txt");

                System.out.println("LOGIN RESPONSE : " + json.toString());
                JSONObject jsonObject = json.optJSONObject("response").optJSONObject("data");

                /*id = jsonObject.optString("UserID");
                bp = jsonObject.optString("ID");
                bpname = jsonObject.optString("name");
                vlcc = jsonObject.optString("vlccID");
                vlccname = jsonObject.optString("vlccName");*/


                /*try {
                    sendOneSignalId();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                /*sp.removeData("ID");
                sp.removeData("BPartnerId");
                sp.removeData("BPartnerName");

                sp.removeData("VlccName");
                sp.removeData("VlccId");
                sp.removeData("ratechartID");
                sp.removeData("ratechartName");
                sp.removeData("FARMER_COUNT");
                sp.removeData("USER_ROLE");
                sp.removeData("clientId");
                sp.removeData("orgID");
                sp.removeData("vlcccode");
                sp.removeData("Milkproducts");
                sp.removeData("vlcpcode");
                sp.removeData("vlcccode");
                sp.removeData("ChillingCenterID");
                sp.removeData("CC-Org");
                sp.removeData("categiryid");
                sp.removeData("categiryname");
                sp.removeData("Vlccforemployes");
*/

                /*sp.writeString("ID", id);
                sp.writeString("BPartnerId", bp);
                sp.writeString("BPartnerName", bpname);
                sp.writeString("VlccName", vlccname);
                sp.writeString("VlccId", vlcc);
                sp.writeString("vlcpcode", (jsonObject.optString("vlcpcode")));
                sp.writeString("vlcccode", (jsonObject.optString("vlcccode")));
                sp.writeString("clientId", (jsonObject.optString("ClientID")));
                sp.writeString("ChillingCenterID",jsonObject.optString("ChillingCenterID"));
                sp.writeString("CC-Org",jsonObject.optString("CC-Org"));
                sp.writeString("orgID", (jsonObject.optString("OrgID")));
                sp.writeString("Milkproducts", (jsonObject.optJSONArray("Product")).toString());
                sp.writeString("FARMER_COUNT", Integer.toString(jsonObject.optInt("RowCount") + 1));
                sp.writeString("USER_ROLE", (jsonObject.optString("Windowname")));
                sp.writeString("categiryid",(jsonObject.optString("CategoryID")));
                sp.writeString("categiryname",(jsonObject.optString("CategoryName")));
                if(sp.readString("USER_ROLE").equalsIgnoreCase("Employee"))
                    sp.writeString("Vlccforemployes",(jsonObject.optJSONArray("VLCC").toString()));*/

                Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                Intent intentFarmReg = new Intent(getApplicationContext(), Home.class);
                startActivity(intentFarmReg);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                Login.this.finish();

            } else {
                Toast.makeText(Login.this, "User Name/Password does not match", Toast.LENGTH_SHORT).show();

            }
        } /*else if (webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + onesigid)) {
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                //Toast.makeText(LoginPage.this, "Sccessfully send one signal id !!!", Toast.LENGTH_SHORT).show();


            }
        }*/
    }
}
