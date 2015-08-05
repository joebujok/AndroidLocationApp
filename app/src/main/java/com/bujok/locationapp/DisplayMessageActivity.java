package com.bujok.locationapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bujok.locationapp.backend.offerApi.OfferApi;
import com.bujok.locationapp.backend.offerApi.model.Offer;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.Collections;


public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this) ;
        textView.setTextSize(40);
        textView.setText(message);
        setContentView(textView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new SendMessage().execute(message);

        // If your minSdkVersion is 11 or higher, instead use:
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        readEndpointTest();

    }

    public void readEndpointTest(){
        new MessageAsyncTask(this).execute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class SendMessage extends AsyncTask<String,Void,Boolean>{

        private OfferApi myApiService = null;
        private Context context;

        @Override
        protected Boolean doInBackground(String... params) {
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
                Offer offerToInsert = new Offer();
                offerToInsert.setTitle(params[0]);
                myApiService.insert(offerToInsert).execute();
                return true;
            } catch (IOException e) {
                return false;
            }

        }
    }
}
