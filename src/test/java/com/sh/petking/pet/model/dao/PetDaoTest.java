package com.sh.petking.pet.model.dao;

import com.sh.petking.pet.model.entity.Pet;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class PetDaoTest {
    private PetDao petDao;
    private SqlSession session;

    @BeforeEach
    public void beforeEach() {
        this.petDao = new PetDao();
        this.session = getSqlSession();
    }
    @AfterEach
    public void afterEach() {
        this.session.rollback();
        this.session.close();
    }

    @DisplayName("Pet객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(petDao).isNotNull();
    }
}

