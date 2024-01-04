package com.sh.petking.ask.model.service;

import com.sh.petking.ask.model.dao.AskDao;
import com.sh.petking.ask.model.entity.Ask;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class AskService {
    AskDao askDao = new AskDao();

    //0.전체조회
    public List<Ask> findAll() {
        SqlSession session = getSqlSession();
        List<Ask> ask = askDao.findAll(session);
        session.close();
        return ask; //결과값을 리턴
    }








    
}
