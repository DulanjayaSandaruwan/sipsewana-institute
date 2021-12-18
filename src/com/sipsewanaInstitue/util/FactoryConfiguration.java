package com.sipsewanaInstitue.util;

import com.sipsewanaInstitue.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @since : 2021-12-16 , 08.47
 **/
public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration () {
        Configuration configuration;
        configuration = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Registration.class)
                .addAnnotatedClass(RegistrationDetail.class)
                .addAnnotatedClass(Program.class)
                .addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
