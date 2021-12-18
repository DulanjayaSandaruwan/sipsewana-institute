package com.sipsewanaInstitue.dao.custom;

import com.sipsewanaInstitue.dao.CrudDAO;
import com.sipsewanaInstitue.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    User getUserDetails(String userName, String pwd) throws Exception;
}
