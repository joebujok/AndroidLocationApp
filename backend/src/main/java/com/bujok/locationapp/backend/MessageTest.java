package com.bujok.locationapp.backend;

/**
 * Created by Buje on 03/08/2015.
 */


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Date;


/**
 * CheckIn entity used to represent information about customers checking into
 * * places.
 */
@Entity
public class MessageTest {

    /**
     * Unique identifier of this Entity in the database.
     */
    @Id
    private Long key;

    /**
     * The users message.
     */
    private String userMessage;

    /**
     *
     * @return the unique identifier of this Entity.
     */
    public final Long getIDKey() {
        return key;
    }

    /**
     * Resets the Entity key to null.
     */
    public final void clearKey() {
        key = null;
    }


   /**
     * Returns the email of the user that checked in.
     * @return the email of the user.
     */
    public final String getUserMessage() {
        return userMessage;
    }

    /**
     * Sets the email of the user that is checking in.
     * @param puserMessage the email of the user that is checking in at this
     *      place.
     */
    public final void setUserMessage(final String puserMessage) {
        this.userMessage = puserMessage;
    }


}