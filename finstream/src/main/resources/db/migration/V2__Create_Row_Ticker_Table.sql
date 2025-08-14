CREATE TABLE market_ticker_row (
  id BIGSERIAL PRIMARY KEY,
  symbol VARCHAR(32) NOT NULL,
  price NUMERIC(18,6) NOT NULL,
  tickTimestamp TIMESTAMPTZ NOT NULL,
  volume BIGINT,
  exchange VARCHAR(64),
  source_event_id text
);