package com.sipsewanaInstitue.bo.custom;

import com.sipsewanaInstitue.bo.SuperBO;
import com.sipsewanaInstitue.dto.CustomDTO;

import java.util.List;

public interface RegistrationDetailBO extends SuperBO {
    List<CustomDTO> getRegDetailsBySid(String sid) throws Exception;

    List<CustomDTO> getRegDetailsByPid(String pid) throws Exception;
}
