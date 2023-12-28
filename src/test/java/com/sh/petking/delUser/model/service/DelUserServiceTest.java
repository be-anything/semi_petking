package com.sh.petking.delUser.model.service;

import com.sh.petking.delUser.model.entity.DelUser;
import com.sh.petking.delUser.model.service.DelUserService;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DelUserServiceTest {
    DelUserService delUserService;

    @BeforeEach
    public void beforeEach(){
        this.delUserService = new DelUserService();
    }

    @DisplayName("DelUsersService 객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(delUserService).isNotNull();
    }

    @DisplayName("탈퇴회원 전체 조회")
    @Test
    public void test2() {
        List<DelUser> delUsers = delUserService.findAll();
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
