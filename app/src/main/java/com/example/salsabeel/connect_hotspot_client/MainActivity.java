package com.example.salsabeel.connect_hotspot_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button connectBtn,sendBtn;
    EditText ipEt,msgET;

    Socket toServer;
    OutputStream outputStream;
    DataOutputStream dataOutputStream;

    boolean isConnected = false;
    String message= "0";
    String ip = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectBtn =  (Button) findViewById(R.id.connectBtn);
        /* Uncomment this to send messages to server
        sendBtn = (Button) findViewById(R.id.sendBtn);
        msgET = (EditText) findViewById(R.id.msgET);
        */
        ipEt = (EditText) findViewById(R.id.ipET);


        connectBtn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ip = ipEt.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // make sure client is connected to server
                        while(true)
                        {
                            try{
                                toServer = new Socket(ip,8000);
                                isConnected = true;
                                break;
                            }
                            catch(Exception e){
                                Log.i("client","couldn't connect to server");
                            }
                        }

                        Log.i("client","connected to server");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Successfully Connected to Server",Toast.LENGTH_LONG).show();
                            }
                        });

                        /* Uncomment this to send messages to server

                        // get output stream
                        try {
                            outputStream = toServer.getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        dataOutputStream = new DataOutputStream(outputStream);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                connectBtn.setEnabled(false);
                                sendBtn.setEnabled(true);
                            }
                        });
                        */

                    }
                }).start();
            }
        });

        /* Uncomment this to send messages to server
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = msgET.getText().toString();
                Log.i("client: ", "sending message " + message);
                try {
                    dataOutputStream.writeUTF(message);
                } catch (IOException e) {
                    Log.i("client: ", "data stream not set");
                }

            }
        });
        */

    }
}
