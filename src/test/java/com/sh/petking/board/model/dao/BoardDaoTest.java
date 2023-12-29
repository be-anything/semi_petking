package com.sh.petking.board.model.dao;

import com.sh.petking.board.model.entity.Board;
import com.sh.petking.board.model.service.BoardService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardDaoTest {

    static final int limit = 10; // 페이지당 게시글 수

    BoardDao boardDao;
    SqlSession session;
    @BeforeEach
    void setUp(){
        this.boardDao = new BoardDao();
        this.session = getSqlSession();
    }

    @AfterEach
    void tearDown(){
        this.session.rollback();
        this.session.close();
    }

    @DisplayName("게시판 전체를 조회 할 수 있습니다.")
    @Test
    void test1(){
        List<Board> boards = boardDao.findAll(session);
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
    void test2_1(long id) {
        Board board = boardDao.findById(session, id);

        assertThat(board)
                .isNotNull()
                .satisfies((_board) -> {
                    assertThat(_board.getId()).isNotZero();
                    assertThat(_board.getUserId()).isNotNull();
                    assertThat(_board.getBoardType()).isNotNull();
                    assertThat(_board.getBoardTitle()).isNotNull();
                    assertThat(_board.getBoardContent()).isNotNull();
                    assertThat(_board.getRegDate()).isNotNull();
                    assertThat(_board.getBoardAttr()).isGreaterThanOrEqualTo(1);
                    assertThat(_board.getViewCount()).isGreaterThanOrEqualTo(0);
                });
    }

    @DisplayName("존재하지 않는 게시글 하나 조회")
    @ParameterizedTest
    @ValueSource(longs = {100000000L, 9999999L})
    void test2_2(long id){
        Board board = boardDao.findById(session, id);
        assertThat(board).isNull();
    }

    @DisplayName("게시글 등록")
    @Test
    void test3(){
        Board board = new Board(0, null, "카테고리", "제목", "내용", null, 0, 0);
        int result = boardDao.insertBoard(session, board);

        assertThat(result).isGreaterThan(0);
    }

    @DisplayName("게시글 수정")
    @ParameterizedTest
    @MethodSource("boardIdProvider")
    void test4(long id){
        Board board = boardDao.findById(session, id);
        assertThat(board).isNotNull();
        String newBoardTitle = "새 제목";
        String newBoardContent = "새 내용";
        board.setBoardTitle(newBoardTitle);
        board.setBoardContent(newBoardContent);
        int result = boardDao.updateBoard(session, board);
        assertThat(result).isGreaterThan(0);
        Board boardUpdated = boardDao.findById(session, id);
        assertThat(boardUpdated).satisfies((b) -> {
            assertThat(b.getBoardTitle()).isEqualTo(newBoardTitle);
            assertThat(b.getBoardContent()).isEqualTo(newBoardContent);
        });
    }

    @DisplayName("게시글 삭제")
    @ParameterizedTest
    @MethodSource("boardIdProvider")
    void test5(long id){
        Board board = boardDao.findById(session, id);
        assertThat(board).isNotNull();
        int result = boardDao.deleteBoard(session, id);
        assertThat(result).isGreaterThan(0);
        Board boardDeleted = boardDao.findById(session, id);
        assertThat(boardDeleted).isNull();
    }

    @DisplayName("전체 게시글수 조회")
    @Test
    void test6() {
        int totalCount = boardDao.getTotalCount(session);
        assertThat(totalCount).isNotNegative();
    }

//    @DisplayName("게시글 페이징 조회")
//    @ParameterizedTest
//    @MethodSource("pageNoProvider")
//    void test7(int page) {
//        Map<String, Object> param = Map.of("page", page, "limit", limit);
//        List<Board> boards = boardDao.findAll(session, param);
//        assertThat(boards)
//                .isNotNull()
//                .isNotEmpty()
//                .size().isLessThanOrEqualTo(limit);
//    }
//
//    public static Stream<Integer> pageNoProvider() {
//        BoardService boardService = new BoardService();
//        SqlSession session = getSqlSession();
//        int totalCount = boardService.getTotalCount();
//        int totalPage = (int) Math.ceil((double) totalCount / limit);
//        return IntStream.range(1, totalPage).boxed(); // 1 부터 total페이지까지를 요소로 하는 Stream생성
//    }
//    public static Stream<Arguments> boardIdProvider() {
//        BoardService boardService = new BoardService(); // non-static fixture를 사용할 수 없다.
//        List<Board> boards = boardService.findAll();
//        return Stream.of(Arguments.arguments(boards.get(0).getId()));
//    }

}
