package com.gavriale.finstream.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "tickers",
        uniqueConstraints = @UniqueConstraint(name = "ux_tickers_exchange_symbol",
                columnNames = {"exchange", "symbol"}))

public class Ticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String symbol;

    @Column(length = 255)
    private String name;

    @Column(nullable = false, length = 64)
    private String exchange;

    @Column(length = 16)
    private String currency;

    @Column(name = "last_price", precision = 18, scale = 6)
    private BigDecimal lastPrice;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (updatedAt == null) updatedAt = OffsetDateTime.now();
        normalize();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = OffsetDateTime.now();
        normalize();
    }

    private void normalize() {
        // Keep a consistent canonical form in DB (optional but helpful)
        if (symbol != null) symbol = symbol.trim();
        if (exchange != null) exchange = exchange.trim();
        if (currency != null) currency = currency.trim();
    }
}
