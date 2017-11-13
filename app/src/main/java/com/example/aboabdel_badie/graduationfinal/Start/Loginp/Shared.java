package com.example.aboabdel_badie.graduationfinal.Start.Loginp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Abo Abdel-Badie on 6/24/2017.
 */

public class Shared {
    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_DOCTOR_PHONE ="d_phone";

    // Email address (make variable public to access from outside)
    public static final String KEY_ID = "email";

    // Constructor
    public Shared(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, context.MODE_WORLD_READABLE);
        editor =  pref.edit();
    }
    /**
     * Create login session
     * */
    public void save(String b , String ID, String name,String d_phone){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_ID, ID);
        //check login
        // Storing email in pref
        editor.putString("log",b );
        editor.putString(KEY_DOCTOR_PHONE,d_phone );

        // commit changes
        editor.commit();
    }
       /* sharedPreferences.edit()
                .putBoolean("rem", true )
                .putString("Key_Id",id)
                .putString("Key_Name",name)
                .putBoolean("log",b)
                .apply();
*/


    public String getId() {
            return pref.getString(KEY_ID,"");
    }
    public String getName(){return pref.getString(KEY_NAME,"");}
    public String getdphone(){return pref.getString(KEY_DOCTOR_PHONE,"");}
    public String is_login(){return pref.getString("log","0");}


}