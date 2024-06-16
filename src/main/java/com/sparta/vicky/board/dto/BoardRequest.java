package com.sparta.vicky.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String region;

    @NotBlank
    private String address;

    @NotBlank
    private String content;

}
