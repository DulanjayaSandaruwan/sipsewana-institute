package com.sipsewanaInstitue.dao.custom.impl;

import com.sipsewanaInstitue.dao.CrudDAOImpl;
import com.sipsewanaInstitue.dao.custom.StudentDAO;
import com.sipsewanaInstitue.entity.Student;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl extends CrudDAOImpl <Student, String> implements StudentDAO {
    @Override
    public String getLastId() throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT sid FROM Student ORDER BY sid DESC LIMIT 1");
        return sqlQuery.uniqueResult().toString();
    }

    @Override
    public List<Student> findStudent(String value) throws Exception {
        Query query = session.createQuery("FROM Student WHERE sid LIKE ?1 OR name LIKE ?2");
        query.setParameter(1, "%"+value+"%");
        query.setParameter(2, "%"+value+"%");
        return query.list();
    }
}
