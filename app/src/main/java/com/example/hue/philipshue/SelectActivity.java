package com.example.hue.philipshue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.List;
import java.util.Random;

/**
 * Created by Sai Chang on 5/30/2016.
 */
public class SelectActivity {

    LightContainer container;

    protected void onCreate(Bundle savedInstanceState) {


        Button next = (Button) findViewById(R.id.next);
        assert next != null;
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                for(int i = 0; i < max; i++) {
                    container.groups;
                }

                Bundle bun = new Bundle();
                bun.putParcelable("container", container);
                Intent next = new Intent();
                next
            }
        });
    }


}
