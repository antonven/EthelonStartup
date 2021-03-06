package myapps.wycoco.com.ethelonstartup.Service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import myapps.wycoco.com.ethelonstartup.Activities.HomeActivity;
import myapps.wycoco.com.ethelonstartup.Activities.LoginActivity;

/**
 * Created by dell on 1/2/2018.
 */

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context mContext;

    private static final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "UserPref";
    private static final String IS_LOGIN = "isLoggedIn";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "fbProfileName";
    public static final String KEY_PICTURE = "fbProfilePicture";
    public static final String KEY_PROFILE_ID = "profileId";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_VOLUNTEER_ID = "volunteer_id";
    public static final String KEY_API_TOKEN = "api_token";
    public static final String KEY_SKILL_SET = "skills";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_BIRTHDAY = "birthday";





    public SessionManager(Context mContext){
        this.mContext = mContext;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email, String fbProfileName, String fbProfilePicture, String profileId, String first_name, String last_name, String volunteer_id, String api_token, String birthday){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);

        editor.putString(KEY_NAME, fbProfileName);
        editor.putString(KEY_PICTURE, fbProfilePicture);
        editor.putString(KEY_PROFILE_ID, profileId);
        editor.putString(KEY_FIRST_NAME, first_name);
        editor.putString(KEY_LAST_NAME, last_name);
        editor.putString(KEY_VOLUNTEER_ID, volunteer_id);
        editor.putString(KEY_API_TOKEN, api_token);
        editor.putString(KEY_BIRTHDAY, birthday);
        Log.e("ANTON GWAPO", email + birthday);
        editor.commit();
    }

    public void createLoginSessionForEthelon(String email, String username, String volunteer_id, String api_token){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_USER_NAME, username);
        editor.putString(KEY_VOLUNTEER_ID, volunteer_id);
        editor.putString(KEY_API_TOKEN, api_token);
        editor.commit();
    }

    public void createSkillPref(Set<String> skillSet){
        editor.putStringSet(KEY_SKILL_SET, skillSet);
        Log.e("SKILLSETSESONMANAGER", skillSet.toString());
        editor.commit();
    }

    public void checkLogin(){

        if(!this.isLoggedIn()){
            Intent i = new Intent(mContext, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(i);
        }
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public HashMap<String, String> getUserCredentials(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PICTURE, pref.getString(KEY_PICTURE, null));
        user.put(KEY_PROFILE_ID, pref.getString(KEY_PROFILE_ID, null));
        user.put(KEY_FIRST_NAME, pref.getString(KEY_FIRST_NAME, null));
        user.put(KEY_LAST_NAME, pref.getString(KEY_LAST_NAME, null));
        user.put(KEY_VOLUNTEER_ID, pref.getString(KEY_VOLUNTEER_ID, null));
        user.put(KEY_API_TOKEN, pref.getString(KEY_API_TOKEN, null));
        user.put(KEY_BIRTHDAY, pref.getString(KEY_BIRTHDAY, null));


        return user;
    }
    public HashMap<String, String> getUserCredentialsEthelon(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));
        user.put(KEY_VOLUNTEER_ID, pref.getString(KEY_VOLUNTEER_ID, null));
        user.put(KEY_API_TOKEN, pref.getString(KEY_API_TOKEN, null));

        return user;
    }


    public Set<String> getUserSkillSet(){


       Set<String> userSkills = pref.getStringSet(KEY_SKILL_SET, null);
//        userSkills.put(KEY_SKILL_SET, String.valueOf(pref.getStringSet(KEY_SKILL_SET, null)));


//        Log.e("KIRSTEN", userSkills.toString());

        return userSkills;
    }


    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(mContext, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }
}
