package com.bujok.locationapp.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.bujok.locationapp.serializable.BasicLocationInfo;
import com.google.android.gms.location.FusedLocationProviderApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LocationService extends IntentService {

    private final String TAG = "loc-app-LocService";
    private String FILENAME = "locationData";

    Location location;

    public LocationService() {

        super("LocationUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent.hasExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED)) {

            location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);

            Log.d(TAG, "time: " + location.getTime() + "accuracy: " + location.getAccuracy() + " lat: " + location.getLatitude() + " lon: " + location.getLongitude());

            BasicLocationInfo LocData = new BasicLocationInfo();
            LocData.setAccuracy(location.getAccuracy());
            LocData.setLatitude(location.getLatitude());
            LocData.setLongitude(location.getLongitude());
            LocData.setTime(location.getTime());
            // get existing object if it exists
            List<BasicLocationInfo> LocDataList = new ArrayList<>();

            try{
                FileInputStream fis = openFileInput(FILENAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                //Todo fix this issue, the stored data is not in a list format so the cast doesnt work. i think....

                LocDataList = (List<BasicLocationInfo>) ois.readObject();
                ois.close();
                fis.close();
            }
            catch (IOException e){
                Log.e(TAG, e.getMessage());
            }
            catch (ClassNotFoundException e){
                Log.e(TAG, e.getMessage());
            }
            LocDataList.add(LocData);

            try {
                FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(LocDataList);
                oos.close();
                fos.close();
                File dir = getFilesDir();
                File file = new File(dir,FILENAME);
                Log.i(TAG,"Object successfully serialised to disk. File size = " + file.length() + " bytes");
            }
            catch (IOException e){
                Log.e(TAG, e.getMessage());
            }
            finally {
                // TODO: JOE BUJOK - Figure out the best practice to close the streams so they get closed if there is an error.
               // oos.close();
               // fos.close();
            }

        }
    }
}