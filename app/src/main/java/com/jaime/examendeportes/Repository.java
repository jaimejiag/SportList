package com.jaime.examendeportes;

import com.jaime.examendeportes.model.Sport;

import java.util.ArrayList;

/**
 * Created by jaime on 8/12/16.
 */

public class Repository extends ArrayList<Sport> {
    private static Repository instance;


    public static Repository getInstance(){
        if(instance == null)
            instance = new Repository();

        return instance;
    }

    public Repository() {
        add(new Sport(R.drawable.icon_baseball, "Baseball", false));
        add(new Sport(R.drawable.icon_basketball, "Baloncesto", false));
        add(new Sport(R.drawable.icon_boxing, "Boxeo", false));
        add(new Sport(R.drawable.icon_golf, "Golf", false));
        add(new Sport(R.drawable.icon_motor, "Motor", false));
        add(new Sport(R.drawable.icon_soccer, "Fútbol", false));
        add(new Sport(R.drawable.icon_tennis, "Tenis", false));
        add(new Sport(R.drawable.icon_volleyball, "Volleyball", false));
    }
}
