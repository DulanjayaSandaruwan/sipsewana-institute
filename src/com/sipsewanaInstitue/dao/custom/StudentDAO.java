package com.sipsewanaInstitue.dao.custom;

import com.sipsewanaInstitue.dao.CrudDAO;
import com.sipsewanaInstitue.entity.Student;
import java.util.List;

public interface StudentDAO extends CrudDAO <Student, String> {
    String getLastId() throws Exception;

    List<Student> findStudent(String value) throws Exception;
}
