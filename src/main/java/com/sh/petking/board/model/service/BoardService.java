package com.sh.petking.board.model.service;

import com.sh.petking.board.model.dao.BoardDao;
import com.sh.petking.board.model.entity.*;
import com.sh.petking.board.model.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class BoardService {

    private BoardDao boardDao = new BoardDao();
    public List<Board> findAll() {
        SqlSession session = getSqlSession();
        List<Board> boards = boardDao.findAll(session);
        session.close();
        return boards;
    }

    public BoardVo findById(long id) {
        SqlSession session = getSqlSession();
        BoardVo board = boardDao.findById(session, id);
        session.close();
        return board;
    }


    public BoardVo findById(long id, boolean hasRead) {
        SqlSession session = getSqlSession();
        BoardVo board = null;
        int result = 0;
        try {
            // 조회수 증가처리
            if (!hasRead)
                result = boardDao.updateBoardViewCount(session, id);

            // 조회
            board = boardDao.findById(session, id);
            List<BoardComment> comments = boardDao.findCommentByBoardId(session, id);
            board.setComments(comments);

            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return board;
    }

    public int insertBoard(BoardVo board){
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.insertBoard(session, board);

            // attachment
            List<BoardAttach> attachments = board.getAttachments();
            if(!attachments.isEmpty()){
                for(BoardAttach attach : attachments){
                    attach.setBoardId(board.getId());
                    result = boardDao.insertAttachment(session, attach);
                }
            }
            session.commit();
        }
        catch (Exception e){
            session.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    public int updateBoard(BoardVo board) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            // board테이블 수정
            result = boardDao.updateBoard(session, board);

            // attachment테이블 삭제
            List<Long> delFiles = board.getDelFiles();
            if (!delFiles.isEmpty()) {
                for (Long id : delFiles) {
                    result = boardDao.deleteAttachment(session, id);
                }
            }
            // attachment테이블 등록
            List<BoardAttach> attachments = board.getAttachments();
            if (!attachments.isEmpty()) {
                for (BoardAttach attach : attachments) {
                    attach.setBoardId(board.getId()); // fk 등록
                    result = boardDao.insertAttachment(session, attach);
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

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

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = boardDao.getTotalCount(session);
        session.close();
        return totalCount;
    }

    public List<BoardVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<BoardVo> boards = boardDao.findAll(session, param);
        session.close();
        return boards;
    }

    public int insertBoardComment(BoardComment comment) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.insertBoardComment(session, comment);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deleteBoardComment(long id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.deleteBoardComment(session, id);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int requestBoard(long id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = boardDao.requestBoard(session, id);
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
}
