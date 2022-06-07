package com.mira.demo.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginalGif {
    private String url;
    private String width;
    private String height;
    private String size;
    private String frames;
    private String hash;
    private String mp4; //< Ссылка на .MP4 формат
    private String mp4_size; //< Размер в байтах для .MP4
    private String webp; //< Ссылка на .webp формат
    private String webp_size; //< Размер в байтах для .webp
}
