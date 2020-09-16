package com.github.abhi10jul.savelogs;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.text.MessageFormat;

import de.mindpipe.android.logging.log4j.LogConfigurator;

import static com.github.abhi10jul.savelogs.PathConstant.LOGGER_FILE_PATH;

/**
 * @author abhishek.
 */
class CLogger {
    protected static String DIRECTORY_NAME = "saveLogs";
    private Logger logger;
    private final String NO_MSG = "No message available!!";

    protected CLogger(Context context, String directoryName) throws LoggerException {
        if (directoryName == null || directoryName.isEmpty())
            throw new LoggerException("Directory Name is not empty.");
        if (this.isStoragePermissionGranted(context)) {
            DIRECTORY_NAME = directoryName;
            this.logger = getLogger(context);
        } else {
            throw new LoggerException("Permission required!! Write storage permission is not granted");
        }
    }


    private boolean isStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private Logger getLogger(Context context) throws LoggerException {
        try {
            LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setFileName(LOGGER_FILE_PATH);
            logConfigurator.setRootLevel(Level.ALL);
            logConfigurator.setLevel("org.apache", Level.ALL);
            logConfigurator.setUseFileAppender(true);
            logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
            logConfigurator.setMaxFileSize(1024 * 1024 * 5);
            logConfigurator.setMaxBackupSize(50);
            logConfigurator.setImmediateFlush(true);
            logConfigurator.configure();
            return Logger.getLogger(context.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoggerException("Exception occurs!! " + e.getLocalizedMessage(), e);
        }
    }

    protected void onInfo(String TAG, String infoMessage) {
        if (logger != null) {
            TAG = TAG == null || TAG.isEmpty() ? DIRECTORY_NAME : TAG;
            infoMessage = infoMessage == null || infoMessage.isEmpty() ? NO_MSG : infoMessage;
            logger.info(MessageFormat.format("{0} information has -> {1} ", TAG.toUpperCase(), infoMessage));
        }
    }


    protected void onInfo(String TAG, String infoMessage, Throwable cause) {
        if (logger != null) {
            TAG = TAG == null || TAG.isEmpty() ? DIRECTORY_NAME : TAG;
            infoMessage = infoMessage == null || infoMessage.isEmpty()
                    ? cause.getLocalizedMessage() == null || cause.getLocalizedMessage().isEmpty()
                    ? NO_MSG : cause.getLocalizedMessage() : infoMessage;
            logger.info(MessageFormat.format("{0} information has -> {1} ", TAG.toUpperCase(), infoMessage), cause);
        }
    }


    protected void onDebug(String TAG, String debugMessage) {
        if (logger != null) {
            TAG = TAG == null || TAG.isEmpty() ? DIRECTORY_NAME : TAG;
            debugMessage = debugMessage == null || debugMessage.isEmpty() ? NO_MSG : debugMessage;
            logger.debug(MessageFormat.format("{0} debug Message has -> {1} ", TAG.toUpperCase(), debugMessage));
        }
    }


    protected void onDebug(String TAG, String debugMessage, Throwable cause) {
        if (logger != null) {
            TAG = TAG == null || TAG.isEmpty() ? DIRECTORY_NAME : TAG;
            debugMessage = debugMessage == null || debugMessage.isEmpty()
                    ? cause.getLocalizedMessage() == null || cause.getLocalizedMessage().isEmpty()
                    ? NO_MSG : cause.getLocalizedMessage() : debugMessage;
            logger.debug(MessageFormat.format("{0} debug Message has -> {1} ", TAG.toUpperCase(), debugMessage), cause);
        }
    }


    protected void onWarning(String TAG, String warningMessage) {
        if (logger != null) {
            TAG = TAG == null || TAG.isEmpty() ? DIRECTORY_NAME : TAG;
            warningMessage = warningMessage == null || warningMessage.isEmpty() ? NO_MSG : warningMessage;
            logger.warn(MessageFormat.format("{0} warning has -> {1} ", TAG.toUpperCase(), warningMessage));
        }
    }


    protected void onWarning(String TAG, String warningMessage, Throwable cause) {
        if (logger != null) {
            TAG = TAG == null || TAG.isEmpty() ? DIRECTORY_NAME : TAG;
            warningMessage = warningMessage == null || warningMessage.isEmpty()
                    ? cause.getLocalizedMessage() == null || cause.getLocalizedMessage().isEmpty()
                    ? NO_MSG : cause.getLocalizedMessage() : warningMessage;
            logger.warn(MessageFormat.format("{0} warning has -> {1} ", TAG.toUpperCase(), warningMessage), cause);
        }
    }


    protected void onError(String TAG, String errorMessage) {
        if (logger != null) {
            TAG = TAG == null || TAG.isEmpty() ? DIRECTORY_NAME : TAG;
            errorMessage = errorMessage == null || errorMessage.isEmpty() ? NO_MSG : errorMessage;
            logger.error(MessageFormat.format("{0} error has -> {1} ", TAG.toUpperCase(), errorMessage));
        }
    }


    protected void onError(String TAG, String errorMessage, Throwable cause) {
        if (logger != null) {
            errorMessage = errorMessage == null || errorMessage.isEmpty()
                    ? cause.getLocalizedMessage() == null || cause.getLocalizedMessage().isEmpty()
                    ? NO_MSG : cause.getLocalizedMessage() : errorMessage;
            logger.error(MessageFormat.format("{0} error has -> {1} ", TAG.toUpperCase(), errorMessage), cause);
        }
    }
}
