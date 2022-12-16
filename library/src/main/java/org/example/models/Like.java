package org.example.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Like {
    private Long id;
    private Long userId;
    private Long postId;
}
