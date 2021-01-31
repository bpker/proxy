package com.kindee.proxyapi.exception;

public class SystemException extends RuntimeException {

    public static final SystemException CANNOT_INSTANTIATION_CLASS = new SystemException("No object for this class");

    /**
     * 构造方法, 制定message
     *
     * @param message 错误信息
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * 构造方法, 指定message和cause
     *
     * @param message 错误信息
     * @param cause   cause
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
