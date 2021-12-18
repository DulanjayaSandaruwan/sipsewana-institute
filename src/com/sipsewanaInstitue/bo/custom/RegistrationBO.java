package com.sipsewanaInstitue.bo.custom;

import com.sipsewanaInstitue.bo.SuperBO;
import com.sipsewanaInstitue.dto.RegistrationDTO;
import com.sipsewanaInstitue.dto.StudentDTO;

public interface RegistrationBO extends SuperBO {
    void makeRegistration(StudentDTO student, RegistrationDTO registrationDTO) throws Exception;

    String getLastId() throws Exception;
}
