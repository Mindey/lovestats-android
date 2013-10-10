package com.example.lovestats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button) findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	// 按『Send』按钮的之后执行的部分
            	Integer timestamp = (int) (System.currentTimeMillis() / 1000L);
            	String message = ((EditText) findViewById(R.id.edit_message)).getText().toString();
            	TextView textView = (TextView)findViewById(R.id.text_view);
        	    textView.setText("成功：一下个信息在 /mnt/sdcard/Android/data/com.dropbox.android/files/scratch/mi.body.txt 文件里被记录了：『" + message + "』");
        	    
        	    // 记录（附加）信息到一个文件        	    
        	    File sdcard = Environment.getExternalStorageDirectory();
        	    File file = new File(sdcard,"/Android/data/com.dropbox.android/files/scratch/mi.body.txt");
        	    
        	    try {
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.append(timestamp.toString() + " " + message);
                    bw.newLine();
                    fw.flush();
                    bw.close();
                    // 删除在界面的信息
                    ((EditText) findViewById(R.id.edit_message)).setText("");
                } 
        	    catch (IOException e) {
        	    	Log.e("TAG", "Could not write file " + e.getMessage());
        	    }
               
        	    
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    
    
}
