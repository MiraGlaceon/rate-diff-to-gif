package com.mira.demo.controllers;

import com.mira.demo.clients.GiphyClient;
import com.mira.demo.clients.OpenExchangerRateClient;
import com.mira.demo.controllers.exceptions.GifNotFoundException;
import com.mira.demo.controllers.exceptions.RateBadRequestException;
import com.mira.demo.controllers.exceptions.RateNotFoundException;
import com.mira.demo.resources.Gif;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerTest {

    @Autowired
    MainController mainController;

    @SpyBean
    OpenExchangerRateClient rateClient;

    @SpyBean
    GiphyClient giphyClient;

    private final String RUB = "RUB";

    @Test
    void getGifTest() {
        Gif gif = mainController.getGif(RUB).getContent();
        Assertions.assertThat(gif).isNotNull();
        Mockito.verify(rateClient, Mockito.times(1)).getLatestRate();
        Mockito.verify(rateClient, Mockito.times(1)).getYesterdayRate(ArgumentMatchers.anyString());
        Mockito.verify(giphyClient, Mockito.times(1)).getRandomGif(ArgumentMatchers.anyString());
    }

    @Test
    void giphyClientErrorTest() {
        Mockito.doReturn(null).when(giphyClient).getRandomGif(ArgumentMatchers.anyString());
        Assertions.assertThatExceptionOfType(GifNotFoundException.class)
                .isThrownBy(() -> mainController.getGif(RUB));
    }

    @Test
    void openExchangerRateErrorTest() {
        String incorrectCode = "AAA";
        Assertions.assertThatExceptionOfType(RateNotFoundException.class)
                .isThrownBy(() -> mainController.getGif(incorrectCode));
    }

    @Test
    void getGifBadRequestErrorTest() {
        String incorrectInput = "rub";
        Assertions.assertThatExceptionOfType(RateBadRequestException.class)
                .isThrownBy(() -> mainController.getGif(incorrectInput));
    }
}