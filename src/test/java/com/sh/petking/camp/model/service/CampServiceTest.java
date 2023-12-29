package com.sh.petking.camp.model.service;

import com.sh.petking.camp.model.dao.CampDao;
import com.sh.petking.camp.model.entity.Camp;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class  CampServiceTest {
    private CampService campService;

    @BeforeEach
    void setUp() {
        this.campService = new CampService();
    }

    @DisplayName("campService는 null이 아닙니다.")
    @Test
    void setUpTest() {
        assertThat(campService).isNotNull();
    }

    @DisplayName("캠핑장 전체 조회를 할 수 있습니다.")
    @Test
    void test1() {
        List<Camp> camps = campService.findAll();
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
        Camp camp = campService.findById(id);
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

    @DisplayName("한개의 캠핑장을 등록할 수 있습니다.")
    @ParameterizedTest
    @CsvSource({"sample,djfkl*d23213,000-00-00000,홍길동,샘플캠핑장,대형 반려견이 뛰놀 수 있는 넓은 운동장,07012341234,강원도 홍천군 서면 밤벌길19번길 111,37.70151912, 127.5967171"})
    void test3(String businessId, String businessPassword, String businessNumber, String businessName, String campName, String campIntro, Long campPhone, String campAddr, double lcla, double lclo) {
        Camp camp = new Camp();
        camp.setBusinessId(businessId);
        camp.setBusinessPassword(businessPassword);
        camp.setBusinessNumber(businessNumber);
        camp.setBusinessName(businessName);
        camp.setCampName(campName);
        camp.setCampIntro(campIntro);
        camp.setCampPhone(campPhone);
        camp.setCampAddr(campAddr);
        camp.setCampLcLa(lcla);
        camp.setCampLcLo(lclo);

        int result = campService.insertCamp(camp);
        System.out.println(camp);
        System.out.println(result);
        System.out.println(camp.getId());

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