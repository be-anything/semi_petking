package com.sh.petking.camp.model.dao;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

class CampDaoTest {
    private CampDao campDao;
    private SqlSession session;

    @BeforeEach
    void setUp() {
        this.campDao = new CampDao();
        this.session = getSqlSession();
    }

    @AfterEach
    void tearDown() {
        session.rollback();
        session.close();
    }

    @DisplayName("campDao과 session은 null이 아닙니다.")
    @Test
    void setUpTest() {
        assertThat(campDao).isNotNull();
        assertThat(session).isNotNull();
    }

    @DisplayName("캠핑장 전체 조회를 할 수 있습니다.")
    @Test
    void test1() {
        List<Camp> camps = campDao.findAll(session);
        System.out.println(camps);
        assertThat(camps)
                .isNotNull()
                .allSatisfy((camp) -> {
                    assertThat(camp.getId()).isNotNull();
                    assertThat(camp.getBusinessId()).isNotNull();
                    assertThat(camp.getBusinessPassword()).isNotNull();
                    assertThat(camp.getBusinessNumber()).isNotNull();
                    assertThat(camp.getBusinessName()).isNotNull();
                    assertThat(camp.getCampName()).isNotNull();
                    assertThat(camp.getCampPhone()).isNotNull();
                    assertThat(camp.getCampAddr()).isNotNull();
                    assertThat(camp.getCampLcLa()).isNotZero();
                    assertThat(camp.getCampLcLo()).isNotZero();
                    assertThat(camp.getCampState()).isNotNull();
                    assertThat(camp.getRegDate()).isNotNull();
                });
    }

    @DisplayName("존재하는 캠핑장 하나를 조회할 수 있습니다.")
    @ParameterizedTest
    @MethodSource("campIdProvider")
    void test2(Long id) {
        Camp camp = campDao.findById(session, id);
        assertThat(camp).isNotNull();
        assertThat(camp.getId()).isNotNull();
        assertThat(camp.getBusinessId()).isNotNull();
        assertThat(camp.getBusinessPassword()).isNotNull();
        assertThat(camp.getBusinessNumber()).isNotNull();
        assertThat(camp.getBusinessName()).isNotNull();
        assertThat(camp.getCampName()).isNotNull();
        assertThat(camp.getCampPhone()).isNotNull();
        assertThat(camp.getCampAddr()).isNotNull();
        assertThat(camp.getCampLcLa()).isNotZero();
        assertThat(camp.getCampLcLo()).isNotZero();
        assertThat(camp.getCampState()).isNotNull();
        assertThat(camp.getRegDate()).isNotNull();
    }

//    @Disabled
    @DisplayName("한개의 캠핑장을 등록할 수 있습니다.")
    @ParameterizedTest
    @CsvSource({"sample,djfkl*d23213,000-00-00000,홍길동,샘플캠핑장,대형 반려견이 뛰놀 수 있는 넓은 운동장,07012341234,강원도 홍천군 서면 밤벌길19번길 111,37.70151912, 127.5967171"})
    void test3(String businessId, String businessPassword, String businessNumber, String businessName, String campName, String campIntro, String campPhone, String campAddr, double campLcLa, double campLcLo) {
        Camp camp = new Camp();
        camp.setBusinessId(businessId);
        camp.setBusinessPassword(businessPassword);
        camp.setBusinessNumber(businessNumber);
        camp.setBusinessName(businessName);
        camp.setCampName(campName);
        camp.setCampIntro(campIntro);
        camp.setCampPhone(campPhone);
        camp.setCampAddr(campAddr);
        camp.setCampLcLa(campLcLa);
        camp.setCampLcLo(campLcLo);

        int result = campDao.insertCamp(session, camp);
        assertThat(result).isGreaterThan(0);

        Camp camp2 = campDao.findById(session, camp.getId());

        assertThat(camp2).isNotNull();
        assertThat(camp2.getId()).isEqualTo(camp.getId());
        assertThat(camp2.getBusinessId()).isEqualTo(businessId);
        assertThat(camp2.getBusinessPassword()).isEqualTo(businessPassword);
        assertThat(camp2.getBusinessNumber()).isEqualTo(businessNumber);
        assertThat(camp2.getBusinessName()).isEqualTo(businessName);
        assertThat(camp2.getCampName()).isEqualTo(campName);
        assertThat(camp2.getCampPhone()).isEqualTo(campPhone);
        assertThat(camp2.getCampAddr()).isEqualTo(campAddr);
        assertThat(camp2.getCampLcLa()).isEqualTo(campLcLa);
        assertThat(camp2.getCampLcLo()).isEqualTo(campLcLo);
    }

//    @Disabled
    @DisplayName("존재하는 캠핑장 정보를 수정할 수 있습니다.")
    @ParameterizedTest
    @CsvSource({"sample,djfkl*d23213,000-99-00000,홍길동동동,샘플캠핑장수정,소형반려견이 뛰놀 수 있는 넓은 운동장,07045671234,강원도 홍천군 서면 밤벌길19번길 900,default.png,1"})
    void test4(String businessId, String businessPassword, String businessNumber, String businessName, String campName, String campIntro, String campPhone, String campAddr, String campOriginalImg, int campState) {
        Long id = (long) 1;
        Camp camp = campDao.findById(session, id);
        assertThat(camp).isNotNull();

        camp.setBusinessPassword(businessPassword);
        camp.setBusinessNumber(businessNumber);
        camp.setBusinessName(businessName);
        camp.setCampName(campName);
        camp.setCampIntro(campIntro);
        camp.setCampPhone(campPhone);
        camp.setCampAddr(campAddr);
        camp.setCampOriginalImg(campOriginalImg);
        camp.setCampState(campState);

        int result = campDao.updateCamp(session, camp);
        assertThat(result).isGreaterThan(0);

        Camp camp2 = campDao.findById(session, id);
        assertThat(camp2).isNotNull();
        assertThat(camp2.getId()).isEqualTo(id);
        assertThat(camp2.getBusinessPassword()).isEqualTo(businessPassword);
        assertThat(camp2.getBusinessNumber()).isEqualTo(businessNumber);
        assertThat(camp2.getBusinessName()).isEqualTo(businessName);
        assertThat(camp2.getCampName()).isEqualTo(campName);
        assertThat(camp2.getCampIntro()).isEqualTo(campIntro);
        assertThat(camp2.getCampPhone()).isEqualTo(campPhone);
        assertThat(camp2.getCampAddr()).isEqualTo(campAddr);
        assertThat(camp2.getCampOriginalImg()).isEqualTo(campOriginalImg);
        assertThat(camp2.getCampState()).isEqualTo(campState);
        System.out.println(camp2);
    }

//    @Disabled
    @DisplayName("캠핑장을 삭제할 수 있습니다.")
    @Test
    void test5() {
        Long id = (long) 1;
        Camp camp = campDao.findById(session, id);
        assertThat(camp).isNotNull();

        int result = campDao.deleteCamp(session, id);
        assertThat(result).isGreaterThan(0);

        Camp camp2 = campDao.findById(session, id);
        assertThat(camp2).isNull();
    }


    public static Stream<Arguments> campIdProvider(){
        CampService campService = new CampService();
        List<Camp> camps = campService.findAll();
        return Stream.of(
                Arguments.arguments(camps.get(0).getId()),
                Arguments.arguments(camps.get(1).getId()),
                Arguments.arguments(camps.get(2).getId())
        );
    }
}