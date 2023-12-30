package com.sh.petking.delUser.model.dao;

import com.sh.petking.delUser.model.entity.DelUser;
import com.sh.petking.delUser.model.service.DelUserService;
import com.sh.petking.review.model.entity.Review;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class DelUserDao {

    public List<DelUser> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("delUser.findAll", param, rowBounds);
    }
    public List<DelUser> findAll(SqlSession session){
        return session.selectList("delUser.findAll");
    }

    public int getTotalCount(SqlSession session) {
        return session.selectOne("delUser.getTotalCount");
    }
}
