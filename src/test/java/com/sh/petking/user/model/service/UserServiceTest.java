package com.sh.petking.user.model.service;

import com.sh.petking.user.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void beforeEach() {
        this.userService = new UserService();
    }

    @DisplayName("User객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(userService).isNotNull();
    }
    @DisplayName("존재하는 회원은 정상적으로 조회된다.")
    @Test
    public void test2() {
        User user = userService.findById("a1");
        assertThat(user).isNotNull();

        assertThat(user.getId()).isNotNull();
        assertThat(user.getPassword()).isNotNull();
        assertThat(user.getNickname()).isNotNull();
        assertThat(user.getName()).isNotNull();
        assertThat(user.getPhone()).isNotNull();

    }

}
