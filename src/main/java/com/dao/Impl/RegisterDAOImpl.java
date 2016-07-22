package com.dao.Impl;

import com.dao.RegisterDAO;
import com.model.Register;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by manish on 19/7/16.
 */
public class RegisterDAOImpl extends HibernateDaoSupport implements RegisterDAO{

    @Transactional
    public void insertRecord(Register record) throws Exception {
        try {
            getHibernateTemplate().saveOrUpdate(record);
            logger.info(String.format("Record inserted successfully for phoneNo [%s]", record.getPhoneNo()));
        } catch (Exception e) {
            logger.error(String.format("Record insertion failed for phoneNo [%s]", record.getPhoneNo()));
            throw  e;
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Register getRecord(Long phoneNo) throws Exception {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(Register.class);
            criteria.add(Restrictions.eq("phoneNo", phoneNo));

            final List<Register> results = (List<Register>) getHibernateTemplate().findByCriteria(criteria);
            if(CollectionUtils.isEmpty(results)) {
                logger.warn(String.format("No Reccord found for phoneNo [%s]", phoneNo));
                return null;
            }
            final Set<Register> records = new HashSet<Register>(results);
            final Register record = records.iterator().next();

            logger.info(String.format("Record for phoneNo [%s] is [%s]", phoneNo, record));
            return record;
        } catch (Exception e) {
            logger.info(String.format("Fetching Record from DB failed for phoneNo [%s]", phoneNo));
            throw  e;
        }
    }


}
