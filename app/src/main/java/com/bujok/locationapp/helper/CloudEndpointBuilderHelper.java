package com.bujok.locationapp.helper;

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
 * Created by Buje on 05/08/2015.
 */
final class CloudEndpointBuilderHelper {
    /**
            * Default constructor, never called.
    */
    private CloudEndpointBuilderHelper() {
    }

    /**
     * *
     *
     * @return ShoppingAssistant endpoints to the GAE backend.
     */
  /**  static ShoppingAssistant getEndpoints() {

        // Create API handler
        ShoppingAssistant.Builder builder = new ShoppingAssistant.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), getRequestInitializer())
                .setRootUrl(Constants.ROOT_URL)
                .setGoogleClientRequestInitializer(
                        new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(
                                    final AbstractGoogleClientRequest<?>
                                            abstractGoogleClientRequest)
                                    throws IOException {
                                abstractGoogleClientRequest
                                        .setDisableGZipContent(true);
                            }
                        }
                );

        return builder.build();
    }

    /**
     * Returns appropriate HttpRequestInitializer depending whether the
     * application is configured to require users to be signed in or not.
     * @return an appropriate HttpRequestInitializer.
     */


}
