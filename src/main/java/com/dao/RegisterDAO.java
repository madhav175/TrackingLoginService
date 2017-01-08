package com.dao;

import com.model.Register;

/**
 * Created by manish on 19/7/16.
 */
public interface RegisterDAO {
    /**
     * Method to insert record in register table
     *
     * @param record
     * @throws Exception
     */
    void insertRecord(final Register record) throws Exception;

    /**
     * Method to get record by phoneNo
     *
     * @param phoneNo
     * @return
     * @throws Exception
     */
    Register getRecord(final Long phoneNo) throws Exception;

    /**
     * DAO method to update otp
     *
     * @param otp
     * @throws Exception
     */
    void updateOTP(final Register register, final String otp) throws Exception;

}
