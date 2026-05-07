**[中文](README_CN.md) | English**

# Hotcoin Futures Java SDK

Java example code for the Hotcoin Perpetual Futures REST and WebSocket APIs, covering market data, assets, trading, positions, and real-time push subscriptions.

**API Documentation:** https://hotcoinex.github.io/docs/

---

## Requirements

- Java 8+
- Maven 3.x

---

## Quick Start

### 1. Clone the repository

```bash
git clone https://github.com/hotcoinexchange/hotcoin_futures_java.git
cd hotcoin_futures_java
```

### 2. Set your API credentials

In `src/main/java/com/hotcoin/swap_api/enums/GlobalConfigEnum.java`, replace the placeholder keys:

```java
YOUR("YOUR_ACCESS_KEY", "YOUR_SECRET_KEY", "https://api-ct.hotcoin.fit", "HmacSHA256")
```

WebSocket examples use `src/main/java/com/hotcoin/api/constant/PrivateApiConfig.java`:

```java
public static String YOUR_KEY        = "YOUR_ACCESS_KEY";
public static String YOUR_SECRET_KEY = "YOUR_SECRET_KEY";
```

> **Security notice:** Never commit real API credentials to version control. Use environment variables or an external config file instead.

### 3. Build

```bash
mvn clean package -DskipTests
```

### 4. Run an example

Open any `Example` class under `src/test/java/` in your IDE and run its `main` method.

---

## Endpoints

| Protocol | URL |
| -------- | --- |
| REST API | `https://api-ct.hotcoin.fit` |
| WebSocket | `wss://wss-ct.hotcoin.fit` |

---

## Examples

### Market — public, no signature required

| File | Description |
| ---- | ----------- |
| `market/PublicExpample.java` | Available contracts list |
| `market/PublicCandlesExpample.java` | Kline / candlestick data |
| `market/OrderbookExpample.java` | Order book (depth) |
| `market/FillsExpample.java` | Latest trades |
| `market/IndexInfoExpample.java` | Index price info |
| `market/FeeRateExpample.java` | Historical funding rates |
| `market/PremiumIndexExpample.java` | Latest mark price and funding rate |

### Assets — signature required

| File | Description |
| ---- | ----------- |
| `assets/AssetsExample.java` | Query assets for a single contract |
| `assets/AssetsListExample.java` | Query assets for all contracts |
| `assets/DealRecordExample.java` | Transaction history |

### Trading — signature required

| File | Description |
| ---- | ----------- |
| `trading/OrderExample.java` | Place an order (limit / market) |
| `trading/BatchOrderExample.java` | Place orders in batch |
| `trading/DeleteOrderExample.java` | Cancel an order |
| `trading/BatchDeleteOrderExample.java` | Cancel orders in batch |
| `trading/BatchDeleteOrderByIdExample.java` | Cancel orders by ID in batch |
| `trading/ClosePositionExample.java` | Close position (one-click) |
| `trading/ConditionalOrderExample.java` | Place a conditional order |
| `trading/DeleteOrderConditionExample.java` | Cancel a conditional order |
| `trading/BatchDeleteOrderConditionExample.java` | Cancel conditional orders in batch |
| `trading/OrderDetailsExample.java` | Query order details |
| `trading/OrderListExample.java` | Active order list |
| `trading/HistoryListExample.java` | Historical order list |
| `trading/OrderDealDetailExample.java` | Order fill details |

### Position — signature required

| File | Description |
| ---- | ----------- |
| `position/PositionListExample.java` | Current positions |
| `position/PositionLeverExample.java` | Adjust leverage |
| `position/PositionMarginExample.java` | Adjust margin |
| `position/PositionSettingExample.java` | Position mode (cross / isolated) |
| `position/PositionConfigsExample.java` | Query position configuration |
| `position/LeverGearsExample.java` | Query available leverage tiers |

### WebSocket Push Subscriptions

| File | Description |
| ---- | ----------- |
| `push/SigninExample.java` | WebSocket authentication |
| `push/TickerExample.java` | Subscribe to ticker for one contract |
| `push/TickersExample.java` | Subscribe to all tickers |
| `push/DepthExample.java` | Subscribe to order book |
| `push/CandlesExample.java` | Subscribe to kline |
| `push/FillsExample.java` | Subscribe to latest trades |
| `push/FundRateExample.java` | Subscribe to funding rate |
| `push/FundRatesExample.java` | Subscribe to all funding rates |
| `push/NewCurrencyExample.java` | Subscribe to new contract listings |
| `push/OrderExample.java` | Subscribe to order updates (private) |
| `push/AssetExample.java` | Subscribe to asset updates (private) |
| `push/PositionExample.java` | Subscribe to position updates (private) |
| `push/ConditionOrdersExample.java` | Subscribe to conditional order updates (private) |

---

## Signature

All private interfaces use HmacSHA256 signing:

1. Concatenate the HTTP method (uppercase), hostname, request path, and ASCII-sorted URL-encoded parameters with `\n` as the separator
2. Sign the resulting string with HmacSHA256 using your `SecretKey`, then Base64-encode the result
3. Append the encoded value as the `Signature` parameter at the end of the request

Signature implementation:
- `src/main/java/com/hotcoin/swap_api/util/SignatureUtil.java`
- `src/main/java/com/hotcoin/api/utils/SignatureUtil.java`

---

## Dependencies

| Dependency | Purpose |
| ---------- | ------- |
| okhttp3 4.9.3 | REST HTTP client |
| Java-WebSocket | WebSocket connection |
| netty-all 4.1.75 | Network transport |
| fastjson | JSON serialization |
| commons-codec | Base64 / HmacSHA256 |
| lombok | Boilerplate reduction |

---

## License

[MIT](LICENSE.md)
