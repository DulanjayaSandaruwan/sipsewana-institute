package com.sipsewanaInstitue.bo.custom;

import com.sipsewanaInstitue.bo.SuperBO;
import com.sipsewanaInstitue.dto.ProgramDTO;
import com.sipsewanaInstitue.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    void add(StudentDTO studentDTO) throws Exception;

    String getLastId() throws Exception;

    List<StudentDTO> getAll() throws Exception;

    StudentDTO search(String id) throws Exception;

    void update(StudentDTO studentDTO) throws Exception;

    void delete(String id) throws Exception;

    List<StudentDTO> findStudents(String value) throws Exception;
}
