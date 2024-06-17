package com.sparta.vicky.board.dto;

import com.sparta.vicky.board.entity.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponse {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private int likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.userId = board.getUser().getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.likes = board.getLikeCount();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }

}
