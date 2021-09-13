-- connect to database host_agent
\c host_agent

-- Create the PUBLIC.host_info table if is not exist
CREATE TABLE IF NOT EXISTS  PUBLIC.host_info
 (
 id                 SERIAL       NOT NULL   PRIMARY KEY,
 hostname           VARCHAR      NOT NULL   UNIQUE,
 cpu_number         INT          NOT NULL,
 cpu_architecture   VARCHAR      NOT NULL,
 cpu_model          VARCHAR      NOT NULL,
 cpu_mhz            REAL         NOT NULL,
 L2_cache           INT          NOT NULL,
 total_mem          INT          NOT NULL,
 timestamp          TIMESTAMP    NOT NULL
 );

-- Create the PUBLIC.host_usage if is not exist
  (
  timestamp          TIMESTAMP    NOT NULL,
  host_id            SERIAL       NOT NULL,
  memory_free        INT          NOT NULL,
  cpu_idle           INT          NOT NULL,
  cpu_kernel         INT          NOT NULL,
  disk_io            INT          NOT NULL,
  disk_available     INT          NOT NULL,
  FOREIGN KEY (host_id) REFERENCES PUBLIC.host_info (id)
  );

  -- DML
  -- INSERT statement
  INSERT INTO host_usage ('timestamp', memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
  VALUES ('$timestamp', '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');