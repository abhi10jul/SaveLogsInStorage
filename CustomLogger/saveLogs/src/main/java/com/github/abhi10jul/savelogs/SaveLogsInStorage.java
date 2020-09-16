package com.github.abhi10jul.savelogs;

import android.content.Context;
import android.util.Log;

/**
 * @author abhishek.
 */
public class SaveLogsInStorage {
    private static CLogger logger;

    private SaveLogsInStorage(Context context, String directoryName) {
        try {
            logger = new CLogger(context, directoryName);
        } catch (LoggerException e) {
            e.printStackTrace();
            Log.e("LoggerException", "Exception Occurs ", e);
        }
    }

    public static synchronized SaveLogsInStorage getSaveLoggerInstance(Context context, String directoryName) {
        return new SaveLogsInStorage(context, directoryName);
    }

    public void saveErrorLogs(String TAG, String errorMessage) {
        if (logger != null)
            logger.onError(TAG, errorMessage);
    }

    public void saveErrorLogs(String TAG, String errorMessage, Throwable t) {
        if (logger != null)
            logger.onError(TAG, errorMessage, t);
    }

    public void saveDebugLogs(String TAG, String debugMessage) {
        if (logger != null)
            logger.onDebug(TAG, debugMessage);
    }

    public void saveDebugLogs(String TAG, String debugMessage, Throwable t) {
        if (logger != null)
            logger.onDebug(TAG, debugMessage, t);
    }

    public void saveInfoLogs(String TAG, String infoMessage) {
        if (logger != null)
            logger.onInfo(TAG, infoMessage);
    }

    public void saveInfoLogs(String TAG, String infoMessage, Throwable t) {
        if (logger != null)
            logger.onInfo(TAG, infoMessage, t);
    }

    public void saveWarningLogs(String TAG, String warningMessage) {
        if (logger != null)
            logger.onWarning(TAG, warningMessage);
    }

    public void saveWarningLogs(String TAG, String warningMessage, Throwable t) {
        if (logger != null)
            logger.onWarning(TAG, warningMessage, t);
    }
}
