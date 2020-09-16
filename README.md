# SaveLogsInStorage
If you want to save logs in your local storage and access or check logs when the application is release or production mode then you can check your logs from storage.

[![](https://jitpack.io/v/abhi10jul/SaveLogsInStorage.svg)](https://jitpack.io/#abhi10jul/SaveLogsInStorage)

Step 1. Add the JitPack repository to your build file


	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
  
  Step 2. Add the dependency
  
  
  	dependencies {
	        implementation 'com.github.abhi10jul:SaveLogsInStorage:1.0.1'
	}
  
  
Step 3. Add these variables in activity or fragment gloabally 
	  
	private static final int PERMISSION_REQUEST_CODE = 101;
	public SaveLogsInStorage saveLoggerInstance;
	public static final String directoryName = "CustomLogger";
	  
Optional: You can also declare directoryName in constant file or static variable because If you want to add like this you can easily use same parameter in SaveLogsInStorage constructor

Step 4. ask write storage permission to user 

	private boolean askWriteStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (ContextCompat.checkSelfPermission(LauchingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }
    
 Step 5. Get onRequestPermissionsResult
 
     @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("value", "Permission Granted, Now you can use save logs in local drive .");
                initialisedLogger();
            } else {
                Log.e("value", "Permission Denied, You cannot use  save logs in local drive .");
                Toast.makeText(LauchingActivity.this, "Write External Storage permission allows us to do store logs in local storage. Please allow this permission 		in App Settings.", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    
  Step 6: initiliase logger
  
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
	  
	
<h2 id="examples">Examples :eyes:</h2>
Link [![](https://github.com/abhi10jul/SaveLogsInStorage/blob/master/app/src/main/java/com/blogspot/abhiandroidknowledge/customlogger/LauchingActivity.java)]

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
	
	

<h2 id="creators">Special Thanks to ALL :heart:</h2>
