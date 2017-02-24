package com.kaka.smargame.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.kaka.smargame.AppContext;
import com.kaka.smargame.R;
import com.kaka.smargame.model.SerializableMap;
import com.kaka.smargame.model.Version;
import com.kaka.smargame.service.DownloadService;
import com.kaka.smargame.util.download.DownloadConstant;
import com.kaka.smargame.util.download.IDownloadCallBack;
import com.kaka.smargame.widget.dialog.CommonDialog;
import com.kaka.smargame.widget.dialog.DialogHelper;
import com.kaka.smargame.widget.dialog.ProgressDialog;


import java.io.File;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VersionUpdateUtil {
    private Version mVersion;
    private Context mContext;
    private CommonDialog mProgressDialog;
    private ViewHolder mDownloadHolder;
    private boolean mIsBinded = false;
    private boolean mIsDestroy = true;
    private DownloadService.DownloadBinder mBinder;

    public VersionUpdateUtil(Context context) {
        this.mContext = context;
    }

    public void check() {
//        HttpClient.instance().version(new HttpCallBack() {
//            @Override
//            public void onSuccess(ResponseBean responseBean) {
//                if (responseBean.isFailure()) {
//                    return;
//                }
//
//                mVersion = new Gson().fromJson(responseBean.toString(), Version.class);
//                if(mVersion.isUpadte(Double.valueOf(DeviceUtil.getVersionName()))){
//                    CommonDialog dialog = DialogHelper.getPinterestDialogCancelable(mContext);
//                    dialog.setTitle(mContext.getString(R.string.checked_new_version, mVersion.getVersionNum() + ""));
//                    dialog.setMessage(mVersion.getMsg());
//                    dialog.setPositiveButton(R.string.ok, new OnUpdateClickListener());
//                    if(!mVersion.isForcedUpdate()){
//                        dialog.setCanceledOnTouchOutside(false);
//                        dialog.setCancelable(false);
//                        dialog.setNegativeButton(R.string.cancel, null);
//                    }
//                    dialog.show();
//                }
//            }
//        });
    }

    private class OnUpdateClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if(mContext == null){
                return;
            }
            startDownloadService(mVersion);
        }
    }

    private class OnCancelDownloadClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mBinder.cancel();
            mBinder.cancelNotification();
            dialog.dismiss();
        }
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            setBinded(false);
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            mBinder = (DownloadService.DownloadBinder) service;
            setBinded(true);
            mBinder.addCallback(callback);
            mBinder.start();
        }
    };

    public void onResume() {
        startDownloadService(mVersion);
    }

    public void onStop(){
        setDestroy(false);
    }

    public void startDownloadService(Version version){
        AppContext.instance().setDownload(true);
        if (isDestroy() && AppContext.instance().isDownload()) {
            Intent it = new Intent(mContext, DownloadService.class);
            it.putExtra("version", version);
            mContext.startService(it);
            mContext.bindService(it, conn, Context.BIND_AUTO_CREATE);
        }
    }

    public void onNewIntent(){
        startDownloadService(mVersion);
    }

    public void onDestroy(){
        if (isBinded()) {
            mContext.unbindService(conn);
        }
        if (mBinder != null && mBinder.isCanceled()) {
            Intent it = new Intent(mContext, DownloadService.class);
            mContext.stopService(it);
        }
    }

    private static final String KEY_MSG_DATA = "data";

    private static final int MSG_CHECKEXISTS = 1;
    private static final int MSG_FILEEXISTS = 2;
    private static final int MSG_START = 3;
    private static final int MSG_DOWNLOADING = 4;
    private static final int MSG_FAIL = 5;
    private static final int MSG_SUCCESS = 6;

    private IDownloadCallBack callback = new IDownloadCallBack() {
        @Override
        public void onResult(String key, Object value) {
            Message msg = mDownloadHandler.obtainMessage();
            Bundle bundle = new Bundle();
            switch (key){
                case DownloadConstant.STATUS_CHECKEXISTS:
                    msg.what = MSG_CHECKEXISTS;
                    break;
                case DownloadConstant.STATUS_FILEEXISTS:
                    msg.what = MSG_FILEEXISTS;
                    bundle.putString(KEY_MSG_DATA, (String) value);
                    break;
                case DownloadConstant.STATUS_START:
                    msg.what = MSG_START;
                    SerializableMap map = new SerializableMap((Map<String, Object>) value);
                    bundle.putSerializable(KEY_MSG_DATA, map);
                    break;
                case DownloadConstant.STATUS_DOWNLOADING:
                    msg.what = MSG_DOWNLOADING;
                    bundle.putInt(KEY_MSG_DATA, (Integer) value);
                    break;
                case DownloadConstant.STATUS_FAIL:
                    msg.what = MSG_FAIL;
                    break;
                case DownloadConstant.STATUS_SUCCESS:
                    msg.what = MSG_SUCCESS;
                    break;
            }
            msg.setData(bundle);
            mDownloadHandler.sendMessage(msg);
        }
    };

    private Handler mDownloadHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ProgressDialog.disMiss();
            switch (msg.what){
                case MSG_CHECKEXISTS:
                    ProgressDialog.show(mContext, R.string.check_file_exists);
                    break;
                case MSG_FILEEXISTS:
                    CommonDialog confirmDialog = DialogHelper.getPinterestDialogCancelable(mContext);
                    confirmDialog.setTitle(R.string.app_name);
                    confirmDialog.setMessage(R.string.file_exists_confirm_content);
                    final String filePath = msg.getData().getString(KEY_MSG_DATA);
                    confirmDialog.setPositiveButton(R.string.file_exists_confirm_install, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DeviceUtil.installAPK(mContext, new File(filePath));
                            dialog.dismiss();
                        }
                    });
                    confirmDialog.setNegativeButton(R.string.file_exists_confirm_download, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startDownloadService(mVersion);
                            dialog.dismiss();
                        }
                    });
                    confirmDialog.show();
                    break;
                case MSG_START:
                    mProgressDialog = DialogHelper.getPinterestDialog(mContext);
                    mProgressDialog.setTitle(R.string.download_new_version);
                    View progressView = LayoutInflater.from(mContext).inflate(R.layout.view_download, null);
                    mDownloadHolder = new ViewHolder(progressView);
                    mProgressDialog.setContent(progressView);
                    mProgressDialog.setPositiveButton(R.string.cancel, new OnCancelDownloadClickListener());
                    mProgressDialog.show();
                    SerializableMap map = (SerializableMap) msg.getData().getSerializable(KEY_MSG_DATA);
                    mDownloadHolder.downloadTip.setText(mContext.getString(R.string.tip_download_file, map.getMap().get("filePath")));
                    mDownloadHolder.fileSize.setText(mContext.getString(R.string.download_file_size, map.getMap().get("fileSize")));
                    break;
                case MSG_DOWNLOADING:
                    int progress = msg.getData().getInt(KEY_MSG_DATA);
                    mDownloadHolder.progressBar.setProgress(progress);
                    break;
                case MSG_FAIL:
                    mBinder.cancel();
                    mBinder.cancelNotification();
                    AppContext.toastFail(R.string.status_download_fail);
                    break;
                case MSG_SUCCESS:
                    if(mProgressDialog != null){
                        mProgressDialog.dismiss();
                    }
                    break;
            }

        }
    };

    static class ViewHolder {
        @InjectView(R.id.fileSize)
        TextView fileSize;
        @InjectView(R.id.progressBar)
        NumberProgressBar progressBar;
        @InjectView(R.id.downloadTip)
        TextView downloadTip;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public boolean isBinded() {
        return mIsBinded;
    }

    public void setBinded(boolean isBinded) {
        this.mIsBinded = isBinded;
    }

    public boolean isDestroy() {
        return mIsDestroy;
    }

    public void setDestroy(boolean isDestroy) {
        this.mIsDestroy = isDestroy;
    }
}
