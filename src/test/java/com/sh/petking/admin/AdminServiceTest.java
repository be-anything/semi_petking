package com.sh.petking.admin;

import com.sh.petking.admin.model.service.AdminService;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminServiceTest {
    AdminService adminService;

    @BeforeEach
    public void beforeEach(){
        this.adminService = new AdminService();
    }

    @DisplayName("AdminService 객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(adminService).isNotNull();
    }

    @DisplayName("회원 전체 조회")
    @Test
    public void test2() {

    }
}
