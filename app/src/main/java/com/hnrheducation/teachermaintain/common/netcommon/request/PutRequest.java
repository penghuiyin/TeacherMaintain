/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hnrheducation.teachermaintain.common.netcommon.request;

import com.google.gson.reflect.TypeToken;
import com.hnrheducation.teachermaintain.common.netcommon.EasyHttp;
import com.hnrheducation.teachermaintain.common.netcommon.api.ApiService;
import com.hnrheducation.teachermaintain.common.netcommon.cache.RxCache;
import com.hnrheducation.teachermaintain.common.netcommon.cache.converter.IDiskConverter;
import com.hnrheducation.teachermaintain.common.netcommon.cache.model.CacheMode;
import com.hnrheducation.teachermaintain.common.netcommon.cache.model.CacheResult;
import com.hnrheducation.teachermaintain.common.netcommon.callback.CallBack;
import com.hnrheducation.teachermaintain.common.netcommon.callback.CallBackProxy;
import com.hnrheducation.teachermaintain.common.netcommon.callback.CallClazzProxy;
import com.hnrheducation.teachermaintain.common.netcommon.func.ApiResultFunc;
import com.hnrheducation.teachermaintain.common.netcommon.func.CacheResultFunc;
import com.hnrheducation.teachermaintain.common.netcommon.func.HandleFuc;
import com.hnrheducation.teachermaintain.common.netcommon.func.RetryExceptionFunc;
import com.hnrheducation.teachermaintain.common.netcommon.https.HttpsUtils;
import com.hnrheducation.teachermaintain.common.netcommon.interceptor.BaseDynamicInterceptor;
import com.hnrheducation.teachermaintain.common.netcommon.interceptor.CacheInterceptor;
import com.hnrheducation.teachermaintain.common.netcommon.interceptor.CacheInterceptorOffline;
import com.hnrheducation.teachermaintain.common.netcommon.interceptor.HeadersInterceptor;
import com.hnrheducation.teachermaintain.common.netcommon.interceptor.NoCacheInterceptor;
import com.hnrheducation.teachermaintain.common.netcommon.model.ApiResult;
import com.hnrheducation.teachermaintain.common.netcommon.model.HttpHeaders;
import com.hnrheducation.teachermaintain.common.netcommon.model.HttpParams;
import com.hnrheducation.teachermaintain.common.netcommon.subsciber.CallBackSubsciber;
import com.hnrheducation.teachermaintain.common.netcommon.transformer.HandleErrTransformer;
import com.hnrheducation.teachermaintain.common.netcommon.utils.HttpLog;
import com.hnrheducation.teachermaintain.common.netcommon.utils.RxUtil;
import com.hnrheducation.teachermaintain.common.netcommon.utils.Utils;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * <p>描述：Put请求</p>
 * 作者： zhouyou<br>
 * 日期： 2017/5/22 16:30 <br>
 * 版本： v1.0<br>
 */
public class PutRequest extends BaseBodyRequest<PutRequest> {
    public PutRequest(String url) {
        super(url);
    }

    public <T> Observable<T> execute(Class<T> clazz) {
        return execute(new CallClazzProxy<ApiResult<T>, T>(clazz) {
        });
    }

    public <T> Observable<T> execute(Type type) {
        return execute(new CallClazzProxy<ApiResult<T>, T>(type) {
        });
    }

    public <T> Observable<T> execute(CallClazzProxy<? extends ApiResult<T>, T> proxy) {
        return build().generateRequest()
                .map(new ApiResultFunc(proxy.getType()))
                .compose(isSyncRequest ? RxUtil._main() : RxUtil._io_main())
                .compose(rxCache.transformer(cacheMode, proxy.getCallType()))
                .retryWhen(new RetryExceptionFunc(retryCount, retryDelay, retryIncreaseDelay))
                .compose(new ObservableTransformer() {
                    @Override
                    public ObservableSource apply(@NonNull Observable upstream) {
                        return upstream.map(new CacheResultFunc<T>());
                    }
                });
    }

    public <T> Disposable execute(CallBack<T> callBack) {
        return execute(new CallBackProxy<ApiResult<T>, T>(callBack) {
        });
    }

    public <T> Disposable execute(CallBackProxy<? extends ApiResult<T>, T> proxy) {
        Observable<CacheResult<T>> observable = build().toObservable(apiManager.put(url, params.urlParamsMap), proxy);
        if (CacheResult.class != proxy.getCallBack().getRawType()) {
            return observable.compose(new ObservableTransformer<CacheResult<T>, T>() {
                @Override
                public ObservableSource<T> apply(@NonNull Observable<CacheResult<T>> upstream) {
                    return upstream.map(new CacheResultFunc<T>());
                }
            }).subscribeWith(new CallBackSubsciber<T>(context, proxy.getCallBack()));
        } else {
            return observable.subscribeWith(new CallBackSubsciber<CacheResult<T>>(context, proxy.getCallBack()));
        }
    }

    private <T> Observable<CacheResult<T>> toObservable(Observable observable, CallBackProxy<? extends ApiResult<T>, T> proxy) {
        return observable.map(new ApiResultFunc(proxy != null ? proxy.getType() : new TypeToken<ResponseBody>() {
        }.getType()))
                .compose(isSyncRequest ? RxUtil._main() : RxUtil._io_main())
                .compose(rxCache.transformer(cacheMode, proxy.getCallBack().getType()))
                .retryWhen(new RetryExceptionFunc(retryCount, retryDelay, retryIncreaseDelay));
    }

    @Override
    protected Observable<ResponseBody> generateRequest() {
        if (this.object != null) {//自定义的请求object
            return apiManager.putBody(url, object);
        } else {
            return apiManager.put(url, params.urlParamsMap);
        }
    }
}
