package com.dao;

import com.model.Group;

import java.util.List;

/**
 * GroupDAO
 */
public interface GroupDAO {

    /**
     *
     * @param group
     * @throws Exception
     */
    void insertPerson(final Group group) throws Exception;

    /**
     *
     *
     * @param eventId
     * @return
     * @throws Exception
     */
    List<Group> getEventGroup(final Long eventId) throws Exception;
}
