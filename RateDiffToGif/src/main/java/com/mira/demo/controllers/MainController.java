package com.mira.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mira.demo.clients.GiphyClient;
import com.mira.demo.clients.OpenExchangerRateClient;
import com.mira.demo.controllers.exceptions.GifNotFoundException;
import com.mira.demo.controllers.exceptions.RateBadRequestException;
import com.mira.demo.controllers.exceptions.RateNotFoundException;
import com.mira.demo.resources.Gif;
import com.mira.demo.resources.OriginalGif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/")
public class MainController {

    @Autowired
    private OpenExchangerRateClient rateClient;
    @Autowired
    private GiphyClient giphyClient;

    @Value("${controller.date_format}")
    private String DATE_FORMAT;

    @Value("${controller.negative_result}")
    private String NEGATIVE_RESULT;

    @Value("${controller.positive_result}")
    private String POSITIVE_RESULT;

    @Value("${controller.base_regex}")
    String regex;

    @GetMapping("{code}")
    public EntityModel<Gif> getGif(@PathVariable("code") String code) {

        if (!code.matches(regex)) {
            throw new RateBadRequestException(code);
        }

        BigDecimal difference;
        try {
            difference = getDifferenceBetweenLastRates(code);
        } catch (NullPointerException ex) {
            throw new RateNotFoundException(code);
        }

        // в зависимости от разницы найдем gif с нужным тегом
        String json;
        try {
            json = difference.compareTo(BigDecimal.ZERO) > 0
                    ? giphyClient.getRandomGif(POSITIVE_RESULT).toJSONString()
                    : giphyClient.getRandomGif(NEGATIVE_RESULT).toJSONString();
        } catch (NullPointerException ex) {
            throw new GifNotFoundException();
        }

        Gif gif = getGifInfoFromJson(json);

        return EntityModel.of(gif,
                linkTo(methodOn(MainController.class).getGif(code)).withSelfRel());
    }
    
    private BigDecimal getDifferenceBetweenLastRates(String code) throws NullPointerException{
        // получим вчерашнюю дату в нужном для нас формате
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String yesterdayDate = dateFormat.format(cal.getTime());

        BigDecimal rateToday = rateClient.getLatestRate().getRates().get(code);
        BigDecimal rateYesterday = rateClient.getYesterdayRate(yesterdayDate).getRates().get(code);
        return rateToday.subtract(rateYesterday);
    }

    private Gif getGifInfoFromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Gif gif = new Gif();
        try {
            JsonNode node = objectMapper.readTree(json).get("data");
            gif.setUrl(node.get("embed_url").asText());
            gif.setOriginalGif(objectMapper.treeToValue(node.get("images").get("original"), OriginalGif.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return gif;
    }

}
