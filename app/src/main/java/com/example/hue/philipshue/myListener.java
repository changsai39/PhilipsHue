package com.example.hue.philipshue;

import android.content.Intent;
import android.widget.TextView;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHMessageType;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHHueParsingError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sai Chang on 5/25/2016.
 */
class myListener implements PHSDKListener {
    PHHueSDK phHueSDK;
    PHBridgeResourcesCache cache;
    List<PHBridge> bridges;
    PHBridgeSearchManager bridgeSearch;
    TextView status;
    lightContainer container;

    public myListener(PHHueSDK sdk, TextView status) {
        super();

        phHueSDK = sdk;
        this.status = status;
        bridgeSearch = (PHBridgeSearchManager) phHueSDK.getSDKService(PHHueSDK.SEARCH_BRIDGE);
        bridgeSearch.search(true, true);

        status.setText("Listener created");
    }

    @Override
    public void onAccessPointsFound(List accessPoint) {
        // Handle your bridge search results here.  Typically if multiple results are returned you will want to display them in a list
        // and let the user select their bridge.   If one is found you may opt to connect automatically to that bridge.

        bridges = accessPoint;
        if(bridges.size() == 1) {
            phHueSDK.setSelectedBridge(bridges.get(0));

            container = new lightContainer(phHueSDK);

            //startActivity(new Intent(EbinaActivity.this, TachibanaActivity.class));
        } else {
            // Manage what happens when there are multiple locations going
        }
    }

    @Override
    public void onCacheUpdated(List cacheNotificationsList, PHBridge bridge) {
        // Here you receive notifications that the BridgeResource Cache was updated. Use the PHMessageType to
        // check which cache was updated, e.g.
        if (cacheNotificationsList.contains(PHMessageType.LIGHTS_CACHE_UPDATED)) {
            status.setText("Lights Cache Updated ");
        }
    }

    @Override
    public void onBridgeConnected(PHBridge bridge, String username) {
        phHueSDK.setSelectedBridge(bridge);
        phHueSDK.enableHeartbeat(bridge, PHHueSDK.HB_INTERVAL);
        // Here it is recommended to set your connected bridge in your sdk object (as above) and start the heartbeat.
        // At this point you are connected to a bridge so you should pass control to your main program/activity.
        // The username is generated randomly by the bridge.
        // Also it is recommended you store the connected IP Address/ Username in your app here.  This will allow easy automatic connection on subsequent use.

        cache = bridge.getResourceCache();

        status.setText("Connected to bridge");
    }

    @Override
    public void onAuthenticationRequired(PHAccessPoint accessPoint) {
        phHueSDK.startPushlinkAuthentication(accessPoint);
        // Arriving here indicates that Pushlinking is required (to prove the User has physical access to the bridge).  Typically here
        // you will display a pushlink image (with a timer) indicating to to the user they need to push the button on their bridge within 30 seconds.
    }

    @Override
    public void onConnectionResumed(PHBridge bridge) {
        // In case we lose connection
    }

    @Override
    public void onConnectionLost(PHAccessPoint accessPoint) {
        // Here you would handle the loss of connection to your bridge.
    }

    @Override
    public void onError(int code, final String message) {
        // Here you can handle events such as Bridge Not Responding, Authentication Failed and Bridge Not Found
    }

    @Override
    public void onParsingErrors(List parsingErrorsList) {
        // Any JSON parsing errors are returned here.  Typically your program should never return these.
    }
}

