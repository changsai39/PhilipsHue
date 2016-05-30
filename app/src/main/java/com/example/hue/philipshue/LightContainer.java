package com.example.hue.philipshue;

import android.os.Parcel;
import android.os.Parcelable;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;

import java.util.List;
import java.util.Map;

/**
 * Created by Sai Chang on 5/29/2016.
 */
public class lightContainer implements Parcelable {
    PHHueSDK hueSDK;
    PHBridge bridge;
    //List<PHLight> lights;
    Map<Integer, List<PHLight>> groups;

    public void LightContainer(PHHueSDK sdk) {
        hueSDK = sdk;
        bridge = sdk.getSelectedBridge();
    }

    private int mData;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    public static final Parcelable.Creator<LightContainer> CREATOR
            = new Parcelable.Creator<LightContainer>() {
        public LightContainer createFromParcel(Parcel in) {
            return new LightContainer(in);
        }

        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };
}
