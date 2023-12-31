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
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("회원가입")
    @Test
    public void test4() {
        String id = "qlqlaqkq1";
        String password = "qwas112!";
        String nickname = "비빔밥1";
        String name = "김모과";

        User user =
                new User(id, "g01", 1, nickname, name, password, null, null,
                        "qlqla1@naver.com", "01022223343", 0, Role.U,null);
        int result = userDao.insertUser(session, user);
        assertThat(result).isEqualTo(1);

        User user1 = userDao.findById(session, id);
        assertThat(user1).isNotNull();
        assertThat(user1.getId()).isEqualTo(id);
        assertThat(user1.getPassword()).isEqualTo(password);
        assertThat(user1.getName()).isEqualTo(name);

    }

}
