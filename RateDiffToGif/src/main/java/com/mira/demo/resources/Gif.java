package com.mira.demo.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gif {
    private String url;
    private OriginalGif originalGif;
}
