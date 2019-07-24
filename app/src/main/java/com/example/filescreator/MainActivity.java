package com.example.filescreator;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Context context = MainActivity.this;
    EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_savedata);

    }


    public void message(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void saveInternalCache(View view)
    {
        String data = editText.getText().toString();
        File folder = getCacheDir();
        File myFile = new File(folder, "intCache.txt");
        writeData(myFile,data);
    }

    public void saveExternalCache(View view)
    {
        String data = editText.getText().toString();
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "extCache.txt");
        writeData(myFile,data);
    }

    public void savePrivateExternalFile(View view)
    {
        String data = editText.getText().toString();
        File folder = getExternalFilesDir("XYZ");
        File myFile = new File(folder, "privateExternal.txt");
        writeData(myFile,data);
    }

    public void savePublicExternalFile(View view)
    {
        String data = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "publicExternal.txt");
        writeData(myFile,data);

    }


    private void writeData(File myFile, String data)
    {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());

            message(data + " Saved " +myFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fileOutputStream!=null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}