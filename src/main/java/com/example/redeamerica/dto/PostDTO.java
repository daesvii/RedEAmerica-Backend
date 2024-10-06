package com.example.redeamerica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private String content;
    private String mediaUrl;
    private String category;
    private String country;
}
