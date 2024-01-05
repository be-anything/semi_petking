package com.sh.petking.pet.model.service;

import com.sh.petking.pet.model.dao.PetDao;
import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.vo.PetVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class PetService {
    private PetDao petDao = new PetDao();
    public List<PetVo> findAllPet(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<PetVo> pets = petDao.findAllPet(session, param);
        session.close();
        return pets;
    }

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = petDao.getTotalCount(session);
        session.close();
        return totalCount;
    }
    public int insertPet(Pet pet) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = petDao.insertPet(session, pet);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
