package com.hotcoin.api.utils;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.clint.HotcoinWebSocketClient;
import com.hotcoin.api.constant.PrivateApiConfig;
import lombok.SneakyThrows;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
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
    private static final int HEARTBEAT_INTERVAL = 30;

    /**
     * web连接
     * @param url 连接地址
     * @param params json入参
     * @param loginIn 是否登陆
     * @param shortConnect 是否闪断
     */
    public static void webConnect(String url, String params,boolean loginIn, boolean shortConnect){
        try {
            URI uri = new URI(url);
            HotcoinWebSocketClient client = new HotcoinWebSocketClient(uri){
                @SneakyThrows
                @Override
                public void onOpen(ServerHandshake handShakeData) {
                    System.out.println("Connected to server");
                    if(loginIn){
                        System.out.println("login message: " + loginGenerate());
                        send(loginGenerate());
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

        // 使用ScheduledExecutorService安排任务进行重复执行
        // initialDelay表示任务开始之前的延迟
        // period表示任务执行的间隔时间
        // unit表示时间单位
        scheduler.scheduleAtFixedRate(heartbeatTask, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 发送心跳
     * @param client
     */
    private static void sendHeartbeat(HotcoinWebSocketClient client) {
        try {
            client.send("{ \"event\": \"ping\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    static String loginGenerate(){
        long time = System.currentTimeMillis();
        Map<String,Object> pushMsg = new LinkedHashMap<>();
        /** 请求类型 */
        pushMsg.put("event","signin");
        Map<String,Object> params = new LinkedHashMap<>();
        /** 访问key */
        params.put("apiKey", PrivateApiConfig.ACCESS_KEY);
        /** 签名 */
        params.put("signature", SignatureGenerator.createWebSocketSignature(time));
        /** timestamp */
        params.put("timestamp",time);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }
}
