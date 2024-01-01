package com.sh.petking.delUser.model.service;

import com.sh.petking.board.model.entity.Board;
import com.sh.petking.delUser.model.dao.DelUserDao;
import com.sh.petking.delUser.model.entity.DelUser;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class DelUserService {
    private DelUserDao delUserDao = new DelUserDao();

    public List<DelUser> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<DelUser> delUsers = delUserDao.findAll(session, param);
        session.close();
        return delUsers;
    }

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = delUserDao.getTotalCount(session);
        session.close();
        return totalCount;
    }
}
