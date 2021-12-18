package com.sipsewanaInstitue.bo.custom;

import com.jfoenix.controls.JFXPasswordField;
import com.sipsewanaInstitue.bo.SuperBO;
import com.sipsewanaInstitue.dto.UserDTO;

public interface UserBO extends SuperBO {
    UserDTO getUserDetails(String userName, String pwd) throws Exception;

    void update(UserDTO userDTO) throws Exception;

    void addUser(UserDTO userDTO) throws Exception;
}
