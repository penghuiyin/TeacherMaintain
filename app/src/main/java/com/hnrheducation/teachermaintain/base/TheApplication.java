package com.hnrheducation.teachermaintain.base;

import android.app.Application;

import com.hnrheducation.teachermaintain.common.netcommon.EasyHttp;
import com.hnrheducation.teachermaintain.common.netcommon.cache.converter.SerializableDiskConverter;
import com.hnrheducation.teachermaintain.common.netcommon.cache.model.CacheMode;
import com.hnrheducation.teachermaintain.common.netcommon.model.HttpHeaders;
import com.hnrheducation.teachermaintain.common.netcommon.model.HttpParams;
import com.hnrheducation.teachermaintain.cons.NetUrl;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * 功能:程序的全局数据
 * <p/>
 * zjn
 * <p/>
 * 2015-12-28
 */
public class TheApplication extends Application {

    public static TheApplication instance = null;
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;

        inintNetWork();
        ZXingLibrary.initDisplayOpinion(this);

    }
    /* 初始化网络加载的框架*/
    private void inintNetWork() {
        EasyHttp.init(instance);
        HttpHeaders headers = new HttpHeaders();
        //全局设置请求参数
        HttpParams params = new HttpParams();
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        EasyHttp.getInstance()
                //可以全局统一设置全局URL
                .setBaseUrl(NetUrl.BASEURL)
                .debug("EasyHttp", false)
                //如果使用默认的60秒,以下三行也不需要设置
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 100)
                .setConnectTimeout(60 * 100)
                .setRetryCount(3)
                .setRetryDelay(500)
                .setRetryIncreaseDelay(500)
                .setCacheMode(CacheMode.NO_CACHE)
                //可以全局统一设置缓存时间,默认永不过期
                .setCacheTime(-1)//-1表示永久缓存,单位:秒 ，Okhttp和自定义RxCache缓存都起作用
                //全局设置自定义缓存保存转换器，主要针对自定义RxCache缓存
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                //全局设置自定义缓存大小，默认50M
                .setCacheMaxSize(100 * 1024 * 1024)//设置缓存大小为100M
                //设置缓存版本，如果缓存有变化，修改版本后，缓存就不会被加载。特别是用于版本重大升级时缓存不能使用的情况
                .setCacheVersion(1)//缓存版本为1
                .setCertificates()
                .addCommonHeaders(headers)//设置全局公共头
                .addCommonParams(params);//设置全局公共参数
    }
}
