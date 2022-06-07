package com.mira.demo.clients;

import com.mira.demo.resources.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "openExchangerRate", url = "${rate.url}")
public interface OpenExchangerRateClient {

    @GetMapping("/latest.json?${rate.key_param}&${rate.base}")
    Rate getLatestRate();

    @GetMapping("historical/{date}.json?${rate.key_param}&${rate.base}")
    Rate getYesterdayRate(@PathVariable("date") String date);

}
