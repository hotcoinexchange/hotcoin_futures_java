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
 * WebSocket 连接工具类（支持登录鉴权、自动心跳保活与断线重连）
 * WebSocket connection utility with optional authentication, scheduled heartbeat and auto-reconnect.
 *
 * <p>主要功能 / Key features:
 * <ul>
 *   <li>建立 WebSocket 连接，并可选传入 accessKey/secretKey 完成登录鉴权
 *       / Establishes a WebSocket connection with optional login authentication</li>
 *   <li>连接建立后延迟 3 秒发送订阅消息
 *       / Sends the subscription message 3 seconds after the connection opens</li>
 *   <li>通过定时心跳保持长连接活跃
 *       / Keeps the long connection alive via a scheduled heartbeat</li>
 *   <li>心跳失败时自动重连，最多重试 5 次
 *       / Automatically reconnects on heartbeat failure, up to 5 attempts</li>
 * </ul>
 *
 * @author hugh
 * @date 2024/4/10
 */
public class WebSocketUtil {

    /** 定时任务调度器，用于处理心跳 / Scheduled executor for heartbeat tasks */
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /** 心跳发送间隔（秒）/ Heartbeat sending interval in seconds */
    private static final int HEARTBEAT_INTERVAL = 5;

    /** 连接建立时间戳（毫秒），用于计算已连接时长
     *  Timestamp (ms) when the connection was established, used to log connection duration */
    private static Long connectTime = 0L;

