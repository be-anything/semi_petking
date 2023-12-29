package com.sh.petking.board.model.service;

import com.sh.petking.board.model.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardServiceTest {
    private BoardService boardService;

    @BeforeEach
    void setUp(){
        this.boardService = new BoardService();
    }

    @DisplayName("boardService는 null이 아닙니다.")
    @Test
    void setUpTest(){
        assertThat(boardService).isNotNull();
    }

    @DisplayName("게시판 전체를 조회 할 수 있습니다.")
    @Test
    void test1(){
        List<Board> boards = boardService.findAll();
        System.out.println(boards);
        assertThat(boards)
                .isNotNull()
                .allSatisfy((board) -> {
                    assertThat(board.getId()).isNotZero();
                    assertThat(board.getUserId()).isNotNull();
                    assertThat(board.getBoardType()).isNotNull();
                    assertThat(board.getBoardTitle()).isNotNull();
                    assertThat(board.getBoardContent()).isNotNull();
                    assertThat(board.getRegDate()).isNotNull();
                    assertThat(board.getBoardAttr()).isGreaterThanOrEqualTo(1);
                    assertThat(board.getViewCount()).isGreaterThanOrEqualTo(0);
                });
    }

    @DisplayName("게시판 하나를 조회 할 수 있습니다.")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void test2(Long id){
        Board board = boardService.findById(id);
        assertThat(board).isNotNull();
        assertThat(board.getId()).isNotZero();
        assertThat(board.getUserId()).isNotNull();
        assertThat(board.getBoardType()).isNotNull();
        assertThat(board.getBoardTitle()).isNotNull();
        assertThat(board.getBoardContent()).isNotNull();
        assertThat(board.getRegDate()).isNotNull();
        assertThat(board.getBoardAttr()).isGreaterThanOrEqualTo(1);
        assertThat(board.getViewCount()).isGreaterThanOrEqualTo(0);
    }
}
