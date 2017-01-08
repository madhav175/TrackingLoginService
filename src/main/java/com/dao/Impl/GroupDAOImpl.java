package com.dao.Impl;

import com.dao.GroupDAO;
import com.model.Group;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * GroupDAOImpl
 */
public class GroupDAOImpl extends HibernateDaoSupport implements GroupDAO{

    @Transactional
    public void insertPerson(Group group) throws Exception {
        try {
            getHibernateTemplate().saveOrUpdate(group);
            logger.info(String.format("Person with name : %s successfully added in the group", group.getPersonName()));
        } catch (Exception e) {
            logger.error(String.format("Adding person to event group failed. Person name : %s", group.getPersonName()));
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Group> getEventGroup(final Long eventId) throws Exception {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(Group.class);
            criteria.add(Restrictions.eq("eventId", eventId));

            final List<Group> records = (List<Group>) getHibernateTemplate().findByCriteria(criteria);
            if(CollectionUtils.isEmpty(records)) {
                logger.error(String.format("No group found for event [%s]", eventId));
                return null;
            }

            logger.info(String.format("Group for the event [%s] is [%s]", eventId, records));
            return records;
        } catch (Exception e) {
            logger.error(String.format("Error occurred while getting Event Group for event[%s]", eventId));
            throw e;
        }
    }


}
