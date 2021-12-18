package com.sipsewanaInstitue.bo.custom.impl;

import com.sipsewanaInstitue.bo.custom.UserBO;
import com.sipsewanaInstitue.dao.DAOFactory;
import com.sipsewanaInstitue.dao.custom.UserDAO;
import com.sipsewanaInstitue.dto.UserDTO;
import com.sipsewanaInstitue.entity.User;
import com.sipsewanaInstitue.util.FactoryConfiguration;
import org.hibernate.Session;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO getUserDetails(String userName, String pwd) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        session.getTransaction().begin();

        UserDTO userDTO = null;
        try {
            User user = userDAO.getUserDetails(userName, pwd);
            userDTO = new UserDTO(user.getUid(), user.getName(), user.getUserName(), user.getPassword());
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }

        return userDTO;
    }

    @Override
    public void update(UserDTO userDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        session.getTransaction().begin();

        try {
            userDAO.update(new User(userDTO.getId(), userDTO.getName(), userDTO.getUserName(), userDTO.getPassword()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public void addUser(UserDTO userDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        session.getTransaction().begin();

        try {
            userDAO.add(new User(userDTO.getId(), userDTO.getName(), userDTO.getUserName(), userDTO.getPassword()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }
}
