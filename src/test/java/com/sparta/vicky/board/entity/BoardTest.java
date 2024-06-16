package com.sparta.vicky.board.entity;

import com.sparta.vicky.board.dto.BoardRequest;
import com.sparta.vicky.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardTest {

    @Mock
    User user;

    @Mock
    BoardRequest boardRequest;

    @InjectMocks
    Board board;

    @Test
    void verifyUser() {
        // given
        when(board.getUser().getId()).thenReturn(1L);

        // when
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> board.verifyUser(99L)
        );

        // then
        assertEquals("해당 게시물의 작성자가 아닙니다.", exception.getMessage());
    }

    @Test
    void update() {
        // given
        BoardRequest request = BoardRequest.builder()
                .title("newTitle")
                .content("newContent")
                .region("newRegion")
                .address("newAddress")
                .build();

        // when
        board.update(request);

        // then
        assertEquals("newTitle", board.getTitle());
        assertEquals("newContent", board.getContent());
        assertEquals("newRegion", board.getRegion());
        assertEquals("newAddress", board.getAddress());
    }

    @Test
    void increaseLikeCount() {
        // given
//        when(board.getLikeCount()).thenReturn(0);

        // when
        board.increaseLikeCount();

        // then
        assertEquals(1, board.getLikeCount());

    }

    @Test
    void decreaseLikeCount() {
        // given
//        when(board.getLikeCount()).thenReturn(1);

        // when
        board.decreaseLikeCount();

        // then
        assertEquals(-1, board.getLikeCount());
    }

}