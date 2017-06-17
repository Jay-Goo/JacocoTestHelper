package jaygoo.jacocohelper;

import android.util.Log;

/**
 * ================================================
 * 作    者：JayGoo
 * 版    本：
 * 创建日期：2017/6/16
 * 描    述:
 * ================================================
 */
class LogUtils {

    private final static String TAG = "JayGoo";
    private static boolean isDebug = false;

    public static void setDebug(boolean debug){
        isDebug = debug;
    }

    public static void d(String msg){
        if (isDebug){
            Log.d(TAG,msg);
        }
    }
}
