package com.gavriale.finstream.finnhub;

import java.math.BigDecimal;
import java.time.Instant;

public record MarketTick(
        String symbol,
        BigDecimal price,
        Instant tickTimestamp,
        Long volume,
        String exchange
) {}
