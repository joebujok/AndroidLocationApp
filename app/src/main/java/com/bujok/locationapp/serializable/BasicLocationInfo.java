package com.bujok.locationapp.serializable;

import java.io.Serializable;

/**
 * Created by joebu on 09/09/2015.
 */
public class BasicLocationInfo implements Serializable{

    private long Time = 0;
    private double Latitude = 0.0;
    private double Longitude = 0.0;
    private float Accuracy = 0.0f;

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public float getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(float accuracy) {
        Accuracy = accuracy;
    }
}
