package com.github.abhi10jul.savelogs;

import android.annotation.SuppressLint;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author abhishek.
 */
class PathConstant {
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static Date now = new Date();
    private static String fileName = formatter.format(now);
    private static String LOGGER_FILE_NAME = fileName + "_file.txt";
    private static final String DEFAULT_PATH = Environment.getExternalStorageDirectory().toString() + File.separator;
    private static final String DIRECTORY_PATH = DEFAULT_PATH + CLogger.DIRECTORY_NAME + File.separator;
    protected static final String LOGGER_FILE_PATH = DIRECTORY_PATH + LOGGER_FILE_NAME;
}
