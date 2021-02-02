package com.example.androidfundamentals.week9;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class InternalStorage {

    private static final String TAG = "InternalStorage";

    public void writeInternal(@NonNull Context context, String fileName) {
        String fileContent = "Android Fundamentals";

        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContent.getBytes());
        } catch (Exception e) {
            Log.e(TAG, "writeInternal: ", e);
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch(IOException e) {
                    Log.e(TAG, "writeInternal: ", e);
                }
            }
        }
    }

    public String readInternal(@NonNull Context context, String fileName) throws IOException{
        FileInputStream inputStream = null;
        inputStream = context.openFileInput(fileName);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] inputBuffer = new char[200];
        StringBuffer stringBuffer = new StringBuffer();
        int charRead;
        while((charRead = inputStreamReader.read(inputBuffer)) > 0) {
            stringBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
        }
        inputStreamReader.close();

        return stringBuffer.toString();
    }
}
