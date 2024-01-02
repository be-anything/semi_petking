package com.sh.petking.delUser.model.dao;

import com.sh.petking.delUser.model.dao.DelUserDao;
import com.sh.petking.delUser.model.entity.DelUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DelUserDaoTest {
    private DelUserDao delUserDao;
    private SqlSession session;
    @BeforeEach
    public void setUp(){
        this.delUserDao = new DelUserDao();
        this.session = getSqlSession();
    }
    @AfterEach
    public void tearDown(){
        this.session.rollback();
        this.session.close();
    }

    @DisplayName("DelUsersDao 객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(delUserDao).isNotNull();
        assertThat(session).isNotNull();
    }

    @DisplayName("탈퇴회원 전체 조회")
    @Test
    public void test2() {
        List<DelUser> delUsers = delUserDao.findAll(session);
        assertThat(delUsers)
                .isNotNull()
                .isNotEmpty();
        System.out.println(delUsers);
        delUsers.forEach((admin) -> {
            assertThat(admin.getId()).isNotNull();
            assertThat(admin.getUserId()).isNotNull();
            assertThat(admin.getName()).isNotNull();
            assertThat(admin.getRole()).isNotNull();
            assertThat(admin.getDelDate()).isNotNull();
            assertThat(admin.getDelReason()).isNotNull();
            assertThat(admin.getRegDate()).isNotNull();
        });
    }
}