    /**
     * 建立 WebSocket 连接（可选登录鉴权）
     * Establishes a WebSocket connection, optionally with authentication.
     *
     * <p>流程 / Flow:
     * <ol>
     *   <li>将 url 字符串解析为 URI 对象 / Parse url string into a URI object</li>
     *   <li>创建 HotcoinWebSocketClient 并覆写 onOpen 回调 / Create client and override onOpen</li>
     *   <li>若 accessKey 不为 null，则先发送登录消息 / If accessKey is provided, send sign-in message first</li>
     *   <li>延迟 3 秒后发送订阅参数 / Send subscription params after a 3-second delay</li>
     *   <li>若非短连接（shortConnect=false），则启动心跳调度器 / If not a short connect, start heartbeat scheduler</li>
     * </ol>
     *
     * @param url          WebSocket 服务地址，例如 wss://wss-ct.hotcoin.fit
     *                     / WebSocket server URL, e.g. wss://wss-ct.hotcoin.fit
     * @param params       订阅消息 JSON 字符串（连接建立后延迟发送）
     *                     / Subscription message JSON string (sent after connection opens with delay)
     * @param accessKey    AccessKey（传 null 表示无需登录）/ AccessKey (pass null to skip login)
     * @param secretKey    SecretKey（传 null 表示无需登录）/ SecretKey (pass null to skip login)
     * @param shortConnect 是否为短连接（true 则发完登录消息后立即关闭）
     *                     / Whether to use a short-lived connection (closes immediately after login if true)
     */
    public static void webConnect(String url, String params, String accessKey, String secretKey, boolean shortConnect) {
        try {
            // 将 URL 字符串解析为 URI / Parse URL string to URI
            URI uri = new URI(url);
            HotcoinWebSocketClient client = new HotcoinWebSocketClient(uri) {
                @SneakyThrows
                @Override
                public void onOpen(ServerHandshake handShakeData) {
                    System.out.println("Connected to server");

                    // 登录鉴权 begin — 若提供 accessKey 则发送签名登录消息
                    // Authentication begin — send signed sign-in message if accessKey is provided
                    if (null != accessKey) {
                        String login = loginGenerate(accessKey, secretKey);
                        System.out.println("login message: " + login);
                        send(login); // 发送登录消息 / Send login message
                    }
                    // 登录鉴权 end / Authentication end

                    // 延迟 3 秒后发送订阅消息（等待服务端完成鉴权）
                    // Send subscription message after 3-second delay (waits for server auth)
                    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
                    Runnable task = () -> {
                        if (null != params) {
                            System.out.println("send message: " + params);
                            send(params); // 发送订阅参数 / Send subscription parameters
                        }
                    };
                    executorService.schedule(task, 3, TimeUnit.SECONDS);
                }
            };
            client.connect(); // 发起 WebSocket 连接 / Initiate WebSocket connection
            connectTime = System.currentTimeMillis(); // 记录连接时间 / Record connection timestamp

            if (shortConnect) {
                // 短连接模式：发完即断 / Short-connect mode: close immediately after sending
                client.close();
                return;
            }
            // 启动心跳任务保持长连接 / Start heartbeat scheduler to keep long connection alive
            startHeartbeat(client);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动定时心跳任务
     * Starts the scheduled heartbeat task.
     *
     * <p>按 {@link #HEARTBEAT_INTERVAL} 秒的固定频率调用 {@link #sendHeartbeat}。
     * Calls {@link #sendHeartbeat} at a fixed rate of {@link #HEARTBEAT_INTERVAL} seconds.</p>
     *
     * @param client 需要保活的 WebSocket 客户端 / The WebSocket client to keep alive
     */
    public static void startHeartbeat(HotcoinWebSocketClient client) {
        // 创建心跳 Runnable 并以固定频率调度 / Create heartbeat task and schedule at fixed rate
        Runnable heartbeatTask = () -> sendHeartbeat(client);
        scheduler.scheduleAtFixedRate(heartbeatTask, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 发送心跳包并检测连接状态
     * Sends a ping and checks connection health.
     *
     * <p>若连接正常，发送 {"event":"ping"}；若发送失败则尝试重连；
     * 若连接已断开则停止心跳调度。
     * Sends {"event":"ping"} if the connection is open; attempts reconnect on failure;
     * stops heartbeat if the connection is closed.</p>
     *
     * @param client WebSocket 客户端 / WebSocket client
     */
    private static void sendHeartbeat(HotcoinWebSocketClient client) {
        if (client != null && client.isOpen()) {
            try {
                // 打印已连接时长（秒）/ Print elapsed connection time in seconds
                System.out.println("Sending heartbeat...已连接" + (System.currentTimeMillis() - connectTime) / 1000 + "秒");
                client.send("{ \"event\": \"ping\"}"); // 发送 ping 心跳包 / Send ping heartbeat
            } catch (Exception e) {
                System.err.println("Failed to send heartbeat: " + e.getMessage());
                // 心跳失败，尝试断线重连 / Heartbeat failed, attempt reconnection
                reconnect(client);
            }
        } else {
            // 连接已断开，停止心跳 / Connection closed, stop heartbeat
            System.err.println("WebSocket connection is closed. Stopping heartbeat.");
            stopHeartbeat();
        }
    }

    /**
     * 尝试断线重连（最多 5 次）
     * Attempts reconnection up to 5 times.
     *
     * <p>每次重连失败后等待 5 秒再试；若所有尝试均失败则停止心跳。
     * Waits 5 seconds between attempts; stops the heartbeat scheduler if all retries fail.</p>
     *
     * @param client 需要重连的 WebSocket 客户端 / The WebSocket client to reconnect
     */
    private static void reconnect(HotcoinWebSocketClient client) {
        int attempts = 0;
        while (attempts < 5) { // 最多重试 5 次 / Maximum 5 retry attempts
            try {
                System.out.println("Attempting to reconnect... Attempt " + (attempts + 1));
                client.reconnectBlocking(); // 同步重连 / Blocking reconnect
                System.out.println("Reconnected successfully!");
                return; // 重连成功，退出 / Reconnection succeeded, return
            } catch (Exception e) {
                System.err.println("Reconnect failed: " + e.getMessage());
                attempts++;
                try {
                    Thread.sleep(5000); // 等待 5 秒后重试 / Wait 5 seconds before next attempt
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        // 超过最大重连次数，关闭心跳 / Max attempts reached, stop heartbeat
        System.err.println("Max reconnection attempts reached. Closing client.");
        stopHeartbeat();
    }

    /**
     * 停止心跳任务调度器
     * Stops the heartbeat scheduler.
     */
    public static void stopHeartbeat() {
        scheduler.shutdown(); // 关闭调度线程池 / Shut down the scheduler thread pool
    }

    /**
     * 生成 WebSocket 登录鉴权消息（含 HMAC-SHA256 签名）
     * Builds the WebSocket sign-in message with HMAC-SHA256 signature.
     *
     * <p>消息格式 / Message format:
     * <pre>
     * {
     *   "event": "signin",
     *   "params": {
     *     "apiKey": "&lt;accessKey&gt;",
     *     "signature": "&lt;HmacSHA256 signature&gt;",
     *     "timestamp": &lt;currentTimeMillis&gt;
     *   }
     * }
     * </pre>
     *
     * @param accessKey 明文 AccessKey / Plain-text access key
     * @param secretKey 明文 SecretKey（用于 HMAC 签名）/ Plain-text secret key (used for HMAC signing)
     * @return 序列化后的登录消息 JSON 字符串 / Serialized JSON string of the sign-in message
     */
    static String loginGenerate(String accessKey, String secretKey) {
        long time = System.currentTimeMillis(); // 当前时间戳（毫秒）/ Current timestamp in ms
        Map<String, Object> pushMsg = new LinkedHashMap<>();
        pushMsg.put("event", "signin"); // 请求类型：登录 / Request type: sign in

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("apiKey", accessKey);    // 访问密钥 / Access key identifier
        // 使用 SignatureGenerator 生成 WebSocket 签名 / Generate WebSocket signature via SignatureGenerator
        params.put("signature", SignatureGenerator.createWebSocketSignature(time, accessKey, secretKey));
        params.put("timestamp", time);      // 时间戳（防重放攻击）/ Timestamp (replay attack protection)

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg); // 序列化为 JSON 字符串 / Serialize to JSON string
    }

    /**
     * 压测专用连接方法（连接后立即关闭，不维持长连接）
     * Stress-test connection method — connects, sends a message, then closes immediately.
     *
     * @param url      WebSocket 连接地址 / WebSocket server URL
     * @param params   订阅消息 JSON 字符串 / Subscription message JSON
     * @param loginIn  是否需要登录 / Whether to send a login message
     */
    public static void webPressureConnect(String url, String params, boolean loginIn) {
        try {
            URI uri = new URI(url); // 解析 URI / Parse URI
            HotcoinWebSocketClient client = new HotcoinWebSocketClient(uri) {
                @SneakyThrows
                @Override
                public void onOpen(ServerHandshake handShakeData) {
                    System.out.println("Connected to server");
                    if (loginIn) {
                        // 需要登录时发送鉴权消息 / Send auth message if login is required
                        String login = loginGenerate(PrivateApiConfig.YOUR_KEY, PrivateApiConfig.YOUR_SECRET_KEY);
                        System.out.println("login message: " + login);
                        send(login);
                    }
                    close(); // 发完立即关闭（压测模式）/ Close immediately after sending (stress-test mode)
                }
            };
            client.connect(); // 发起连接 / Initiate connection
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
