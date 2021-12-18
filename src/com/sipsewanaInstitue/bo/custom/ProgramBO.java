package com.sipsewanaInstitue.bo.custom;

import com.sipsewanaInstitue.bo.SuperBO;
import com.sipsewanaInstitue.dto.ProgramDTO;

import java.util.List;

public interface ProgramBO extends SuperBO {
    List<ProgramDTO> getAll() throws Exception;

    void add(ProgramDTO programDTO) throws Exception;

    String getLastId() throws Exception;

    void update(ProgramDTO programDTO) throws Exception;

    void delete(String id) throws Exception;

    List<ProgramDTO> findProgram(String value) throws Exception;

    ProgramDTO search(String id) throws Exception;
}
