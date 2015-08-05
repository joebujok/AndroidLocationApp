package com.bujok.locationapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import com.bujok.locationapp.backend.offerApi.OfferApi;
import com.bujok.locationapp.backend.offerApi.model.Offer;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Buje on 03/08/2015.
 */
public class MessageAsyncTask extends AsyncTask<Void, Void, List<Offer>>  {

    private static OfferApi myApiService = null;
    private Context context;

    MessageAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<Offer> doInBackground(Void... params) {
        if(myApiService == null) { // Only do this once
            OfferApi.Builder builder = new OfferApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.10:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
// end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.list().execute().getItems();
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }

    }
    @Override
    protected void onPostExecute(List<Offer> result) {
        for (Offer q : result) {
            Toast.makeText(context, q.getOfferID() + " : " + q.getTitle(), Toast.LENGTH_LONG).show();
        }
    }
}
