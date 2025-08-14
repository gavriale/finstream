package com.gavriale.finstream.finnhub;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class FinnhubClient {
    private final WebClient finnhubWebClient;
    private final FinnhubProps props;

    public FinnhubQuote getQuoteRaw(String symbol) {
        return finnhubWebClient.get()
                .uri(uri -> uri.path("/quote")
                        .queryParam("symbol", symbol)
                        .queryParam("token", props.apiKey())
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), r -> r.createException())
                .onStatus(status -> status.is5xxServerError(), r -> r.createException())
                .bodyToMono(FinnhubQuote.class)
                .retryWhen(
                        Retry.backoff(2, Duration.ofMillis(250))  // light retry only for transient issues
                                .filter(ex -> !(ex instanceof org.springframework.web.reactive.function.client.WebClientResponseException))
                )
                .block(); // fine for this debug step
    }

    public MarketTick getQuoteNormalized(String symbol) {
        FinnhubQuote q = getQuoteRaw(symbol);
        if (q == null || q.c() == null) {
            throw new IllegalStateException("Empty Finnhub quote for " + symbol);
        }
        return new MarketTick(
                symbol,
                q.c(),
                Instant.ofEpochSecond(q.t()),
                null,         // /quote has no per-tick volume
                "FINNHUB"
        );
    }
}
