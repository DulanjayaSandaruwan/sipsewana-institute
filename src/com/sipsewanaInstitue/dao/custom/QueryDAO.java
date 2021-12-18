package com.sipsewanaInstitue.dao.custom;

import com.sipsewanaInstitue.dao.SuperDAO;
import com.sipsewanaInstitue.entity.Custom;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Custom> getRegDetailsBySid(String sid) throws Exception;

    List<Custom> getRegDetailsByPid(String pid) throws Exception;
}
