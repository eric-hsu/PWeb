package com.jinxin.common.exception;

/**
 * 基础异常类
 * 
 * @author chenmubing
 * @since 1.0
 * @see Exception
 */
public class BaseException extends Exception {
    private String msg;

    private String errno;

    private static final long serialVersionUID = 3358888911029354719L;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
        this.msg = message;
        int pos = message.indexOf(":");
        this.errno = "-1024";
        if (-1 < pos) {
            try {
                errno = message.substring(0, pos).trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
        this.errno = parseErrno(msg);
    }

    public BaseException(String exceptionMsg, String service_error) {
        super(exceptionMsg);
        this.msg = exceptionMsg;
        this.errno = service_error;
    }

    protected String parseErrno(String message) {
        String errno = "-1024";
        int pos = message.indexOf(":");
        if (-1 < pos) {
            try {
                errno = message.substring(0, pos).trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return errno;
    }

    public String getErrno() {
        return errno;
    }

    public String getMsg() {
        return msg;
    }

}
