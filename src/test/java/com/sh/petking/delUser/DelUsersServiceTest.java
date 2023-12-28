package com.sh.petking.delUsers;

import com.sh.petking.delUsers.model.entity.DelUsers;
import com.sh.petking.delUsers.model.service.DelUsersService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DelUsersServiceTest {
    DelUsersService delUsersService;

    @BeforeEach
    public void beforeEach(){
        this.delUsersService = new DelUsersService();
    }

    @DisplayName("DelUsersService 객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(delUsersService).isNotNull();
    }

//    @DisplayName("탈퇴회원 전체 조회")
//    @Test
//    public void test2() {
//        List<DelUsers> admins = delUsersService.findAll();
//        assertThat(admins)
//                .isNotNull()
//                .isNotEmpty();
//        admins.forEach((admin) -> {
//            assertThat(admin.getId()).isNotNull();
//            assertThat(admin.getUserId()).isNotNull();
//            assertThat(admin.getName()).isNotNull();
//            assertThat(admin.getRole()).isNotNull();
//
//        });
//    }
}
