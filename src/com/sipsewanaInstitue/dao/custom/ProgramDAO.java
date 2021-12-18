package com.sipsewanaInstitue.dao.custom;

import com.sipsewanaInstitue.dao.CrudDAO;
import com.sipsewanaInstitue.entity.Program;

import java.util.List;

public interface ProgramDAO extends CrudDAO <Program, String> {
    String getLastId() throws Exception;
    List<Program> findProgram(String value) throws Exception;
}
