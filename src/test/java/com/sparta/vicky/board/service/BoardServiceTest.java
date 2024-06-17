package com.sparta.vicky.board.service;

import com.sparta.vicky.board.dto.BoardRequest;
import com.sparta.vicky.board.entity.Board;
import com.sparta.vicky.board.repository.BoardRepository;
import com.sparta.vicky.user.entity.User;
import com.sparta.vicky.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    BoardRepository boardRepository;

    @Mock
    UserService userService;

    @Mock
    User user;

    @Test
    void updateBoard() {
        // given
        Long userId = 1L;
        Long boardId = 1L;

        BoardRequest createRequest = BoardRequest.builder()
                .title("title")
                .content("content")
                .build();

        BoardRequest updateRequest = BoardRequest.builder()
                .title("newTitle")
                .content("newContent")
                .build();

        Board board = new Board(createRequest, user);

        given(board.getUser().getId()).willReturn(userId);
        given(boardRepository.findById(boardId)).willReturn(Optional.of(board));

        BoardService boardService = new BoardService(boardRepository, userService);

        // when
        Board newBoard = boardService.updateBoard(boardId, updateRequest, user.getId());

        // then
        assertEquals("newTitle", newBoard.getTitle());
        assertEquals("newContent", newBoard.getContent());
    }

}