package com.sparta.vicky.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
