package com.sh.petking.board.model.service;

import com.sh.petking.board.model.dao.BoardDao;
import com.sh.petking.board.model.entity.Board;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class BoardService {

    private BoardDao boardDao = new BoardDao();
    public List<Board> findAll() {
        SqlSession session = getSqlSession();
        List<Board> boards = boardDao.findAll(session);
        session.close();
        return boards;
    }

    public Board findById(long id) {
        SqlSession session = getSqlSession();
        Board board = boardDao.findById(session, id);
        session.close();
        return board;
    }

//    public int insertBoard(long id ){
//
//    }

    public int deleteBoard(long id){
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.deleteBoard(session, id);
            session.commit();
        }
        catch(Exception e){
            session.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

//    public int getTotalCount() {
//        int result = 0;
//    }
}
