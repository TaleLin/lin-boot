package com.talelin.linboot.log.constant;

/**
 * @author 桔子
 * @since 2020/12/8 00:00
 */
public class MDCAccessConstant {
    /**
     * 请求方法：GET、POST……
     */
    public static final String REQUEST_METHOD_MDC_KEY = "req.method";

    /**
     * 请求状态码：200、404……
     */
    public static final String RESPONSE_STATUS_MDC_KEY = "res.status";

    /**
     * 请求来源
     */
    public static final String REQUEST_REFERER_MDC_KEY = "req.referer";

    /**
     * 请求协议：HTTP、HTTPS
     */
    public static final String REQUEST_PROTOCOL_MDC_KEY = "req.protocol";

    /**
     * UA 信息
     */
    public static final String REQUEST_USER_AGENT_MDC_KEY = "req.userAgent";

    /**
     * 客户端 IP
     */
    public static final String REQUEST_REMOTE_HOST_MDC_KEY = "req.remoteHost";

    /**
     * 客户端主机名
     */
    public static final String REQUEST_REMOTE_ADDR_MDC_KEY = "req.remoteAddr";

    /**
     * 请求 URI
     */
    public static final String REQUEST_REQUEST_URI_MDC_KEY = "req.requestURI";

    /**
     * 请求 URL
     */
    public static final String REQUEST_REQUEST_URL_MDC_KEY = "req.requestURL";

    /**
     * 查询参数
     */
    public static final String REQUEST_QUERY_STRING_MDC_KEY = "req.queryString";

    public static final String REQUEST_X_FORWARDED_FOR_MDC_KEY = "req.xForwardedFor";
    public static final String REQUEST_BODY_BYTES_SENT_MDC_KEY = "req.bodyBytesSent";
    public static final String REQUEST_REMOTE_PORT_MDC_KEY = "req.remotePort";

    /**
     * 日志 TRACE_ID，用于跟踪请求链路
     */
    public static final String TRACE_ID_KEY = "trace.id";
}
