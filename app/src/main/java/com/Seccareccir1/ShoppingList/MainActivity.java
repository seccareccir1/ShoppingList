package com.Seccareccir1.ShoppingList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    Button button1;
    Button button2;
    Button button3;
    EditText mEdit;
    private String myFile = "list.lis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, SecondActivity.class);
                startActivity(intent);
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdit   = (EditText)findViewById(R.id.editText1);
                String temp = mEdit.getText().toString();
                if(temp == null){
                    return;
                }else{
                    writeToFile(temp);
                    mEdit.setText("");
                }

            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(myFile);
                boolean deleted = file.delete();
                if (deleted){
                    Log.e(TAG,"File was deleted ");
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void writeToFile(String data)
        {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(myFile, Context.MODE_APPEND));
                outputStreamWriter.write(data); // the scan result
                outputStreamWriter.append("\n\r");
                outputStreamWriter.close();
                Toast t = Toast.makeText(this, data + " - Has Been Added", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP,0,0);
                t.show();
            }
            catch (IOException e)
            {
                Log.e(TAG,"File write failed: " + e.toString());
            }
        }

}
