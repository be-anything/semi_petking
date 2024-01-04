package com.sh.petking.ask.model.service;

import com.sh.petking.ask.model.dao.AskDao;
import com.sh.petking.ask.model.entity.Ask;
import com.sh.petking.ask.model.vo.AskVo;
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

    //1.문의글 id로 조회
    public AskVo findById(long id)
    {
        SqlSession session = getSqlSession();
        int result=0;
        AskVo ask = askDao.findById(session, id);
        session.close();
        return ask;
    }

    //2.문의글 insert
    public int insertAsk(AskVo ask)
    {
        int result = 0;
        SqlSession session = getSqlSession();
        try
        {
            result = askDao.insertAsk(session,ask);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw  e;
        }
        finally
        {
            session.close();
        }
        return result;
    }

    //3.문의글 답변 update
    public int updateAsk(AskVo ask)
    {
        int result=0;
        SqlSession session = getSqlSession();
        try
        {
            result = askDao.updateAsk(session,ask);
            System.out.println("서비스단-updateAsk-성공");
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e;
        }
        finally
        {
            session.close();
        }
        return result;
    }






    
}
