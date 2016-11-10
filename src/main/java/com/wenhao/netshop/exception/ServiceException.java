package com.wenhao.netshop.exception;

/**
 * Created by lenovo on 2016/11/10.
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = -1708015121235851228L;

    public ServiceException(String message) {
        super(message);
    }
}
