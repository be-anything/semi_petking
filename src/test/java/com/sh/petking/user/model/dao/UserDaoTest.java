package com.sh.petking.user.model.dao;

import com.sh.petking.common.Role;
import com.sh.petking.user.model.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.*;

public class UserDaoTest {

    private UserDao userDao;
    private SqlSession session;

    @BeforeEach
    public void beforeEach() {
        this.userDao = new UserDao();
        this.session = getSqlSession();
    }
    @AfterEach
    public void afterEach() {
        this.session.rollback();
        this.session.close();
    }

    @DisplayName("User객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(userDao).isNotNull();
    }

    @DisplayName("존재하는 회원은 정상적으로 조회된다.")
    @Test
    public void test2() {
        User user = userDao.findById(session, "ronn11");
        assertThat(user).isNotNull();

        assertThat(user.getId()).isNotNull();
        assertThat(user.getPassword()).isNotNull();
        assertThat(user.getNickname()).isNotNull();
        assertThat(user.getName()).isNotNull();
        assertThat(user.getPhone()).isNotNull();
    }

    @DisplayName("모든 회원 조회")
    @Test
    public void test3() {
        List<User> users = userDao.findAll(session);

        assertThat(users)
                .isNotNull()
                .allSatisfy((user -> {
                    assertThat(user.getId()).isNotNull();
                    assertThat(user.getName()).isNotNull();
                    assertThat(user.getNickname()).isNotNull();
                }));
    }

    @DisplayName("회원 이름으로 검색")
    @Test
    public void test4() {
        String keyword = "권유상";
        List<User> users = userDao.findByName(session, keyword);
        assertThat(users)
                .isNotNull()
                .isNotEmpty();
        users.forEach((user) -> assertThat(user.getName()).contains(keyword));
    }
    @DisplayName("회원가입")
    @Test
    public void test5() {
        String id = "qlqlaqkq1";
        String password = "qwas112!";
        String nickname = "비빔밥1";
        String name = "김모과";

        User user =
                new User(id, "g01", 1L, nickname, name, password, null, null,
                        "qlqla1@naver.com", "01022223343", 0L, Role.U,null);
        int result = userDao.insertUser(session, user);
        assertThat(result).isEqualTo(1);

        User user1 = userDao.findById(session, id);
        assertThat(user1).isNotNull();
        assertThat(user1.getId()).isEqualTo(id);
        assertThat(user1.getPassword()).isEqualTo(password);
        assertThat(user1.getName()).isEqualTo(name);

    }

    @DisplayName("회원가입시 오류 체크")
    @Test
    public void test6() {
        User user = new User("alal11", "g01", 1L, "오리는 꽥", "김오리",
                    "1234ㅂㅂ!", null, null, "dhfl12@naver.com", "01099222837", 0L, Role.U, null);
        Throwable throwable = catchThrowable(() -> {
            int result = userDao.insertUser(session, user);
        });
        assertThat(throwable).isInstanceOf(Exception.class);
    }

    @DisplayName("회원 정보 수정")
    @Test
    public void test7() {
        String id = "ronn11";
        User user = userDao.findById(session, id);
        String newName = user.getName() + "론";
        String newEmail = "dhflek12@naver.com";
        String newPhone = "010992283784";
        String newNickname = "고라니화났다";

        user.setName(newName);
        user.setEmail(newEmail);
        user.setPhone(newPhone);
        user.setNickname(newNickname);

        int result = userDao.updateUser(session, user);
        assertThat(result).isGreaterThan(0);

        User user1 = userDao.findById(session, id);
        assertThat(user1.getName()).isEqualTo(newName);
        assertThat(user1.getPhone()).isEqualTo(newPhone);
        assertThat(user1.getEmail()).isEqualTo(newEmail);
        assertThat(user1.getNickname()).isEqualTo(newNickname);
    }

}
