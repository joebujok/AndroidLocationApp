package com.bujok.locationapp.backend;

/**
 * Created by Buje on 03/08/2015.
 */



        import com.googlecode.objectify.annotation.Entity;
        import com.googlecode.objectify.annotation.Id;

/**
 * Offer entity.
 */
@Entity
public class Offer {

    /**
     * Unique identifier of this Entity in the database.
     */
    @Id
    private Long offerId;

    /**
     * The offer title.
     */
    private String title;


    /**
     * Returns the unique identifier of this offer.
     * @return the unique identifier associated to this offer.
     */
    public final Long getOfferID() {
        return offerId;
    }

    /**
     * Sets the unique identifier of this offer.
     * @param offerID the unique identifier to associate to this offer.
     */
    public final void setOfferID(final Long offerID) {
        this.offerId = offerID;
    }

    /**
     * Returns the offer title.
     * @return The offer title.
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Sets the offer title.
     * @param pTitle The title to set for this offer.
     */
    public final void setTitle(final String pTitle) {
        this.title = pTitle;
    }



}