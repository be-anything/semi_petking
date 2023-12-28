package com.sh.petking.delUser.model.dao;

import com.sh.petking.delUser.model.entity.DelUser;
import com.sh.petking.delUser.model.service.DelUserService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DelUserDao {

    public List<DelUser> findAll(SqlSession session) {
        return session.selectList("delUser.findAll");
    }
}
