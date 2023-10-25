package com.amilaabeygunasekara.nativebarcodetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button startButton = (Button)findViewById(R.id.activty_config_startButton);
        startButton.setOnClickListener(v -> {
            System.out.println("---------------------------------test------------------------");
            EditText ipAddressEditText = (EditText)findViewById(R.id.activty_config_ipAddressEditText);
            String ipAddress = ipAddressEditText.getText().toString();
            EditText portEditText = (EditText)findViewById(R.id.activty_config_portEditText);
            int port = Integer.parseInt(portEditText.getText().toString());

            Pattern mPattern = Pattern.compile("(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}");

            Matcher matcher = mPattern.matcher(ipAddress);
            if(!matcher.find()) {
                ipAddressEditText.setText("192.168.142.197"); // Don't know what to place
                Toast.makeText(ConfigActivity.this,"Veuillez entrer une adresse IP valide", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ConfigActivity.this,"Lancement en cours", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ConfigActivity.this, MainActivity.class);
                intent.putExtra("ipAddress", ipAddress);
                intent.putExtra("port", port);
                startActivity(intent);
            }
        });
    }
}