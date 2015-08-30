package com.bujok.locationapp.async;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.bujok.locationapp.backend.locationHistoryApi.LocationHistoryApi;
import com.bujok.locationapp.backend.locationHistoryApi.model.GeoPt;
import com.bujok.locationapp.backend.locationHistoryApi.model.LocationHistory;
import com.bujok.locationapp.helper.Constants;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Buje on 30/08/2015.
 */
public class sendLocationAsyncTask extends AsyncTask<Location, Void, Void> {

    private static LocationHistoryApi myApiService = null;
    private static final String TAG = "loc-app-sndLocASyncTask";


    @Override
    protected Void doInBackground(Location... params) {
        if (myApiService == null) {
            LocationHistoryApi.Builder builder = new LocationHistoryApi.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null
            )
                    .setRootUrl(Constants.BACKEND_URL_LOCAL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            LocationHistory locToInsert = new LocationHistory();
            locToInsert.setDeviceID("test");
            GeoPt coords = new GeoPt();
            coords.setLatitude((float) params[0].getLatitude());
            coords.setLongitude((float) params[0].getLongitude());
            locToInsert.setGeoPt(coords);
            locToInsert.setTimeStamp(params[0].getTime());
            myApiService.insert(locToInsert).execute();
            Log.i(TAG, "Location Successfully inserted");

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;

    }
}
