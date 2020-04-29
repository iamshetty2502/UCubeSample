# UCubeSample
This is a sample app for UCube SDK.

### Add the below line of code in your project's root level gradle.
    maven {
    url 'https://dl.bintray.com/saraswatinfotech/com.sil.ucubesdk'
    }

 e.g 
 ```
 allprojects { 
  repositories {
          google()
          jcenter()
          maven { url 'https://dl.bintray.com/saraswatinfotech/com.sil.ucubesdk'}
        }
  }
  ```
### Add the below dependencies in the project's build.gradle 
    implementation 'com.sil.ucubesdk:ucubesdk:2.0.1'
e.g
```
    dependencies {
		implementation 'com.sil.ucubesdk:ucubesdk:2.0.1'
	}
 ```

### Add the below permission in the manifest file 

    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE " />

### Implementation
1. Initialize the UCubeManager with the context and your provided License-Key.
e.g
```
UCubeManager uCubeManager = UCubeManager.getInstance(Context,License-key);
```
2. Create an Object of UCubeRequest and set the respective fields:
```
  UCubeRequest uCubeRequest = new UCubeRequest();
    uCubeRequest.setUsername(username);
    uCubeRequest.setPassword(password);
    uCubeRequest.setRefCompany(your_org_name);
    CubeRequest.setMid(Merchant_Id);
    uCubeRequest.setTid(Termianl_Id);
    uCubeRequest.setTransactionId(Transaction_Id);
    uCubeRequest.setImei(Device_Imei);
    uCubeRequest.setImsi(Device_Imsi);
    uCubeRequest.setTxn_amount(amount);
    uCubeRequest.setBt_address(Ucube_device_Mac_Id);
    uCubeRequest.setRequestCode(TransactionType); 
```
Provided Transaction type are as follows:
```
    	TransactionType.INQUIRY 	: for balance enquiry
    	TransactionType.WITHDRAWAL	: for amount withdrawal
    	TransactionType.DEBIT		: for sale by card.
```
Transaction Id is your unique 12 digit Id. In case you dont have any such Unique Id , you can call the below method to generate the Unique Transaction Id.<br/>
note: The Transaction Id is returned in the Response Callback as token.
```
uCubeManager.getTransactionId()
```
3. Once the Ucuberequest object is created and set proceed with the excecution.
e.g 
```
	uCubeManager.execute(uCubeRequest,new UCubeCallBacks() {
            @Override
            public void successCallback(JSONObject jsonObject) {
                
            }

            @Override
            public void progressCallback(String s) {
              
            }

            @Override
            public void failureCallback(JSONObject jsonObject) {
               
            }
        });
```
4. UCubeCallBacks implement the method for success, failure and progress state.
	
	i. 	**public void successCallback(JSONObject jsonObject);** <br>
			The above method is called when the transaction is success. 

 	ii. **public void failureCallback(JSONObject jsonObject);** <br>
 			The above method is called when the transaction is failed due to some issues.

 	iii. **public void progressCallback(String message)** <br>
 			The above method is called to show the message about the current status. This message can be displayed to the end-user refelcting the state of Ucube Device.
5. The UCubeManager also provide service to check the status of your transaction. To check the transaction status use below method

```
     uCubeManager.checkStatus(uCubeRequest, new StatusCallBack() {
            @Override
            public void successCallback(JSONObject jsonObject) {
              
            }

            @Override
            public void failureCallback(JSONObject jsonObject) {
            
            }
        });
```
note : Check status method is applicable only for transaction type TransactionType.WITHDRAWAL and/or TransactionType.DEBIT
