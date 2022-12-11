package com.hoprik.screen;

public class guiFucn {
    public static boolean switchBolen(boolean getChange){
        if (getChange){
            getChange = false;
        }
        else {
            getChange = true;
        }
        return getChange;
    }
}
