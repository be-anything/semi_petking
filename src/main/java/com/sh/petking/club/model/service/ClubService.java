package com.sh.petking.club.model.service;

import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.club.model.dao.ClubDao;
import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.club.model.vo.ClubVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ClubService {

    private ClubDao clubDao = new ClubDao();

    public List<Club> findAll() {
        SqlSession session = getSqlSession();
        List<Club> clubs = clubDao.findAll(session);
        session.close();
        return clubs; //결과값을 리턴
    }

    public ClubVo findById(long id){
        SqlSession session = getSqlSession();
        ClubVo club = clubDao.findById(session, id);
        session.close();
        return club;
    }

    public ClubVo findById(long id, boolean hasRead) {
        SqlSession session = getSqlSession();
        ClubVo club = null;
        int result = 0;
        try {
            // 조회수 증가처리
            if (!hasRead)
                result = clubDao.updateClubViewCount(session, id);

            // 조회
            club = clubDao.findById(session, id);
//            List<ClubComment> comments = boardDao.findCommentByBoardId(session, id);
//            board.setComments(comments);

            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return club;
    }

    public int insertClub(ClubVo club) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = clubDao.insertClub(session, club);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int updateClub(ClubVo club) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            // club테이블 수정
            result = clubDao.updateClub(session, club);

//            // attachment테이블 삭제
//            List<Long> delFiles = club.getDelFiles();
//            if (!delFiles.isEmpty()) {
//                for (Long id : delFiles) {
//                    result = clubDao.deleteAttachment(session, id);
//                }
//            }
//            // attachment테이블 등록
//            List<ClubAttach> attachments = club.getAttachments();
//            if (!attachments.isEmpty()) {
//                for (ClubAttach attach : attachments) {
//                    attach.setClubId(club.getId()); // fk 등록
//                    result = clubDao.insertAttachment(session, attach);
//                }
//            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deleteClub(long id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = clubDao.deleteClub(session, id);
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

    public int insertClubUsers(ClubUsers clubUsers) {
        int result2 = 0;
        SqlSession session = getSqlSession();
        try {
            result2 = clubDao.insertClubUsers(session, clubUsers);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result2;
    }
}
