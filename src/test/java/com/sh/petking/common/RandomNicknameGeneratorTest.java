package com.sh.petking.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNicknameGeneratorTest {

    @Test
    public void test1(){
        String nickname = RandomNicknameGenerator.createNickname();
        System.out.println(nickname);

        assertThat(nickname).isNotNull();
        assertThat(nickname).contains(" ");
    }
}
