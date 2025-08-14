package com.gavriale.finstream.controllers;


import com.gavriale.finstream.entities.MarketTickerRaw;
import com.gavriale.finstream.finnhub.FinnhubClient;
import com.gavriale.finstream.finnhub.FinnhubQuote;
import com.gavriale.finstream.finnhub.MarketTick;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finnhub")
@RequiredArgsConstructor
public class FinnhubController {
    private final FinnhubClient client;

    @GetMapping("/quote/raw")
    public FinnhubQuote raw(@RequestParam String symbol) {
        return client.getQuoteRaw(symbol);
    }

    @GetMapping("/quote")
    public MarketTick normalized(@RequestParam String symbol) {
        return client.getQuoteNormalized(symbol);
    }
}
