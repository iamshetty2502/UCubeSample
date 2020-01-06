package com.sil.ucubesample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sil.ucubesdk.POJO.UCubeRequest;
import com.sil.ucubesdk.StatusCallBack;
import com.sil.ucubesdk.UCubeCallBacks;
import com.sil.ucubesdk.UCubeManager;
import com.sil.ucubesdk.payment.TransactionType;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String LICENSEY_KEY = "";
    UCubeManager uCubeManager;
    UCubeRequest uCubeRequest;
    Button tranxBtn,statusBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tranxBtn = findViewById(R.id.transaction_button);
        statusBtn = findViewById(R.id.status_button);

        uCubeManager = UCubeManager.getInstance(this, LICENSEY_KEY);

        uCubeRequest = new UCubeRequest();

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

        tranxBtn.setOnClickListener(this);
        statusBtn.setOnClickListener(this);
        tranxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    void connectDevice() {

        uCubeRequest.setTransactionId(uCubeManager.getTransactionId()); //you can have your own 12digit integer Id or create one using UCubeManager;

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.transaction_button: connectDevice();break;
            case R.id.status_button: checkStatus();break;
        }
    }

    //Check status is applicable only for TransactionType.DEBIT and TransactionType.WITHDRAWAL
    private void checkStatus() {

        uCubeManager.checkStatus(uCubeRequest, new StatusCallBack() {
            @Override
            public void successCallback(JSONObject jsonObject) {
                Log.d(TAG, "checkStatus successCallback: " + jsonObject);
            }

            @Override
            public void failureCallback(JSONObject jsonObject) {
                Log.d(TAG, "checkStatus failureCallback: " + jsonObject);
            }
        });
    }
}
