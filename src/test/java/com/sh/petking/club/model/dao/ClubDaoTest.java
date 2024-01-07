package com.sh.petking.club.model.dao;

import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.service.ClubService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class ClubDaoTest {

    ClubDao clubDao;
    SqlSession session;


    @BeforeEach
    void setUp(){
        this.clubDao = new ClubDao();
        this.session = getSqlSession();
    }

    @AfterEach
    void tearDown(){
        this.session.rollback();
        this.session.close();
    }

    @DisplayName("Club객체는 null이 아니다.")
    @Test
    public void test0() {
        assertThat(clubDao).isNotNull();
    }

    @DisplayName("동아리 전체를 조회 할 수 있습니다.")
    @Test
    void test1(){
        List<Club> boards = clubDao.findAll(session);
        System.out.println(boards);
        assertThat(boards)
                .isNotNull()
                .allSatisfy((club) -> {
                    assertThat(club.getId()).isNotZero();
                    assertThat(club.getUserId()).isNotNull();
                    assertThat(club.getClubName()).isNotNull();
                    assertThat(club.getClubIntroTitle()).isNotNull();
                    assertThat(club.getClubIntroContent()).isNotNull();
                    assertThat(club.getRegDate()).isNotNull();
                });
    }

    @DisplayName("동아리 하나를 조회 할 수 있습니다.")
    @ParameterizedTest
    @ValueSource(longs = {45L, 46L, 47L})
    void test2_1(long id) {
        Club club = clubDao.findById(session, id);
        System.out.println(club);

        assertThat(club)
                .isNotNull()
                .satisfies((_club) -> {
                    assertThat(_club.getId()).isNotZero();
                    assertThat(_club.getClubName()).isNotNull();
                    assertThat(_club.getUserId()).isNotNull();
                    assertThat(_club.getClubIntroTitle()).isNotNull();
                    assertThat(_club.getClubIntroContent()).isNotNull();
                    assertThat(_club.getRegDate()).isNotNull();
                });
    }

    @DisplayName("존재하지 않는 동아리 하나 조회")
    @ParameterizedTest
    @ValueSource(longs = {100000000L, 9999999L})
    void test2_2(long id){
        Club club = clubDao.findById(session, id);
        assertThat(club).isNull();
    }

    @DisplayName("동아리 등록")
    @Test
    void test3(){
        Club club = new Club('5', "최고동아리", "최고제목", "최고내용", null, "abcde");
        System.out.println(club);
        int result = clubDao.insertClub(session, club);

        assertThat(result).isGreaterThan(0);
        assertThat(club.getId()).isNotZero();
    }

    @DisplayName("동아리 수정")
    @ParameterizedTest
    @MethodSource("clubIdProvider")
    void test4(long id){
        Club club = clubDao.findById(session, id);
        assertThat(club).isNotNull();
        System.out.println(club);
        String newClubIntroTitle = "새 제목";
        String newClubIntroContent = "새 내용";
        club.setClubIntroTitle(newClubIntroTitle);
        club.setClubIntroContent(newClubIntroContent);
        System.out.println(club);
        int result = clubDao.updateClub(session, club);
        assertThat(result).isGreaterThan(0);
        Club clubUpdated = clubDao.findById(session, id);
        assertThat(clubUpdated).satisfies((c) -> {
            assertThat(c.getClubIntroTitle()).isEqualTo(newClubIntroTitle);
            assertThat(c.getClubIntroContent()).isEqualTo(newClubIntroContent);
        });
    }

    @DisplayName("동아리 삭제")
    @ParameterizedTest
    @MethodSource("clubIdProvider")
    void test5(long id){
        Club club = clubDao.findById(session, id);
        assertThat(club).isNotNull();
        int result = clubDao.deleteClub(session, id);
        assertThat(result).isGreaterThan(0);
        Club clubDeleted = clubDao.findById(session, id);
        assertThat(clubDeleted).isNull();
    }

    public static Stream<Arguments> clubIdProvider() {
        ClubService clubService = new ClubService(); // non-static fixture를 사용할 수 없다.
        List<Club> clubs = clubService.findAll();
        return Stream.of(Arguments.arguments(clubs.get(0).getId()));
    }
}
