package com.sparta.vicky.board.entity;

import com.sparta.vicky.board.dto.BoardRequest;
import com.sparta.vicky.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int likeCount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 생성자
     */
    public Board(BoardRequest requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.likeCount = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.user = user;
    }

    /**
     * 검증 메서드
     */
    public void verifyUser(Long userId) {
        if (!this.user.getId().equals(userId)) {
            throw new IllegalArgumentException("해당 게시물의 작성자가 아닙니다.");
        }
    }

    /**
     * 수정 메서드
     */
    public void update(BoardRequest requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 좋아요 비즈니스 로직
     */
    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

}
