package com.gavriale.finstream.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "market_ticker_latest")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MarketTickerLatest {
    @Id
    @Column(length = 32, nullable = false)
    private String symbol;

    @Column(nullable = false, precision = 18, scale = 6)
    private BigDecimal price;

    @Column(name = "tickTimestamp", nullable = false)
    private Instant tickTimestamp;

    private Long volume;

    @Column(length = 64)
    private String exchange;

    @Column(name = "source_event_id", nullable = false, updatable = false, unique = true)
    private String sourceEventId;
}
