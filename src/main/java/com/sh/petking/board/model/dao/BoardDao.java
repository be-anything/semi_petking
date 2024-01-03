package com.sh.petking.board.model.dao;

import com.sh.petking.board.model.entity.Board;
import com.sh.petking.board.model.entity.BoardAttach;
import com.sh.petking.board.model.entity.BoardComment;
import com.sh.petking.board.model.vo.BoardVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class BoardDao {
    public List<Board> findAll(SqlSession session) {
        return session.selectList("board.findAll");
    }

    public List<Board> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        return session.selectList("board.findAll", null, new RowBounds(offset, limit));
    }

    public BoardVo findById(SqlSession session, long id) {
        return session.selectOne("board.findById", id);
    }

    public int insertBoard(SqlSession session, Board board) {
        return session.insert("board.insertBoard", board);
    }

    public int updateBoard(SqlSession session, Board board) {
        return session.update("board.updateBoard", board);
    }

    public int deleteBoard(SqlSession session, long id) {
        return session.delete("board.deleteBoard", id);
    }

    public int getTotalCount(SqlSession session) {
        return session.selectOne("board.getTotalCount");
    }

    public List<BoardComment> findCommentByBoardId(SqlSession session, long boardId) {
        return session.selectList("board.findCommentByBoardId", boardId);
    }

    public int updateBoardViewCount(SqlSession session, long id) {
        return session.update("board.updateBoardViewCount", id);
    }

    public int deleteAttachment(SqlSession session, Long id) {
        return session.delete("board.deleteAttachment", id);
    }

    public int insertAttachment(SqlSession session, BoardAttach attach) {
        return session.insert("board.insertAttachment", attach);
    }
}
