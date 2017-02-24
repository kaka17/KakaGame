package com.kaka.smargame;

import android.os.Environment;

import com.kaka.smargame.util.DateUtil;


public class Config {
    public static final String APP = "smargame";
    //私人
    public static final String APPID_WEIXIN = "wx102b99845171327e";
    public static final String APPID_QZONE = "1104805211";
    public static final String SECRET_WEIXIN = "21d2eedc1148497b456555d2f6fd128d";
    public static final String SECRET_QZONE = "yaHeFXaEc87GflNY";
    public static final String DESCRIPTOR = "com.umeng.share";
    public final static int PAGESIZE = 20;
    public final static int DEFAULT_LISTDIALOG_ID = 1000;

    public final static String BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + APP;
    public final static String UPDATE_APK_PAHT = BASE_DIR + "/" + "update";
    public final static String SELECT_IMAGE_SAVE_PAHT = BASE_DIR + "/" + "camera";
    public final static String IMAGE_SAVE_PAHT = BASE_DIR + "/" + "images";
    public final static String IMAGE = "/" + APP + "/" + "images/";
    public final static String CACHE_IMAGE_SAVE_PAHT = IMAGE_SAVE_PAHT + "/" + "cache/";
    public final static String LOG_PAHT = BASE_DIR + "/" + "log";

    public final static String HOTEL_TYPE = "HOTEL_TYPE";
    public static final String KEY_HOTEL_URL = "HOTEL_URL";
    public static final String KEY_HOTEL_ID = "hotelid";
    public static final String KEY_HOTEL_CAPTION ="hotelcaption" ;


    public static final String RANKTYPE_PRICE = "price";
    public static final String RANKTYPE_DISTANCE = "distance";
    public static final String RANKORDER_ASC = "asc";
    public static final String RANKORDER_DESC = "desc";
    public static final String KEY_HOTEL_ROOMID = "roomid";
    public static final String KEY_CHECKIN_DATE = "BookingDateStart";
    public static final String KEY_CHECKOUT_DATE= "BookingDateEnd";
    public static final String KEY_HOTEL_ROOMCAPTION ="roomcaption" ;
    public static final String KEY_HOTEL_ROOMPRICE ="roomprice" ;
    public static final String KEY_ARRIVE_TIME = "arrive_time";
    public static final String KEY_BOOKING_BILLNUM ="booking_billnum" ;
    public static final String KEY_HOTEL_ROOMCOUNT ="hotel_roomcount" ;
    public static final String KEY_HOTEL_ROOMTOTALPRICE ="hotel_roomtotalprice" ;

    public static String SHARE_LOGIN_TAG = "MAP_SHARE_LOGIN_TAG";
    public static String SHARE_USERNAME = "SHARE_USERNAME";
    public static String SHARE_USERPWD = "SHARE_PASSWORD";
    public static String SHARE_USERSESSIONID = "SHARE_SESSIONID";




    public static String getLogName() {
        long timestamp = System.currentTimeMillis();
        String time = DateUtil.getCurrentDateStr(DateUtil.PATTERN);
        return "error-" + time + "-" + timestamp + ".log";
    }





}
