package com.example.user.xprocure;

import android.os.Build;

/**
 * Created by user on 09/02/2017.
 */

public class Util {

    public static  boolean isLollipolOrGreater() {
        return Build.VERSION.SDK_INT >= 21 ? true : false;


    }

    public static boolean isJellyBeanOrGreater() {
return Build.VERSION.SDK_INT >=16 ?true:false;

    }


}
