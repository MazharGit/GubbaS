package ssg1.gubba1.gubba1.g.utils;

/**
 * Created by sun on 24/8/16.
 */
public class DataValidation {
    public static boolean isNullString(String str){
        boolean flag=false;

        try {


            if (str == null || str.equalsIgnoreCase("") || str.equalsIgnoreCase("null")) {
                flag = true;
            }
        }catch (NullPointerException e){}

        return flag;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
