package com.dao.Impl;

import com.dao.EventDAO;
import com.model.Event;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * EventDAOImpl
 */
public class EventDAOImpl extends HibernateDaoSupport implements EventDAO{

    @Transactional
    public void insertEvent(final Event event) throws Exception {
        try {
            getHibernateTemplate().saveOrUpdate(event);
            logger.info(String.format("Event Record inserted successfully for event_id [%s]", event.getEventId()));
        } catch (Exception e) {
            logger.error(String.format("Event Record insertion unsuccessful for event_id [%s]", event.getEventId()));
            throw e;
        }

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Event getEvent(final Long eventId) throws Exception {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);
            criteria.add(Restrictions.eq("eventId", eventId));

            final List<Event> results = (List<Event>) getHibernateTemplate().findByCriteria(criteria);
            if(CollectionUtils.isEmpty(results)) {
                logger.info(String.format("No Event found for eventId [%s]", eventId));
                return null;
            }

            final Event record = results.iterator().next();
            logger.info(String.format("Event for event_id [%s] is [%s]", eventId, record.getEventName()));
            return record;
        } catch (Exception e) {
            logger.error(String.format("Error while getting Event for eventId [%s]", eventId));
            throw e;
        }
    }


}
