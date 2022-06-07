package com.mira.demo.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    private String disclaimer;
    private String license;
    private Date timestamp;
    private String base;
    private Map<String, BigDecimal> rates;
}
