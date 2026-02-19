package org.mailosz.crmrest.exception;

import java.util.Collections;
import java.util.Map;

public class ErrorResponse {
    private final String errMessage;
    private final String errCode;
    private final Map<String,Object> errMeta;

    public ErrorResponse(String errMessage, String errCode, Map<String,Object> errMeta) {
        this.errMessage = errMessage;
        this.errCode = errCode;
        this.errMeta = errMeta;
    }
    public ErrorResponse(String errMessage, String errCode) {
        this.errMessage = errMessage;
        this.errCode = errCode;
        this.errMeta = Collections.emptyMap();
    }
    public String getErrMessage() {
        return errMessage;
    }
    public String getErrCode() {
        return errCode;
    }
    public Map<String, Object> getErrMeta() {
        return errMeta;
    }
}
