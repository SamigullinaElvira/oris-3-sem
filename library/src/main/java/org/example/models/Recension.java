package org.example.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Recension {
    private Long id;
    private String text;
    private Long postID;
    private Long userID;
}
