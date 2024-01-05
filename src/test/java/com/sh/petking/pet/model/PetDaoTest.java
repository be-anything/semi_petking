package com.sh.petking.pet.model;

import com.sh.petking.pet.model.dao.PetDao;
import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.vo.PetVo;
import com.sh.petking.user.model.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.*;

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

    @DisplayName("전체 반려동물 조회")
    @Test
    public void test2() {
        // given
        // when
        int totalCount = petDao.getTotalCount(session);
        // then
        assertThat(totalCount).isNotNegative(); // 음수가 아니어야 한다. 0이상
    }

    @DisplayName("반려동물 등록")
    @Test
    void test3() {
        // given
        // when
        Pet pet = new Pet("wjdgy1", "밀크", 3, "F", "X", null);
        int result = petDao.insertPet(session, pet);
        // then
        assertThat(result).isGreaterThan(0);
    }

}
