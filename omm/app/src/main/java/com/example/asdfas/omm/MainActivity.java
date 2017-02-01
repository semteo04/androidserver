package com.example.asdfas.omm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button button01;
    EditText input01;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input01=(EditText)findViewById(R.id.input01);
        button01=(Button)findViewById(R.id.button01);
    }
    public void bb(View v){
        String addr=input01.getText().toString().trim();
        ConnectThread thread=new ConnectThread(addr);
    }
    class ConnectThread extends Thread{
        String hostname;
        public ConnectThread(String addr){
            hostname=addr;
        }
        public void run(){
            try {
                int port=10001;
                Socket sock= null;
                sock = new Socket(hostname,port);
                ObjectOutputStream outstream=new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject("Hello AndroidTown on Android");
                outstream.flush();
                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                String obj = (String)instream.readObject();
                Log.d("MainActivity","서버에서 받은 메세지 : "+obj);
                sock.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
