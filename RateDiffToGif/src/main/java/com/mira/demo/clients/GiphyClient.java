package com.mira.demo.clients;

import net.minidev.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphy", url = "${giphy.url}")
public interface GiphyClient {

    @GetMapping("/gifs/random?${giphy.key_param}")
    JSONObject getRandomGif(@RequestParam("tag") String tag);
}
