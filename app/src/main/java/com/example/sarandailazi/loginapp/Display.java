package com.example.sarandailazi.loginapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by Saranda Ilazi on 12/09/2017.
 */
public class Display extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);
    }
}
