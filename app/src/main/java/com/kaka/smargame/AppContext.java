package com.kaka.smargame;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.kaka.smargame.base.BaseApplication;
import com.kaka.smargame.model.User;
import com.kaka.smargame.util.StringUtil;
import com.kaka.smargame.util.cache.DataCache;
import com.kaka.smargame.widget.dialog.CommonDialog;
import com.kaka.smargame.widget.dialog.DialogHelper;


import java.util.Iterator;
import java.util.List;
import java.util.UUID;


public class AppContext extends BaseApplication {
    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;
    private boolean mIsDownload;

    private static AppContext instance;
    public static Context applicationContext;
    private DataCache mDataCache;
    public static String TAG="TAG";
   // public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();

    public static SharedPreferences  share;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = this;
        mDataCache = DataCache.get(this);
        initImageLoader(this);
        share = getSharedPreferences(Config.SHARE_LOGIN_TAG, 0);
        /*设置ImageLoader默认参数
        创建默认的ImageLoader配置参数
        */
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
//                .createDefault(this);
//        //Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(configuration);

        initImageLoader(applicationContext);


//        int pid = android.os.Process.myPid();
//        String processAppName = getAppName(pid);
//    // 如果app启用了远程的service，此application:onCreate会被调用2次
//    // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
//    // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
//
//        if (processAppName == null ||!processAppName.equalsIgnoreCase("com.easemob.chatuidemo")) {
//            Log.e("TAG", "enter the service process!");
//            //"com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名
//
//            // 则此application::onCreate 是被service 调用的，直接返回
//            return;
//        }
//
//        EMChat.getInstance().init(applicationContext);
//
//    /**
//     * debugMode == true 时为打开，sdk 会在log里输入调试信息
//     * @param debugMode
//     * 在做代码混淆的时候需要设置成false
//     */
//        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，如果未被关闭，则会出现程序无法运行问题
      //  hxSDKHelper.onInit(applicationContext);
        //自动登录
      //  EMChat.getInstance().setAutoLogin(true);
    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }


    public static void initImageLoader(Context context) {
        DisplayImageOptions displayOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(R.drawable.page_icon_empty)
                .showImageOnFail(R.drawable.page_icon_empty)
                .showImageOnLoading(R.drawable.page_icon_empty)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(200)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(displayOptions).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public int getNetworkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtil.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }


    public static void clearLoginInfo() {
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取App唯一标识
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = mDataCache.getAsString(CONF_APP_UNIQUEID);
        if (StringUtil.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            mDataCache.put(CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    public void showDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveClickListener, DialogInterface.OnClickListener negativeClickListener) {
        CommonDialog dialog = DialogHelper.getPinterestDialogCancelable(context);
        if (!title.equals("")) {
            dialog.setTitle(title);
        }
        dialog.setMessage(message);
        dialog.setPositiveButton(R.string.ok, positiveClickListener);
        dialog.setNegativeButton(R.string.cancel, negativeClickListener);
        dialog.show();
    }

    public static void setProperty(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static String getProperty(String key) {
        return getPreferences().getString(key, null);
    }

    public static void removeProperty(String... keys) {
        for (String key : keys) {
            SharedPreferences.Editor editor = getPreferences().edit();
            editor.putString(key, null);
            apply(editor);
        }
    }

    public User getUser() {
        return null;
    }

    public int getUserId() {
        return 0;
    }

    public void setUser(User user) {

    }
    public boolean isAliasd(){
        String isAlias = getProperty(CacheKey.ALIASD);
        return isAlias != null && isAlias.equals(CacheKey.ALIASD_YES);
    }

    public void setAliasd(){
        setProperty(CacheKey.ALIASD, CacheKey.ALIASD_YES);
    }

    public void cleanTag(){
        removeProperty(CacheKey.TAGD);
    }

    public boolean isTagd(){
        String isAlias = getProperty(CacheKey.TAGD);
        return isAlias != null && isAlias.equals(CacheKey.TAGD_YES);
    }

    public void setTagd(){
        setProperty(CacheKey.TAGD, CacheKey.TAGD_YES);
    }

    public void cleanAliasd(){
        removeProperty(CacheKey.ALIASD);
    }

    public static void apply(SharedPreferences.Editor editor) {
        editor.apply();
    }

    public static AppContext instance() {
        return instance;
    }

    public void setDownload(boolean isDownload) {
        this.mIsDownload = isDownload;
    }

    public boolean isDownload() {
        return mIsDownload;
    }


    public static AppContext getInstance() {
        return instance;
    }


    /**
     * 获取当前登陆用户名
     *
     * @return
     */
//    public String getUserName() {
//        return hxSDKHelper.getHXId();
//    }

    /**
     * 获取密码
     *
     * @return
     */
//    public String getPassword() {
//        return hxSDKHelper.getPassword();
//    }

    /**
     * 设置用户名
     *
     * @param username
     */
//    public void setUserName(String username) {
//        hxSDKHelper.setHXId(username);
//    }

    /**
     * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
     * 内部的自动登录需要的密码，已经加密存储了
     *
     * @param pwd
     */
//    public void setPassword(String pwd) {
//        hxSDKHelper.setPassword(pwd);
//    }

    /**
     * 退出登录,清空数据
     */
//    public void logout(final boolean isGCM,final EMCallBack emCallBack) {
//        // 先调用sdk logout，在清理app中自己的数据
//        hxSDKHelper.logout(isGCM, emCallBack);
//    }

    public static  void toLog(String ss){
        Log.i(TAG, "-------->" + ss);
    }

    //获取登录的用户名
    public static String getShareUserName() {

        return getPreferences().getString(Config.SHARE_USERNAME,"");
    }

    //获取登录的密码
    public static String getShareUserPwd() {

        return getPreferences().getString(Config.SHARE_USERPWD,"");
    }

    //获取登录的sessionid
    public static String getShareUserSessinid() {

        return getPreferences().getString(Config.SHARE_USERSESSIONID,"");
    }
}
