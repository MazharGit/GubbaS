package ssg1.gubba1.gubba1.g;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.interfaces.IJsonParserInterface;
import ssg1.gubba1.gubba1.g.utils.CallWebService;
import ssg1.gubba1.gubba1.g.utils.CheckNetworkConnctivity;
import ssg1.gubba1.gubba1.g.utils.Constants;
import ssg1.gubba1.gubba1.g.utils.SharedPref;


public class Login extends AppCompatActivity implements IJsonParserInterface {

    EditText UserName;
    EditText Password;
    Button Login;
    CheckBox RememberMe;
    TextView Text;

    public static TextInputLayout LUserName,LPassword;

    public static String login_url;
    CallWebService service;

    SharedPref sp;

    public static String user, id, username, password;

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

        UserName.setTypeface(UbuntuFont);
        Password.setTypeface(UbuntuFont);
        RememberMe.setTypeface(UbuntuFont);

        LUserName = (TextInputLayout) findViewById(R.id.u);
        LPassword= (TextInputLayout) findViewById(R.id.p);
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getAppCurrentVersion();
            }
        }, 500);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    afterpopup();
            }
        });

    }

    private void getAppCurrentVersion()
    {
        if (CheckNetworkConnctivity.checkConnectivity(getApplicationContext()))
        {
            service.makeJsonObjectRequestGet(Constants.BASE_RIL_URL+"ADPreference?l=Madhavi&p=gubba&_where=attribute=%27mobileversion%27", true);
        }
    }

    public void VersionPOPUP()
    {

        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(Login.this);
        myAlertDialog.setMessage("New version available please update your app");
        // myAlertDialog.setMessage("Select picture mode");

        myAlertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface arg0, int arg1)
            {

                Intent launchPlaystoreIntent = new Intent(
                        Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ssg1.gubba1.gubba1.g&hl=en"));
                startActivity(launchPlaystoreIntent);
            }
        });

        myAlertDialog.show();
    }

    private void userlogin() throws Exception {

        login_url = "ws/com.gubba.crm.AppLoginData";
        service.makeJsonObjectRequestPost(Constants.BASE_RIL + login_url, new JSONObject().put("data", new JSONObject().put("username", username)), true);
    }

    @Override
    public void parseJsonResult(JSONObject json, String webserviceName) {

        Constants.jsonLoginResponse=json;
        System.out.println("login response"+json);
        if (webserviceName.equalsIgnoreCase(Constants.BASE_RIL + login_url)) {

            if (json.optJSONObject("response").optJSONObject("data").optString("status").equalsIgnoreCase("0")) {

                System.out.println("LOGIN RESPONSE : " + json.toString());
                Intent intentFarmReg = new Intent(getApplicationContext(), Home.class);
                startActivity(intentFarmReg);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                Login.this.finish();

            } else {
                    /*UserName.setError("Enter Valid Username");
                UserName.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    LUserName.setError("Enter Valid Password");*/
                //Toast.makeText(Login.this, "User Name/Password does not match", Toast.LENGTH_SHORT).show();

            }
        }
        else if(webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL +"ADPreference?l=Madhavi&p=gubba&_where=attribute=%27mobileversion%27")) {

            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {

                PackageManager manager = getPackageManager();
                PackageInfo info = null;
                try {
                    info = manager.getPackageInfo(
                            getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String version = info.versionName;

                try {
                    if (version.equalsIgnoreCase(json.optJSONObject("response").optJSONArray("data").optJSONObject(0).optString("searchKey")))
                    {
                        VersionPOPUP();
                    }


                }catch (Exception e){}
            }

        }
        /*else if (webserviceName.equalsIgnoreCase(Constants.BASE_RIL_URL + onesigid)) {
            if (json.optJSONObject("response").optString("status").equalsIgnoreCase("0")) {
                //Toast.makeText(LoginPage.this, "Sccessfully send one signal id !!!", Toast.LENGTH_SHORT).show();


            }
        }*/
    }

    protected void afterpopup() {

        try
        {
            username = UserName.getText().toString();
            password = Password.getText().toString();

            if (username.length() == 0 || password.length() == 0)
            {
                LUserName.setError("Enter Valid Username");
                LPassword.setError("Enter Valid Password");
            }

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

            int permission = ActivityCompat.checkSelfPermission(Login.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

            ActivityCompat.requestPermissions(
                    Login.this,
                    new String[]{android.Manifest.permission.INTERNET,
                            android.Manifest.permission.ACCESS_NETWORK_STATE,},
                    2
            );


                    if (CheckNetworkConnctivity.checkConnectivity(getApplicationContext())) {
                        try {
                            userlogin();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    else
                    {
                        Toast.makeText(Login.this, "Check Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
        }

    }
}