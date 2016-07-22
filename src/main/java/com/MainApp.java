package com;

import com.configuration.AppConfig;
import com.dao.RegisterDAO;
import com.model.Register;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by manish on 14/7/16.
 */

public class MainApp {

    public static void main(String[] args) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RegisterDAO registerDAO = (RegisterDAO) context.getBean("registerDao");
        Register register = new Register();
        register.setName("Manish");
        register.setPhoneNo(5678L);
        register.setEmail("abc@gmail.com");


        try {
            registerDAO.insertRecord(register);
            registerDAO.getRecord(5678L);
            System.out.print("Insertion Done!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
