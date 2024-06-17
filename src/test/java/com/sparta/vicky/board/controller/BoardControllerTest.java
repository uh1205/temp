package com.sparta.vicky.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.vicky.board.dto.BoardRequest;
import com.sparta.vicky.board.entity.Board;
import com.sparta.vicky.board.service.BoardService;
import com.sparta.vicky.config.WebSecurityConfig;
import com.sparta.vicky.mvc.MockSpringSecurityFilter;
import com.sparta.vicky.security.UserDetailsImpl;
import com.sparta.vicky.user.dto.SignupRequest;
import com.sparta.vicky.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {
                BoardController.class
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
class BoardControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BoardService boardService;

    @Autowired
    MockMvc mvc;

    Principal mockPrincipal;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    @Test
    void createBoard() throws Exception {
        // given
        User user = this.setupTestUser();

        BoardRequest request = BoardRequest.builder()
                .title("title")
                .region("region")
                .address("address")
                .content("content")
                .build();

        Board board = new Board(request, user);

        given(boardService.createBoard(any(), any())).willReturn(board);

        // when - then
        mvc.perform(post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
    // Spring auto Rest Doc

    private User setupTestUser() {
        SignupRequest request = SignupRequest.builder()
                .username("testUsername")
                .password("testTEST123!")
                .name("testUser")
                .email("test@gmail.com")
                .introduce("introduce")
                .build();

        User testUser = new User(request, request.getPassword()); // 비밀번호 인코딩 생략

        mockPrincipal = new UsernamePasswordAuthenticationToken(
                new UserDetailsImpl(testUser),
                ""
        );

        return testUser;
    }

}