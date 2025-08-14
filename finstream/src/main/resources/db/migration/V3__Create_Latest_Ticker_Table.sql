CREATE TABLE market_ticker_latest (
    symbol VARCHAR(32) PRIMARY KEY,
    price NUMERIC(18,6) NOT NULL,
    tickTimestamp TIMESTAMPTZ NOT NULL,
    volume BIGINT,
    exchange VARCHAR(64)
);