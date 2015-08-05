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
        name = "offerApi",
        version = "v1",
        resource = "offer",
        namespace = @ApiNamespace(
                ownerDomain = "backend.locationapp.bujok.com",
                ownerName = "backend.locationapp.bujok.com",
                packagePath = ""
        )
)
public class OfferEndpoint {

    private static final Logger logger = Logger.getLogger(OfferEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Offer.class);
    }

    /**
     * Returns the {@link Offer} with the corresponding ID.
     *
     * @param offerId the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Offer} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "offer/{offerId}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Offer get(@Named("offerId") Long offerId) throws NotFoundException {
        logger.info("Getting Offer with ID: " + offerId);
        Offer offer = ofy().load().type(Offer.class).id(offerId).now();
        if (offer == null) {
            throw new NotFoundException("Could not find Offer with ID: " + offerId);
        }
        return offer;
    }

    /**
     * Inserts a new {@code Offer}.
     */
    @ApiMethod(
            name = "insert",
            path = "offer",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Offer insert(Offer offer) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that offer.offerId has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(offer).now();
        logger.info("Created Offer.");

        return ofy().load().entity(offer).now();
    }

    /**
     * Updates an existing {@code Offer}.
     *
     * @param offerId the ID of the entity to be updated
     * @param offer   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code offerId} does not correspond to an existing
     *                           {@code Offer}
     */
    @ApiMethod(
            name = "update",
            path = "offer/{offerId}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Offer update(@Named("offerId") Long offerId, Offer offer) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(offerId);
        ofy().save().entity(offer).now();
        logger.info("Updated Offer: " + offer);
        return ofy().load().entity(offer).now();
    }

    /**
     * Deletes the specified {@code Offer}.
     *
     * @param offerId the ID of the entity to delete
     * @throws NotFoundException if the {@code offerId} does not correspond to an existing
     *                           {@code Offer}
     */
    @ApiMethod(
            name = "remove",
            path = "offer/{offerId}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("offerId") Long offerId) throws NotFoundException {
        checkExists(offerId);
        ofy().delete().type(Offer.class).id(offerId).now();
        logger.info("Deleted Offer with ID: " + offerId);
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
            path = "offer",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Offer> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Offer> query = ofy().load().type(Offer.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Offer> queryIterator = query.iterator();
        List<Offer> offerList = new ArrayList<Offer>(limit);
        while (queryIterator.hasNext()) {
            offerList.add(queryIterator.next());
        }
        return CollectionResponse.<Offer>builder().setItems(offerList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long offerId) throws NotFoundException {
        try {
            ofy().load().type(Offer.class).id(offerId).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Offer with ID: " + offerId);
        }
    }
}