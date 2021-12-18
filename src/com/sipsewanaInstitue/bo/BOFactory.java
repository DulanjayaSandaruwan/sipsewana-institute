package com.sipsewanaInstitue.bo;

import com.sipsewanaInstitue.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case PROGRAM:
                return (T) new ProgramBOImpl();
            case REGISTRATION:
                return (T) new RegistrationBOImpl();
            case REGISTRATIONDETAIL:
                return (T) new RegistrationDetailBOImpl();
            case USER:
                return (T) new UserBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        STUDENT, PROGRAM, REGISTRATION, REGISTRATIONDETAIL, USER
    }
}
