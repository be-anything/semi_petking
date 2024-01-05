package com.sh.petking.pet.model.dao;

import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.vo.PetVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class PetDao {
    public List<PetVo> findAllPet(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");

        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("pet.findAllPet", param, rowBounds);
    }
    public int getTotalCount(SqlSession session) {
        return session.selectOne("pet.getTotalCount");
    }

    public int insertPet(SqlSession session, Pet pet) {
        return session.insert("pet.insertPet", pet);
    }
}
