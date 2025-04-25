package com.hotcoin.api.utils;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.clint.HotcoinWebSocketClient;
import com.hotcoin.api.constant.PrivateApiConfig;
import lombok.SneakyThrows;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * WebSocket工具类
 * @author zenghaihui
 * @date 2024/4/10
 */
public class WebSocketUtil {

    /** 定时任务处理心跳 */
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    /** 心跳间隔（seconds） */
    private static final int HEARTBEAT_INTERVAL = 2;
    private static Long connectTime = 0l;
    /**
     * web连接
     * @param url 连接地址
     * @param params json入参
     * @param accessKey 是否登陆
     * @param shortConnect 是否闪断
     */
    public static void webConnect(String url, String params, String accessKey, boolean shortConnect){
        try {
            URI uri = new URI(url);
            HotcoinWebSocketClient client = new HotcoinWebSocketClient(uri){
                @SneakyThrows
                @Override
                public void onOpen(ServerHandshake handShakeData) {
                    System.out.println("Connected to server");
                    if(null != accessKey){
                        System.out.println("login message: " + loginGenerate(accessKey));
                        send(loginGenerate(accessKey));
                    }

                    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
                    Runnable task = () -> {
                        if(null != params){
                            System.out.println("send message: " + params);
                            send(params);
                        }
                    };
                    executorService.schedule(task, 3, TimeUnit.SECONDS);

                }
            };
            client.connect();
            connectTime = System.currentTimeMillis();
            if(shortConnect) {
                client.close();
                return;
            }
            startHeartbeat(client);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时任务调用心跳
     * @param client
     */
    public static void startHeartbeat(HotcoinWebSocketClient client) {
        // 创建一个Runnable任务来发送心跳
        Runnable heartbeatTask = () -> sendHeartbeat(client);
        scheduler.scheduleAtFixedRate(heartbeatTask, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 发送心跳
     * @param client
     */
    private static void sendHeartbeat(HotcoinWebSocketClient client) {
        if (client != null && client.isOpen()) {
            try {
                System.out.println("Sending heartbeat...已连接" + (System.currentTimeMillis() - connectTime) / 1000 + "秒");
                client.send("{ \"event\": \"ping\"}");
            } catch (Exception e) {
                System.err.println("Failed to send heartbeat: " + e.getMessage());
                // 尝试重新连接
                reconnect(client);
            }
        } else {
            System.err.println("WebSocket connection is closed. Stopping heartbeat.");
            stopHeartbeat();
        }
    }

    /**
     * 发送心跳
     * @param client
     */
    private static void reconnect(HotcoinWebSocketClient client) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                System.out.println("Attempting to reconnect... Attempt " + (attempts + 1));
                client.reconnectBlocking();
                System.out.println("Reconnected successfully!");
                return;
            } catch (Exception e) {
                System.err.println("Reconnect failed: " + e.getMessage());
                attempts++;
                try {
                    Thread.sleep(5000); // 等待 5 秒后重试
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.err.println("Max reconnection attempts reached. Closing client.");
        stopHeartbeat();
    }

    /**
     * 关闭心跳
     */
    public static void stopHeartbeat() {
        scheduler.shutdown();
    }

    /**
     * 请求参数制造方法
     * @return
     */
    static String loginGenerate(String accessKey){
        long time = System.currentTimeMillis();
        Map<String,Object> pushMsg = new LinkedHashMap<>();
        /** 请求类型 */
        pushMsg.put("event","signin");
        Map<String,Object> params = new LinkedHashMap<>();
        /** 访问key */
        params.put("apiKey", accessKey);
        /** 签名 */
        params.put("signature", SignatureGenerator.createWebSocketSignature(time, accessKey));
        /** timestamp */
        params.put("timestamp",time);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    /**
     * 压测使用
     * @param url 连接地址
     * @param params json入参
     * @param loginIn 是否登陆
     */
    public static void webPressureConnect(String url, String params,boolean loginIn){
        try {
            URI uri = new URI(url);
            HotcoinWebSocketClient client = new HotcoinWebSocketClient(uri){
                @SneakyThrows
                @Override
                public void onOpen(ServerHandshake handShakeData) {
                    System.out.println("Connected to server");
                    if(loginIn){
                        System.out.println("login message: " + loginGenerate(PrivateApiConfig.HUGH_KEY));
                        send(loginGenerate(PrivateApiConfig.HUGH_KEY));
                    }
                    close();
                }
            };
            client.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
