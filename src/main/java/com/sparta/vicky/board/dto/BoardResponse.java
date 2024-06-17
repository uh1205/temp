package com.sparta.vicky.board.dto;

import com.sparta.vicky.board.entity.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponse {

    private Long id;
    private String title;
    private String region;
    private String address;
    private String content;
    private Long userId;
    private int likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.region = board.getRegion();
        this.address = board.getAddress();
        this.content = board.getContent();
        this.userId = board.getUser().getId();
        this.likes = board.getLikeCount();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }

}
