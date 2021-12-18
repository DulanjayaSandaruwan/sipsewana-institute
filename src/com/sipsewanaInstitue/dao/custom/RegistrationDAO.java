package com.sipsewanaInstitue.dao.custom;


import com.sipsewanaInstitue.dao.CrudDAO;
import com.sipsewanaInstitue.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration, String> {
    String getLastId() throws Exception;
}
