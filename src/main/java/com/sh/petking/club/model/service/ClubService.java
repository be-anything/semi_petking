package com.sh.petking.club.model.service;

import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.club.model.dao.ClubDao;
import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.club.model.vo.ClubVo;
import com.sh.petking.user.model.dao.UserDao;
import com.sh.petking.user.model.entity.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ClubService {

    private ClubDao clubDao = new ClubDao();
    private UserDao userDao = new UserDao();

    public List<Club> findAll() {
        SqlSession session = getSqlSession();
        List<Club> clubs = clubDao.findAll(session);
        session.close();
        return clubs; //결과값을 리턴
    }

    public List<ClubUsers> findAllClubUsers() {
        SqlSession session = getSqlSession();
        List<ClubUsers> clubUsers = clubDao.findAllClubUsers(session);
        session.close();
        return clubUsers; //결과값을 리턴
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

    public int insertClubUsers(ClubUsers clubUsers) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = clubDao.insertClubUsers(session, clubUsers);
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

    public int insertClub(Map<String, Object> param) {
        Club club = (Club) param.get("club");
        ClubUsers clubUsers = (ClubUsers) param.get("clubUsers");
        User user = (User) param.get("user");

        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = clubDao.insertClub(session, club);

            if(result > 0){
                club.getId();
                System.out.println("club아이디 가져와지나요" + club.getId());
                // role 확인용
                clubUsers.setClubId(club.getId());
                user.setClubId(club.getId());
                result = clubDao.insertClubUsers(session, clubUsers);
                result = userDao.updateUserClubId(session, user);
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

    public int insertClubUsersRequest(Map<String, Object> param) {
        ClubUsers clubUsers = (ClubUsers) param.get("clubUsers");
        User user = (User) param.get("user");

        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = clubDao.insertClubUsers(session, clubUsers);
            result = userDao.updateUserClubId(session, user);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public ClubUsers findByUserId(String userId) {
        SqlSession session = getSqlSession();
        ClubUsers clubUsers = clubDao.findByUserId(session, userId);
        session.close();
        return clubUsers;
    }
}
