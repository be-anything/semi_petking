package com.sh.petking.delUser.model.service;

import com.sh.petking.delUser.model.dao.DelUserDao;
import com.sh.petking.delUser.model.entity.DelUser;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class DelUserService {
    private DelUserDao delUserDao = new DelUserDao();
    public List<DelUser> findAll() {
        SqlSession session = getSqlSession();
        List<DelUser> delUsers = delUserDao.findAll(session);
        session.close();
        return delUsers;
    }
}
