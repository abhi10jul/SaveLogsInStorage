package com.blogspot.abhiandroidknowledge.customlogger;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.abhi10jul.savelogs.SaveLogsInStorage;

/**
 * @author abhishek
 */
public class LauchingActivity extends AppCompatActivity {
    private static final String TAG = LauchingActivity.class.getSimpleName();
    private static final int PERMISSION_REQUEST_CODE = 101;
    public SaveLogsInStorage saveLoggerInstance;
    public static final String directoryName = "CustomLogger";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (askWriteStoragePermission()) {
            initialisedLogger();
        } else {
            requestPermission();
        }
    }

    private void initialisedLogger() {
        /*
         * First initialised SaveLogsInStorage in variable
         * to access all save logs instance method
         */
        saveLoggerInstance = SaveLogsInStorage.getSaveLoggerInstance(this, directoryName);
        /*
         *Explain how to use all save logs instance method
         */
        printLogsInStorage();
    }

    private void printLogsInStorage() {
        Log.i(TAG, "save info logs");
        saveLoggerInstance.saveInfoLogs(TAG, "successfully saved info logs");
        Log.d(TAG, "save debug logs");
        saveLoggerInstance.saveDebugLogs(TAG, "successfully saved debug logs");
        Log.w(TAG, "save warning logs");
        saveLoggerInstance.saveWarningLogs(TAG, "successfully warnings info logs");
        Log.e(TAG, "save error logs");
        saveLoggerInstance.saveErrorLogs(TAG, "successfully saved error logs");
        try {
            Log.e(TAG, "save error logs with exception");
            //  int dividedZero = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            saveLoggerInstance.saveErrorLogs(TAG, "successfully saved error logs", e);
        }
    }


    private boolean askWriteStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (ContextCompat.checkSelfPermission(LauchingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("value", "Permission Granted, Now you can use save logs in local drive .");
                initialisedLogger();
            } else {
                Log.e("value", "Permission Denied, You cannot use  save logs in local drive .");
                Toast.makeText(LauchingActivity.this, "Write External Storage permission allows us to do store logs in local storage. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
