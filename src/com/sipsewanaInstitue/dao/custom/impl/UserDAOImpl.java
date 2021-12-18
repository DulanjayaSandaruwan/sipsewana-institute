package com.sipsewanaInstitue.dao.custom.impl;

import com.sipsewanaInstitue.dao.CrudDAOImpl;
import com.sipsewanaInstitue.dao.custom.UserDAO;
import com.sipsewanaInstitue.entity.User;
import org.hibernate.query.Query;

public class UserDAOImpl extends CrudDAOImpl<User, String> implements UserDAO {
    @Override
    public User getUserDetails(String userName, String pwd) throws Exception {
        Query query = session.createQuery("FROM User WHERE (userName = ?1 AND password = ?2)");
        query.setParameter(1, userName);
        query.setParameter(2, pwd);
        return (User) query.uniqueResult();
    }
}
