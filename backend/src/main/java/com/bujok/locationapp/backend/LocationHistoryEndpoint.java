package com.bujok.locationapp.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "locationHistoryApi",
        version = "v1",
        resource = "locationHistory",
        namespace = @ApiNamespace(
                ownerDomain = "backend.locationapp.bujok.com",
                ownerName = "backend.locationapp.bujok.com",
                packagePath = ""
        )
)
public class LocationHistoryEndpoint {

    private static final Logger logger = Logger.getLogger(LocationHistoryEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(LocationHistory.class);
    }

    /**
     * Returns the {@link LocationHistory} with the corresponding ID.
     *
     * @param locationHistoryId the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code LocationHistory} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "locationHistory/{locationHistoryId}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public LocationHistory get(@Named("locationHistoryId") Long locationHistoryId) throws NotFoundException {
        logger.info("Getting LocationHistory with ID: " + locationHistoryId);
        LocationHistory locationHistory = ofy().load().type(LocationHistory.class).id(locationHistoryId).now();
        if (locationHistory == null) {
            throw new NotFoundException("Could not find LocationHistory with ID: " + locationHistoryId);
        }
        return locationHistory;
    }

    /**
     * Inserts a new {@code LocationHistory}.
     */
    @ApiMethod(
            name = "insert",
            path = "locationHistory",
            httpMethod = ApiMethod.HttpMethod.POST)
    public LocationHistory insert(LocationHistory locationHistory) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that locationHistory.locationHistoryId has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(locationHistory).now();
        logger.info("Created LocationHistory.");

        return ofy().load().entity(locationHistory).now();
    }

    /**
     * Updates an existing {@code LocationHistory}.
     *
     * @param locationHistoryId the ID of the entity to be updated
     * @param locationHistory   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code locationHistoryId} does not correspond to an existing
     *                           {@code LocationHistory}
     */
    @ApiMethod(
            name = "update",
            path = "locationHistory/{locationHistoryId}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public LocationHistory update(@Named("locationHistoryId") Long locationHistoryId, LocationHistory locationHistory) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(locationHistoryId);
        ofy().save().entity(locationHistory).now();
        logger.info("Updated LocationHistory: " + locationHistory);
        return ofy().load().entity(locationHistory).now();
    }

    /**
     * Deletes the specified {@code LocationHistory}.
     *
     * @param locationHistoryId the ID of the entity to delete
     * @throws NotFoundException if the {@code locationHistoryId} does not correspond to an existing
     *                           {@code LocationHistory}
     */
    @ApiMethod(
            name = "remove",
            path = "locationHistory/{locationHistoryId}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("locationHistoryId") Long locationHistoryId) throws NotFoundException {
        checkExists(locationHistoryId);
        ofy().delete().type(LocationHistory.class).id(locationHistoryId).now();
        logger.info("Deleted LocationHistory with ID: " + locationHistoryId);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "locationHistory",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<LocationHistory> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<LocationHistory> query = ofy().load().type(LocationHistory.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<LocationHistory> queryIterator = query.iterator();
        List<LocationHistory> locationHistoryList = new ArrayList<LocationHistory>(limit);
        while (queryIterator.hasNext()) {
            locationHistoryList.add(queryIterator.next());
        }
        return CollectionResponse.<LocationHistory>builder().setItems(locationHistoryList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long locationHistoryId) throws NotFoundException {
        try {
            ofy().load().type(LocationHistory.class).id(locationHistoryId).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find LocationHistory with ID: " + locationHistoryId);
        }
    }
}