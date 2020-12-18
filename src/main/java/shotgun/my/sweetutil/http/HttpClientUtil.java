package shotgun.my.sweetutil.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HttpClientUtil {
    String UTF8 = "UTF-8";

    String GET = "GET";
    String POST = "POST";

    /**
     * 默认连接超时时间(秒)
     */
    int CONNECT_TIMEOUT = 600;

    /**
     * 默认读取超时时间(秒)
     */
    int READ_TIMEOUT = 600;

    /**
     * 头部信息
     */
    String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    String HEADER_KEY_ACCEPT_ENCODING = "Accept-Encoding";
    String HEADER_KEY_RANGE = "Range";

    /**
     * 头部信息默认值
     */
    String HEADER_VAL_CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    String HEADER_VAL_CONTENT_TYPE_JSON = "application/json";

    default String execute(String method, String url) throws IOException {
        return execute(method, url, (Map<String, String>) null, null);
    }

    /**
     * 默认Content-Type:application/x-www-form-urlencoded
     * 如遇到“&#xxx;”字符编码，可使用StringEscapeUtils.unescapeHtml进行解码
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(非必填)
     * @param body    请求体(非必填)，get请求不会传递body
     * @return 返回结果
     **/
    default String execute(String method, String url, String headers, String body) throws IOException {
        Map<String, String> headersMap;

        if (StringUtils.isEmpty(headers)) {
            headersMap = null;
        } else {
            headersMap = new HashMap<>();

            String[] ps = headers.split("\n");
            for (String p : ps) {
                String pTrim = p.trim();
                if (StringUtils.isNotEmpty(pTrim)) {
                    int psi = pTrim.indexOf(":");
                    if (psi > 0) {
                        //只筛选出是请求头的头部信息，带“:”符号的请求头
                        String headerValue = pTrim.substring(psi + 1);
                        if (headerValue.indexOf("//") == 0) {
                            //防止SoapUI、fiddler复制时带上请求地，如POST http://oa.ytport.com/sys/webservice HTTP/1.1
                            continue;
                        }
                        headersMap.put(pTrim.substring(0, psi).trim(), headerValue.trim());
                    }
                }
            }
        }

        return execute(method, url, headersMap, body);
    }


    /**
     * 所有请求统一入口
     * <p>
     * 默认Content-Type:application/x-www-form-urlencoded
     * 如遇到“&#xxx;”字符编码，可使用StringEscapeUtils.unescapeHtml进行解码
     *
     * @param method  请求方法，get、post、put等
     * @param url     url
     * @param headers 多个请求头(非必填)
     * @param body    请求体(非必填)，get请求不会传递body
     * @return 返回结果
     **/
    String execute(String method, String url, Map<String, String> headers, String body) throws IOException;


    default String httpGet(String url) throws IOException {
        return execute(GET, url);
    }

    default String httpGet(String url, Map<String, String> headers,
            Map<String, String> paramsMap) throws IOException, URISyntaxException {
        return execute(GET, buildGetUrl(url, paramsMap), headers, null);
    }

    default String httpGet(String url, Map<String, String> paramsMap) throws IOException, URISyntaxException {
        return httpGet(url, null, paramsMap);
    }

    default String httpPostForm(String url, Map<String, String> headers,
            Map<String, String> paramsMap) throws IOException {
        String bodyStr;
        if (paramsMap == null || paramsMap.isEmpty()) {
            bodyStr = null;
        } else {
            //构建form表单传递字符串
            bodyStr = buildFormString(paramsMap);
        }

        return httpPostForm(url, headers, bodyStr);
    }

    default String httpPostForm(String url, Map<String, String> headers, String body) throws IOException {

        if (headers == null) {
            //设置Content-Type为application/x-www-form-urlencoded
            headers = new HashMap<>(1);
            headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_FORM);
        } else if (!HEADER_VAL_CONTENT_TYPE_FORM.equals(headers.get(HEADER_KEY_CONTENT_TYPE))) {
            //手动设置Content-Type，进行覆盖为application/x-www-form-urlencoded
            headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_FORM);
        }
        return execute(POST, url, headers, body);
    }

    default String httpPostJson(String url, Map<String, String> headers, String bodyJson) throws IOException {
        if (headers == null) {
            //设置Content-Type为application/json
            headers = new HashMap<>(1);
            headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_JSON);
        } else if (!HEADER_VAL_CONTENT_TYPE_JSON.equals(headers.get(HEADER_KEY_CONTENT_TYPE))) {
            //手动设置Content-Type，进行覆盖为application/json
            headers.put(HEADER_KEY_CONTENT_TYPE, HEADER_VAL_CONTENT_TYPE_JSON);
        }

        return execute(POST, url, headers, bodyJson);
    }


//    public static String buildFormString3(Map<String, String> paramsMap) throws
//    UnsupportedEncodingException {
//
//        StringBuilder sb = new StringBuilder();
//        Set<Map.Entry<String, String>> entries = paramsMap.entrySet();
//        boolean isFirst = true;
//        for (Map.Entry<String, String> en : entries) {
//            if (isFirst) {
//                isFirst = false;
//            } else {
//                sb.append("&");
//            }
//            sb.append(URLEncoder.encode(en.getKey(), UTF8)).append("=")
//                    .append(URLEncoder.encode(en.getValue(), UTF8));
//        }
//        return sb.toString();
//    }


    /**
     * 构建get url
     *
     * @param url       url
     * @param paramsMap 参数
     * @return url结果
     **/
    public static String buildGetUrl(String url, Map<String, String> paramsMap) throws URISyntaxException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("url不能为空!");
        }

        if (paramsMap == null || paramsMap.isEmpty()) {
            return url;
        }
        URIBuilder builder = new URIBuilder(url);

        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            builder.setParameter(entry.getKey(), entry.getValue());
        }
        return builder.toString();
    }

    /**
     * 构建form表单传递字符串
     * 例如：aa=%E5%BC%A0%E4%B8%89&bb=%E6%9D%8E%E5%9B%9B&cc=%E7%8E%8B%E4%BA%94
     *
     * @param paramsMap 表单参数
     * @return 表单传递字符串
     **/
    public static String buildFormString(Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.isEmpty()) {
            return null;
        }
        URIBuilder builder = new URIBuilder();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            builder.setParameter(entry.getKey(), entry.getValue());
        }
        //首个字符串是“?”，过滤掉“?”
        return builder.toString().substring(1);
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "张三");
        map.put("bb", "李四");
        map.put("cc", "王五");

//        System.out.println(buildFormString(map));
//
//        System.out.println(buildGetUrl("http://www.baidu.com/?wahaha=111", map));

        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, "UTF-8");

        System.out.println(urlEncodedFormEntity.toString());
        BufferedReader br=new BufferedReader(new InputStreamReader(urlEncodedFormEntity.getContent()));
        System.out.println(br.readLine());


    }

}
