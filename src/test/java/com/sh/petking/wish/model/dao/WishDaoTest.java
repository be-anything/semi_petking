package com.sh.petking.wish.model.dao;

import com.sh.petking.camp.model.dao.CampDao;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.user.model.dao.UserDao;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.wish.model.entity.Wish;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WishDaoTest {
    private WishDao wishDao;
    private SqlSession session;

    @BeforeEach
    void setUp() {
        this.wishDao = new WishDao();
        this.session = getSqlSession();
    }

    @AfterEach
    void tearDown() {
        session.rollback();
        session.close();
    }

    @DisplayName("wishDao와 session은 null이 아닙니다.")
    @Test
    void setUpTest() {
        assertThat(wishDao).isNotNull();
        assertThat(session).isNotNull();
    }

    @DisplayName("찜 전체 조회를 할 수 있습니다.")
    @Test
    void test1() {
        List<Wish> wishes = wishDao.findAll(session);
        System.out.println(wishes);
        assertThat(wishes)
                .isNotNull()
                .allSatisfy((wish) -> {
                    assertThat(wish.getId()).isNotZero();
                    assertThat(wish.getUserId()).isNotNull();
                    assertThat(wish.getCampId()).isNotZero();
                    assertThat(wish.getRegDate()).isNotNull();
                });
    }
    @DisplayName("찜 한건 조회를 할 수 있습니다.")
    @ParameterizedTest
    @MethodSource("idProvider")
    void test2(Long id) {
        Wish wish = wishDao.findById(session, id);
        assertThat(wish).isNotNull();
        assertThat(wish.getId()).isEqualTo(id);
        assertThat(wish.getUserId()).isNotNull();
        assertThat(wish.getCampId()).isNotZero();
        assertThat(wish.getRegDate()).isNotNull();
    }

    @DisplayName("회원 한명의 찜 목록을 조회할 수 있습니다.")
    @ParameterizedTest
    @MethodSource("userIdProvider")
    public void test3(String userId) {
        List<Wish> wishes = wishDao.findByUserId(session, userId);
        System.out.println(wishes);
        assertThat(wishes)
                .isNotNull()
                .allSatisfy((wish) -> {
                    assertThat(wish.getId()).isNotZero();
                    assertThat(wish.getUserId()).isNotNull();
                    assertThat(wish.getCampId()).isNotZero();
                    assertThat(wish.getRegDate()).isNotNull();
                });
    }
    @DisplayName("캠핑장 아이디로 찜목록을 조회할 수 있습니다.")
    @ParameterizedTest
    @MethodSource("campIdProvider")
    public void test4(Long campId) {
        List<Wish> wishes = wishDao.findByCampId(session, campId);
        System.out.println(wishes);
        assertThat(wishes)
                .isNotNull()
                .allSatisfy((wish) -> {
                    assertThat(wish.getId()).isNotZero();
                    assertThat(wish.getUserId()).isNotNull();
                    assertThat(wish.getCampId()).isNotZero();
                    assertThat(wish.getRegDate()).isNotNull();
                });
    }

    @DisplayName("사용자는 하나의 캠핑장을 찜할 수 있습니다.")
    @ParameterizedTest
    @MethodSource("userIdProvider")
    void test5(String userId) {
        Long campId = (long) 1;
        Wish wish = new Wish(0, userId, campId, null);
        int result = wishDao.insertWish(session, wish);
        assertThat(result).isGreaterThan(0);

        Wish wish2 = wishDao.findById(session, wish.getId());
        assertThat(wish2.getId()).isEqualTo(wish.getId());
        assertThat(wish2.getUserId()).isEqualTo(userId);
        assertThat(wish2.getCampId()).isEqualTo(campId);
        assertThat(wish2.getRegDate()).isNotNull();

    }

    /**
     * wish의 아이디(pk)를 가지고 delete하는 테스트
     * @param userId
     */
    @DisplayName("사용자는 찜 목록 중 하나를 삭제할 수 있습니다.")
    @ParameterizedTest
    @MethodSource("userIdProvider")
    void test6(String userId) {
        List<Wish> wishes = wishDao.findByUserId(session, userId);
        
        // 사용자의 찜목록이 비어있지 않을 때만 삭제 테스트를 하도록 함
        if(!wishes.isEmpty()){
            Long id = wishes.get(0).getId();
            assertThat(id).isNotZero();
            assertThat(wishes.get(0)).isNotNull();

            int result = wishDao.deleteWish(session, id);
            assertThat(result).isGreaterThan(0);

            Wish wish = wishDao.findById(session, id);
            assertThat(wish).isNull();
        }
    }

    @DisplayName("사용자는 찜 목록을 전체 삭제할 수 있습니다.")
    @ParameterizedTest
    @MethodSource("userIdProvider")
    void test7(String userId) {
        List<Wish> wishes = wishDao.findByUserId(session, userId);

        // 사용자의 찜목록이 비어있지 않을 때만 삭제 테스트를 하도록 함
        if(!wishes.isEmpty()){
            int result = wishDao.deleteWishByUserId(session, userId);
            assertThat(result).isEqualTo(wishes.size());

            List<Wish> wishes1 = wishDao.findByUserId(session, userId);
            assertThat(wishes1).isEmpty();
        }
    }

    public static Stream<Arguments> campIdProvider(){
        CampDao campDao = new CampDao();
        SqlSession session = getSqlSession();
        List<Camp> camps = campDao.findAll(session);
        return Stream.of(
                Arguments.arguments(camps.get(0).getId()),
                Arguments.arguments(camps.get(1).getId()),
                Arguments.arguments(camps.get(2).getId())
        );
    }
    public static Stream<Arguments> userIdProvider(){
        UserDao userDao = new UserDao();
        SqlSession session = getSqlSession();
        List<User> users = userDao.findAll(session);
        return Stream.of(
                Arguments.arguments(users.get(0).getId()),
                Arguments.arguments(users.get(1).getId()),
                Arguments.arguments(users.get(2).getId())
        );
    }
    public static Stream<Arguments> idProvider(){
        WishDao wishDao = new WishDao();
        SqlSession session = getSqlSession();
        List<Wish> wishes = wishDao.findAll(session);
        return Stream.of(
                Arguments.arguments(wishes.get(0).getId()),
                Arguments.arguments(wishes.get(1).getId()),
                Arguments.arguments(wishes.get(2).getId())
        );
    }
}