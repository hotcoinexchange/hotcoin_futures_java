package com.hotcoin.api.utils;

import com.hotcoin.api.exceptions.HotcoinApiException;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Hotcoin Api ConnectionFactory
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
public class ConnectionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);

    private static final int maxIdleConnections = 20;

    private static final long keepAliveDuration = 300;

    private static final long connectTimeout = 5000;

    private static final long readTimeout = 5000;

    private static final long writeTimeout = 5000;

    private static final ConnectionPool CONNECTION_POOL = new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);

    /**
     * Sock代理配置
     */
    private static final String PROXY_HOSTNAME = "127.0.0.1";
    private static final int PROXY_PORT = 1080;
    private static final Proxy PROXY = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(PROXY_HOSTNAME, PROXY_PORT));

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .followSslRedirects(Boolean.FALSE)
            .followRedirects(Boolean.FALSE)
//            .proxy(PROXY) // 代理
            .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            .connectionPool(CONNECTION_POOL)
            .build();

    /**
     * Request Execute
     * 请求执行
     *
     * @param request
     * @return
     */
    public static String execute(Request request) {
        Response response = null;
        String responseString = null;
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("RequestURL={}", request.url());
            }
            response = client.newCall(request).execute();
            if (response.code() != 200) {
                throw new HotcoinApiException(HotcoinApiException.EXEC_ERROR, "Execute Error Code : " + response.code() + " Message : " + response.message());
            }
            if (response != null && response.body() != null) {
                responseString = response.body().string();
                response.close();
            } else {
                throw new HotcoinApiException(HotcoinApiException.ENV_ERROR, "Execute Response Error From Server");
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("responseString={}", responseString);
            }
            return responseString;
        } catch (IOException ex) {
            LOG.error("Execute Exception", ex);
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR, "Execute Exception");
        }
    }

}
