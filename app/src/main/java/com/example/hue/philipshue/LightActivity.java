package com.example.hue.philipshue;

import android.os.Bundle;

import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.List;

/**
 * Created by Sai Chang on 5/30/2016.
 */
public class LightActivity {

    protected void onCreate(Bundle savedInstanceState) {
        ;
    }

    public void colorGroup(List<PHLight> group, int color) {
        PHBridge bridge = container.bridge;

        for (PHLight light : group) {
            PHLightState lightState = new PHLightState();
            lightState.setHue(color);
            bridge.updateLightState(light, lightState);
        }
    }
}
