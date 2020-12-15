package shotgun.my.sweetutil.http.impl;

import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.http.BridgeInterceptor;
import org.apache.commons.lang3.StringUtils;
import shotgun.my.sweetutil.http.HttpClientUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpClientUtilImpl implements HttpClientUtil {

    @Override
    public String execute(String method, String url, Map<String, String> headers,
            String body) throws IOException {
        if (StringUtils.isEmpty(method)) {
            throw new IllegalArgumentException("method不能为空!");
        }
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url不能为空!");
        }

        method = method.toUpperCase();

        RequestBody requestBody;

        if (GET.equals(method)) {

            if (headers == null) {
                headers = Collections.emptyMap();
            }
            //GET、HEAD请求，requestBody必须为null
            requestBody = null;

        } else {
            //post和其他处理

            if (headers == null) {
                //设置默认Content-Type为application/x-www-form-urlencoded
                headers = new HashMap<>(1);
                headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_FORM);
            } else if (!headers.containsKey(HEADER_KEY_CONTENT_TYPE)) {
                //没有Content-Type，设置默认Content-Type为application/x-www-form-urlencoded
                headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_FORM);
            }
            //除GET、HEAD请求外，requestBody不能为null
            requestBody = StringUtils.isEmpty(body) ? Util.EMPTY_REQUEST : RequestBody
                    .create(MediaType.get(headers.get(HEADER_KEY_CONTENT_TYPE)), body);
        }
        //headers和requestBody处理完毕
        //进行公共处理

        //去除Accept-Encoding、Range头部信息，使用okhttp3处理gzip，否则无法自动解析gzip压缩内容出现乱码
        headers.remove(HEADER_KEY_ACCEPT_ENCODING);
        headers.remove(HEADER_KEY_RANGE);

        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BridgeInterceptor(CookieJar.NO_COOKIES))
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();


        // 构造Request
        Request request = new Request.Builder().url(url)
                .headers(headers.isEmpty() ? new Headers.Builder().build() : Headers.of(headers))
                .method(method, requestBody).build();

        Call call = okHttpClient.newCall(request);

        //发起请求
        Response response = call.execute();

        ResponseBody responseBody = response.body();

        return responseBody == null ? "" : responseBody.string();
//            if (response.isSuccessful()) {
//            }
    }

}
