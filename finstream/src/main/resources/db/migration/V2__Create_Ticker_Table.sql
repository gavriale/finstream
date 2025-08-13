CREATE TABLE IF NOT EXISTS tickers (
    id         UUID PRIMARY KEY,
    symbol     VARCHAR(32) NOT NULL UNIQUE,
    name       VARCHAR(255),
    exchange   VARCHAR(64),
    currency   VARCHAR(16),
    last_price NUMERIC(18,6),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);