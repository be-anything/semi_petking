package com.sh.petking.room.model.dao;


import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 혜진
 * 객실 첨부 사진 test
 */
public class RoomAttachDaoTest
{
    private RoomDao roomDao;
    private SqlSession session;

    @BeforeEach
    public void setUp()
    {
        this.roomDao = new RoomDao();
        this.session = getSqlSession();
    }

    @AfterEach
    public void tearDown()
    {
        this.session.rollback();//test라 실제 반영하지 않기 위해
        this.session.close();//세션은 사용후 종료
    }

    @DisplayName("roomDao,SqlSession은 null이 아니다.")
    @Test
    public void test1()
    {
        assertThat(roomDao).isNotNull();
        assertThat(session).isNotNull();
    }
}
