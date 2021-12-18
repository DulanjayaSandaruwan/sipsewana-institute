package com.sipsewanaInstitue.bo.custom.impl;

import com.sipsewanaInstitue.bo.custom.RegistrationDetailBO;
import com.sipsewanaInstitue.dao.DAOFactory;
import com.sipsewanaInstitue.dao.custom.QueryDAO;
import com.sipsewanaInstitue.dto.CustomDTO;
import com.sipsewanaInstitue.entity.Custom;
import com.sipsewanaInstitue.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDetailBOImpl implements RegistrationDetailBO {
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<CustomDTO> getRegDetailsBySid(String sid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        queryDAO.setSession(session);
        session.getTransaction().begin();

        List<CustomDTO> list = new ArrayList<>();
        try {
            List<Custom> all = queryDAO.getRegDetailsBySid(sid);
            for (Custom c : all) {
                list.add(new CustomDTO(c.getRegId(),c.getDate(), c.getProgram()));
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<CustomDTO> getRegDetailsByPid(String pid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        queryDAO.setSession(session);
        session.getTransaction().begin();

        List<CustomDTO> list = new ArrayList<>();
        try {
            List<Custom> all = queryDAO.getRegDetailsByPid(pid);
            for (Custom c : all) {
                list.add(new CustomDTO(c.getSid(),c.getRegId(), c.getName(), c.getAddress()));
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return list;
    }
}
