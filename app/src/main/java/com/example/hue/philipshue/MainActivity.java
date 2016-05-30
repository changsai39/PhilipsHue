package com.example.hue.philipshue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.hue.sdk.heartbeat.PHHeartbeatManager;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHHueParsingError;

import java.util.List;

//Connect to bridge here
public class MainActivity extends Activity {

    PHHueSDK phHueSDK;
    private PHSDKListener listener;
    lightContainer container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = inflater.inflate(R.layout.activity_main, null);
        setContentView(vi);

        TextView status = (TextView) findViewById(R.id.status);

        phHueSDK = PHHueSDK.getInstance();
        //phHueSDK.setAppName("Philips Hue");
        phHueSDK.setDeviceName(android.os.Build.MODEL);

        listener = new myListener(phHueSDK, status);

        // Register the PHSDKListener to receive callbacks from the bridge.
        phHueSDK.getNotificationManager().registerSDKListener(listener);

        //Store connection here
        /*
        SharedPreferences lastState = getSharedPreferences("statePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = lastState.edit();
        if(!lastState.contains("IPAddress") || !lastState.contains("Username")) {
            //Get IPAddress and Usernames
            bridgeSearch.getPortalAddress();
        }
        editor.commit();
        PHAccessPoint accessPoint = new PHAccessPoint();
        accessPoint.setIpAddress(lastState.getString("IPAddress", "String"));
        accessPoint.setUsername(lastState.getString("Username", "String"));
        phHueSDK.connect(accessPoint);
        */

        Button next = (Button) findViewById(R.id.next);
        assert next != null;
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bundle bun = new Bundle();
                bun.putParcelable("container", container);
                Intent next = new Intent(MainActivity.this, SelectActivity.class);
                next.putExtra(bun);
                startActivity(next);
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();

        //Disable the heartbeat when exiting your app
        phHueSDK.disableAllHeartbeat();
    }
}