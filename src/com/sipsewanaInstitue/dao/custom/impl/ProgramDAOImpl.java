package com.sipsewanaInstitue.dao.custom.impl;

import com.sipsewanaInstitue.dao.CrudDAOImpl;
import com.sipsewanaInstitue.dao.custom.ProgramDAO;
import com.sipsewanaInstitue.entity.Program;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ProgramDAOImpl extends CrudDAOImpl <Program, String> implements ProgramDAO {
    @Override
    public String getLastId() throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT pid FROM Program ORDER BY pid DESC LIMIT 1");
        return sqlQuery.uniqueResult().toString();
    }

    @Override
    public List<Program> findProgram(String value) throws Exception {
        Query query = session.createQuery("FROM Program WHERE pid LIKE ?1 OR program LIKE ?2");
        query.setParameter(1, "%"+value+"%");
        query.setParameter(2, "%"+value+"%");
        return query.list();
    }
}
