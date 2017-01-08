package com.dao;

import com.model.Event;

/**
 * EventDAO
 */
public interface EventDAO {

    /**
     *
     * @param event
     */
    public void insertEvent(final Event event) throws Exception;

    Event getEvent(final Long eventId) throws Exception;
}
