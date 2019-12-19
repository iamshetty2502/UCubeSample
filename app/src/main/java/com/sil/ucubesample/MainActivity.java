package com.sil.ucubesample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sil.ucubesdk.POJO.UCubeRequest;
import com.sil.ucubesdk.UCubeCallBacks;
import com.sil.ucubesdk.UCubeManager;
import com.sil.ucubesdk.payment.TransactionType;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LICENSEY_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectDevice();
            }
        });

    }

    void connectDevice() {
        UCubeManager uCubeManager = UCubeManager.getInstance(this, LICENSEY_KEY);
        UCubeRequest uCubeRequest = new UCubeRequest();

        uCubeRequest.setUsername("username");
        uCubeRequest.setPassword("password");
        uCubeRequest.setRefCompany("companyname");
        uCubeRequest.setMid("merchantId");
        uCubeRequest.setTid("TerminalId");
        uCubeRequest.setImei("IMEI");

        uCubeRequest.setImsi("IMSI");
        uCubeRequest.setTxn_amount("amount"); //e.g 100.00, 150.50
        uCubeRequest.setBt_address("Bluetooth_Mac_Address"); //eg xx:yy:zz:aa

        uCubeRequest.setRequestCode(TransactionType.DEBIT);

        uCubeManager.execute(uCubeRequest, new UCubeCallBacks() {
            @Override
            public void successCallback(JSONObject jsonObject) {
                Log.d(TAG, "successCallback: " + jsonObject);
            }

            @Override
            public void progressCallback(String s) {
                Log.d(TAG, "progressCallback: " + s);
            }

            @Override
            public void failureCallback(JSONObject jsonObject) {
                Log.d(TAG, "failureCallback: " + jsonObject);
            }
        });
    }
}
