**中文 | [English](README.md)**

# Hotcoin Futures Java SDK

Hotcoin 永续合约 REST & WebSocket API 的 Java 示例代码库，覆盖行情、资产、交易、仓位、推送订阅等全部接口。

**API 文档：** https://hotcoinex.github.io/docs/

---

## 环境要求

- Java 8+
- Maven 3.x

---

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/hotcoinexchange/hotcoin_futures_java.git
cd hotcoin_futures_java
```

### 2. 填写 API Key

在 `src/main/java/com/hotcoin/swap_api/enums/GlobalConfigEnum.java` 中替换密钥：

```java
YOUR("YOUR_ACCESS_KEY", "YOUR_SECRET_KEY", "https://api-ct.hotcoin.fit", "HmacSHA256")
```

WebSocket 示例使用 `src/main/java/com/hotcoin/api/constant/PrivateApiConfig.java`：

```java
public static String YOUR_KEY        = "YOUR_ACCESS_KEY";
public static String YOUR_SECRET_KEY = "YOUR_SECRET_KEY";
```

> **安全提示：** 请勿将真实密钥提交到版本控制系统，建议通过环境变量或配置文件管理密钥。

### 3. 编译

```bash
mvn clean package -DskipTests
```

### 4. 运行示例

直接在 IDE 中运行 `src/test/java/` 下对应的 Example 类的 `main` 方法即可。

---

## 接口地址

| 协议 | 地址 |
| ---- | ---- |
| REST API | `https://api-ct.hotcoin.fit` |
| WebSocket | `wss://wss-ct.hotcoin.fit` |

---

## 示例目录

### 行情（Market）— 公开接口，无需签名

| 文件 | 说明 |
| ---- | ---- |
| `market/PublicExpample.java` | 获取合约列表 |
| `market/PublicCandlesExpample.java` | K 线数据 |
| `market/OrderbookExpample.java` | 深度行情 |
| `market/FillsExpample.java` | 最新成交记录 |
| `market/IndexInfoExpample.java` | 指数价格信息 |
| `market/FeeRateExpample.java` | 历史资金费率 |
| `market/PremiumIndexExpample.java` | 最新标记价格和资金费率 |

### 资产（Assets）— 需要签名

| 文件 | 说明 |
| ---- | ---- |
| `assets/AssetsExample.java` | 查询单个合约资产 |
| `assets/AssetsListExample.java` | 查询全部合约资产 |
| `assets/DealRecordExample.java` | 历史成交记录 |

### 交易（Trading）— 需要签名

| 文件 | 说明 |
| ---- | ---- |
| `trading/OrderExample.java` | 普通下单（限价 / 市价） |
| `trading/BatchOrderExample.java` | 批量下单 |
| `trading/DeleteOrderExample.java` | 撤销订单 |
| `trading/BatchDeleteOrderExample.java` | 批量撤销订单 |
| `trading/BatchDeleteOrderByIdExample.java` | 按 ID 批量撤销订单 |
| `trading/ClosePositionExample.java` | 一键平仓 |
| `trading/ConditionalOrderExample.java` | 条件单下单 |
| `trading/DeleteOrderConditionExample.java` | 撤销条件单 |
| `trading/BatchDeleteOrderConditionExample.java` | 批量撤销条件单 |
| `trading/OrderDetailsExample.java` | 查询订单详情 |
| `trading/OrderListExample.java` | 当前委托列表 |
| `trading/HistoryListExample.java` | 历史委托列表 |
| `trading/OrderDealDetailExample.java` | 订单成交明细 |

### 仓位（Position）— 需要签名

| 文件 | 说明 |
| ---- | ---- |
| `position/PositionListExample.java` | 当前持仓列表 |
| `position/PositionLeverExample.java` | 调整杠杆倍数 |
| `position/PositionMarginExample.java` | 调整保证金 |
| `position/PositionSettingExample.java` | 仓位设置（全仓 / 逐仓） |
| `position/PositionConfigsExample.java` | 查询仓位配置 |
| `position/LeverGearsExample.java` | 查询可用杠杆档位 |

### WebSocket 推送订阅（Push）

| 文件 | 说明 |
| ---- | ---- |
| `push/SigninExample.java` | WebSocket 登录鉴权 |
| `push/TickerExample.java` | 订阅单合约行情 |
| `push/TickersExample.java` | 订阅全量行情 |
| `push/DepthExample.java` | 订阅深度行情 |
| `push/CandlesExample.java` | 订阅 K 线 |
| `push/FillsExample.java` | 订阅最新成交 |
| `push/FundRateExample.java` | 订阅资金费率 |
| `push/FundRatesExample.java` | 订阅全量资金费率 |
| `push/NewCurrencyExample.java` | 订阅新合约上线通知 |
| `push/OrderExample.java` | 订阅订单推送（私有） |
| `push/AssetExample.java` | 订阅资产推送（私有） |
| `push/PositionExample.java` | 订阅仓位推送（私有） |
| `push/ConditionOrdersExample.java` | 订阅条件单推送（私有） |

---

## 签名说明

所有私有接口均采用 HmacSHA256 签名，签名步骤：

1. 将 `GET`（或 `POST`）、Host、请求路径、按 ASCII 排序并 URL 编码后的参数串依次以 `\n` 连接
2. 以 `SecretKey` 为密钥进行 HmacSHA256 计算，再做 Base64 编码
3. 将编码结果作为 `Signature` 参数附加到请求末尾

签名实现参见：
- `src/main/java/com/hotcoin/swap_api/util/SignatureUtil.java`
- `src/main/java/com/hotcoin/api/utils/SignatureUtil.java`

---

## 主要依赖

| 依赖 | 用途 |
| ---- | ---- |
| okhttp3 4.9.3 | REST HTTP 请求 |
| Java-WebSocket | WebSocket 连接 |
| netty-all 4.1.75 | 网络通信 |
| fastjson | JSON 序列化 |
| commons-codec | Base64 / HmacSHA256 |
| lombok | 简化 POJO 代码 |

---

## License

[MIT](LICENSE.md)
