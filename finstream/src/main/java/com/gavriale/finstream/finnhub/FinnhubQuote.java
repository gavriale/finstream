package com.gavriale.finstream.finnhub;


import java.math.BigDecimal;

public record FinnhubQuote(
        BigDecimal c,  // current price
        BigDecimal h,  // high
        BigDecimal l,  // low
        BigDecimal o,  // open
        BigDecimal pc, // previous close
        long t         // epoch seconds
) {}


