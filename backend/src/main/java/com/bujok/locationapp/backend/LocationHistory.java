package com.bujok.locationapp.backend;

/**
 * Created by Buje on 03/08/2015.
 */



import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * LocationHistory entity.
 */
@Entity
public class LocationHistory {

    /**
     * Unique identifier of this Entity in the database.
     */
    @Id
    private Long locationHistoryId;

    /**
     * The device that hte location history came from.
     */
    private String deviceID;

    /**
     * The coordinates of the location history .
     */
    private GeoPt latLng;

    /**
     * The time stamp coordinates of the location history .
     */
    private long timeStamp;

    /**
     * Returns the unique identifier of this locationHistory.
     * @return the unique identifier associated to this locationHistory.
     */
    public final Long getLocationHistoryID() {
        return locationHistoryId;
    }

    /**
     * Sets the unique identifier of this locationHistory.
     * @param locationHistoryID the unique identifier to associate to this locationHistory.
     */
    public final void setLocationHistoryID(final Long locationHistoryID) {
        this.locationHistoryId = locationHistoryID;
    }

    /**
     * Returns the locationHistory deviceID.
     * @return The locationHistory deviceID.
     */
    public final String getDeviceID() {
        return deviceID;
    }

    /**
     * Sets the locationHistory deviceID.
     * @param pDeviceID The deviceID to set for this locationHistory.
     */
    public final void setDeviceID(final String pDeviceID) {
        this.deviceID = pDeviceID;
    }

    /**
     * Returns the locationHistory latitude and longitude.
     * @return The locationHistory latitude and longitude.
     */
    public final GeoPt getLatLng() {
        return latLng;
    }

    /**
     * Sets the locationHistory latitude and longitude.
     * @param pLatLng The latitude and longitude to set for this locationHistory.
     */
    public final void setGeoPt(final GeoPt pLatLng) {
        this.latLng = pLatLng;
    }

    /**
     * Returns the locationHistory timestamp.
     * @return The locationHistory timestamp.
     */
    public final long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the locationHistory timestamp.
     * @param pTimeStamp The timestamp to set for this locationHistory.
     */
    public final void setTimeStamp(final long pTimeStamp) {
        this.timeStamp = pTimeStamp;
    }
}