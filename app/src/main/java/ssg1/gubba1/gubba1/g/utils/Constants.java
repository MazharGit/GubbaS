package ssg1.gubba1.gubba1.g.utils;

import org.json.JSONObject;

import ssg1.gubba1.gubba1.g.AppController;

public class Constants {

    public SharedPref sp=new SharedPref(AppController.getInstance());
    //--------------------- BASE URL ----------------------------
    public static String BASE_RIL_URL = "http://78.46.107.62:8080/gubbaerp/org.openbravo.service.json.jsonrest/";
    public static String BASE_RIL = "http://78.46.107.62:8080/gubbaerp/";
    public static String BASE_PATH = "Test/attachment/";


   //------------------------Constant------------------------------
    public String USER_N_PASSWORD="l=" + sp.readString("Username") + "&p=" + sp.readString("password") ;
    public String USER_ID=sp.readString("ID");

    public static JSONObject jsonLoginResponse;

}