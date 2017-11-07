package com.example.mazhar.g.utils;

import android.os.Environment;

import com.example.mazhar.g.AppController;

import org.json.JSONObject;

import java.io.File;

public class Constants {

    public SharedPref sp=new SharedPref(AppController.getInstance());
    //--------------------- BASE URL ----------------------------
    public static String BASE_RIL_URL = "http://78.46.107.62:8080/gubbaerp/org.openbravo.service.json.jsonrest/";
    public static String BASE_RIL = "http://78.46.107.62:8080/gubbaerp/";
    public static String BASE_PATH = "Test/attachment/";




   //------------------------Constant------------------------------
    public String USER_N_PASSWORD="l=" + sp.readString("Username") + "&p=" + sp.readString("password") ;
    public String USER_ID=sp.readString("ID");
    public String BUSINESS_PARTNER_NAME=sp.readString("BPartnerName");
    public String VLCC_NAME=sp.readString("VlccName");
    public String VLCC=sp.readString("VlccId");
    public String CLIENT_ID=sp.readString("clientId");
    public String ORGNAZATION_ID=sp.readString("orgID");

    public static JSONObject jsonObject;

    //--------------------- METHODS -----------------------------
    public static File root= Environment.getExternalStorageDirectory();
    public static File PATH=new File(root.getAbsolutePath()+"/GubbaStore");

}
