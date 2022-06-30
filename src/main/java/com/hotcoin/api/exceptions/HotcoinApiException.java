package com.hotcoin.api.exceptions;

/**
 * Hotcoin Api Exception
 * Hotcoin Api 异常
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
public class HotcoinApiException extends RuntimeException {

    public static final String INPUT_ERROR = "InputError";

    public static final String RUNTIME_ERROR = "RuntimeError";

    public static final String ENV_ERROR = "EnvironmentError";

    public static final String EXEC_ERROR = "ExecuteError";

    public static final String APIKEY_ERROR = "ApiKeyError";

    public static final String SYSTEM_ERROR = "SystemError";

    private final String errCode;

    public HotcoinApiException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

}
