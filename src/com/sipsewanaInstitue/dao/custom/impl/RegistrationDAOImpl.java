package com.sipsewanaInstitue.dao.custom.impl;


import com.sipsewanaInstitue.dao.CrudDAOImpl;
import com.sipsewanaInstitue.dao.custom.RegistrationDAO;
import com.sipsewanaInstitue.entity.Registration;
import org.hibernate.query.NativeQuery;

public class RegistrationDAOImpl extends CrudDAOImpl<Registration, String> implements RegistrationDAO {
    @Override
    public String getLastId() throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT regId FROM Registration ORDER BY regId DESC LIMIT 1");
        return sqlQuery.uniqueResult().toString();
    }
}
