package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    static final UUID mUUID= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        StringBuffer sb = new StringBuffer();
        String line = "233/";
        int c=5;
        while(c>0){
            sb.append(line);
            sb.append("\n");
            c--;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ((TextView)findViewById(R.id.text1)).setText(sb.toString());
        */

        StringBuffer sb = new StringBuffer();

        BluetoothAdapter btAdapter= BluetoothAdapter.getDefaultAdapter();

        //System.out.println(btAdapter.getBondedDevices());
        sb.append(btAdapter.getBondedDevices());
        sb.append("\n");
        ((TextView)findViewById(R.id.text1)).setText(sb.toString());

        BluetoothDevice hc05=btAdapter.getRemoteDevice("00:14:03:05:08:56");

        //System.out.println(hc05.getName());
        sb.append(hc05.getName());
        sb.append("\n");
        ((TextView)findViewById(R.id.text1)).setText(sb.toString());

        BluetoothSocket btSocket=null;
        int counter=0;


        do{
            try{
                btSocket=hc05.createRfcommSocketToServiceRecord(mUUID);

                //System.out.println(btSocket);
                sb.append(btSocket);
                sb.append("\n");
                ((TextView)findViewById(R.id.text1)).setText(sb.toString());

                btSocket.connect();


                //System.out.println(btSocket.isConnected());
                sb.append(btSocket.isConnected());
                sb.append("\n");
                ((TextView)findViewById(R.id.text1)).setText(sb.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;
        } while(!btSocket.isConnected()&&counter<3);

        if(btSocket.isConnected()){
            InputStream inputStream=null;
            //do {
                try {
                    inputStream = btSocket.getInputStream();
                    inputStream.skip(inputStream.available());

                    sb.append((byte)inputStream.read());
                    sb.append("\n");
                    ((TextView)findViewById(R.id.text1)).setText(sb.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            //}while(btSocket.isConnected());
        }






    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}