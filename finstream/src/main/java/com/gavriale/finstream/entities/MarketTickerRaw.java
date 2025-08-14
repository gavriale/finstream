package com.gavriale.finstream.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        name = "market_ticker_row",
        indexes = {
                @Index(name = "ix_mtr_symbol_ts", columnList = "symbol,tickTimestamp DESC")
        }
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MarketTickerRaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
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