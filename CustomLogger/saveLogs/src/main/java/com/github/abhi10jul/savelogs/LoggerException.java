package com.github.abhi10jul.savelogs;

/**
 * @author abhishek.
 */
public class LoggerException extends Exception {

    public LoggerException(String message) {
        super(message);
    }

    public LoggerException(String message, Throwable cause) {
        super(message, cause);
    }

}
