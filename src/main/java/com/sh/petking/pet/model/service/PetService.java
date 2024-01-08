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

    public int updatePet(Pet pet) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            System.out.println("service update" + pet);
            result = petDao.updatePet(session, pet);
                session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deletePet(String userid) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = petDao.deletePet(session, userid);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public Pet findByPet(String id) {
        SqlSession session = getSqlSession();
        Pet pet = petDao.findByPet(session, id);
        session.close();
        return pet;
    }
}
