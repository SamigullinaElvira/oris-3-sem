package org.example.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class Post {
    private Long id;
    private String title;
    private String text;
    private Long userID;
    private String imgName;
    private byte[] img;
}
