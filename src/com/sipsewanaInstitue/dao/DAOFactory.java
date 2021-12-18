package com.sipsewanaInstitue.dao;

import com.sipsewanaInstitue.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case PROGRAM:
                return (T) new ProgramDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
            case REGISTRATIONDETAIL:
                return (T) new RegistrationDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT, PROGRAM, REGISTRATION, REGISTRATIONDETAIL, QUERY, USER
    }
}
