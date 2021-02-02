package com.example.androidfundamentals.week9;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class ApplicationData {

    private static final String APP_KEY = "android_course_key";

    private static void setValueInSharedPref(@NonNull Context context, String key, String value) {
        SharedPreferences sp = getSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @NonNull
    private static String getValueInSharedPref(@NonNull Context context, String key, @NonNull String defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);

        return sp.getString(key, defaultValue);
    }

    private static void removeValueInSharedPref(@NonNull Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    private static void deleteAllValuesInSharedPref(@NonNull Context context) {
        SharedPreferences sp = getSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences (Context context) {
        return context.getApplicationContext().getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);
    }
}
