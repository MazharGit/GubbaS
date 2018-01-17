package ssg1.gubba1.gubba1.g.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SharedPref {

    public static final String PREF_NAME = "SHARED+PREF_FOR_Device_id";



    static Context _context;

    // Constructor
    public SharedPref(Context c) {
        _context = c;
    }


    // for String value
    public static void writeString(String key, String value) {
        getEditor().putString(key, value).commit();

    }

    public static String readString(String key) {
        return getPreferences().getString(key, "");
    }

    public static String readString(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }



    private static Editor getEditor() {
        // TODO Auto-generated method stub
        return getPreferences().edit();
    }

    @SuppressWarnings("static-access")
    public static SharedPreferences getPreferences() {
        return _context.getSharedPreferences(PREF_NAME, _context.MODE_PRIVATE);
    }





    public static void writeStringArray(Map<String, Integer> value) {
        System.out.println("Counter Data size1: "+value.size());
        for (String s : value.keySet()) {
            System.out.println("Counter Data : "+ String.valueOf(value.get(s))+"key : "+s);
            getEditor().putString(s, String.valueOf(value.get(s))).commit();
        }


    }

    public static Map<String, Integer> readStringArray(ArrayList<String> value) {
        Map<String, Integer> counterMap=new HashMap<>();
        System.out.println("Counter Data size: "+value.size());
        for (int i=0;i<value.size();i++) {
            if(!(getPreferences().getString(value.get(i), "").equalsIgnoreCase(""))) {
                System.out.println("Counter Data no true: " + value.get(i));
                counterMap.put(value.get(i), Integer.parseInt(getPreferences().getString(value.get(i), "")));
            }
            else {
                System.out.println("Counter Data no false: "+value.get(i));
                counterMap.put(value.get(i), 0);
            }
        }
        return counterMap;
    }


    public static void removeData(String key)
    {
        //SharedPreferences settings = _context.getSharedPreferences(PREF_NAME Context.MODE_PRIVATE);
        getPreferences().edit().remove(key).commit();
    }

}
