package com.hnrheducation.teachermaintain.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.hnrheducation.teachermaintain.utils.GlideUtils;
import com.hnrheducation.teachermaintain.utils.ToastUtils;

/**
 * Created by My on 2017/10/10.
 */

public class BaseFragment extends Fragment {


    // 第一次按下返回键的时间
    private static long m_FirstTime;


    /**
     * 判断本地网络是否连接
     *
     * @param paramContext
     * @return
     */
    public static boolean isConnect(Context paramContext) {
        ConnectivityManager localConnectivityManager = (ConnectivityManager) TheApplication.instance
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (localConnectivityManager != null) {
            NetworkInfo localNetworkInfo = localConnectivityManager
                    .getActiveNetworkInfo();
            if ((localNetworkInfo != null)
                    && (localNetworkInfo.isConnected())
                    && (localNetworkInfo.getState() == NetworkInfo.State.CONNECTED))
                return true;
        }
        return false;
    }

    public void connectShowMsg(Context ctx) {
        if (!isConnect(ctx)) {
            showPopwindow();
        }
    }
    /**
     * 显示popupWindow
     */
    private void showPopwindow() {
        ToastUtils.showToast(getActivity().getBaseContext(),"联网失败，请保持网络畅通");
    }


    public void createToast(final String paramString) {
        ToastUtils.showToast(getActivity().getBaseContext(),paramString);
    }

    /**
     * 显示图片
     *
     * @param view
     * @param strHttpUrl
     */
    public void display(ImageView view, String strHttpUrl) {
        GlideUtils.loadImageView(getActivity(),strHttpUrl,view);
    }

    /**
     * @param view
     * @param strHttpUrl
     * @param loadingResId 加载中与加载失败为同一张图片
     */
    public void display(ImageView view, String strHttpUrl, int loadingResId) {
        GlideUtils.loadImageViewLoding(getActivity(),strHttpUrl,view,loadingResId,loadingResId);
    }

    /**

     */
    public void display(ImageView view, String strHttpUrl,RequestListener<String, GlideDrawable> requstlistener) {
        GlideUtils.loadImageViewListener(getActivity(),strHttpUrl, view, requstlistener);
    }


}
