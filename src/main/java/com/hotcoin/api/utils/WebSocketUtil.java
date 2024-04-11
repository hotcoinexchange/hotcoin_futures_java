package com.hotcoin.api.utils;

import com.hotcoin.api.clint.HotcoinWebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
     * 闪断连接（只请求一次就断开）
     * @param url
     * @param params
     */
    public static void fastConnect(String url, String params){
        try {
            URI uri = new URI(url);
            HotcoinWebSocketClient client = new HotcoinWebSocketClient(uri){
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("Connected to server");
                    System.out.println("send message: " + params);
                    send(params);
                }
            };
            client.connect();
            client.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    //todo 短暂连接（5分钟后停止）
    public static void shortConnect(String url, String params){
        stopHeartbeat();
    }

    /**
     * 长期连接
     * @param url
     * @param params
     */
    public static void longConnect(String url, String params){
        try {
            URI uri = new URI(url);
            HotcoinWebSocketClient client = new HotcoinWebSocketClient(uri){
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("Long Connected to server");
                    System.out.println("send message: " + params);
                    send(params);
                }
            };
            client.connect();
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
            // Handle exception, e.g., reconnect or shutdown
            e.printStackTrace();
        }
    }

    /**
     * 关闭心跳
     */
    public static void stopHeartbeat() {
        // 关闭ScheduledExecutorService，这将停止所有正在执行和等待执行的任务
        scheduler.shutdown();
    }
}
