package com.jaime.examendeportes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jaime.examendeportes.model.Sport;

import java.util.ArrayList;

/**
 * Created by jaime on 11/12/16.
 */

public class Preferences {
    private SharedPreferences preferences;
    private Context context;


    public Preferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }


    public SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void savePreferences() {
        ArrayList<Sport> sports = Repository.getInstance();
        SharedPreferences.Editor editor = preferences.edit();

        for (int i = 0; i < sports.size(); i++)
            editor.putBoolean(sports.get(i).getName(), sports.get(i).isLike()).commit();
    }

    public void LoadPreferences() {
        ArrayList<Sport> sports = Repository.getInstance();

        for (int i = 0; i < sports.size(); i++)
            sports.get(i).setLike(preferences.getBoolean(sports.get(i).getName(), false));
    }
}
