--Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)
SELECT
   cpu_number,
   id AS "host_id",
   total_mem
FROM
   host_info
GROUP By
   cpu_number,
   host_id
ORDER BY
   cpu_number ASC,
   total_mem  DESC;

--Create  a function that returns the timestamp rounded to the nearest 5 minute interval.
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

--Verify round5 function
SELECT
host_id, timestamp, round5(timestamp)
FROM host_usage;

--Create a function that gets free_memory and host_id and returns used memory percentage.
CREATE FUNCTION used_memory_percentage(free_memory INTEGER, host_id INTEGER) RETURNS NUMERIC AS
$$
DECLARE
    total_memory INTEGER;
BEGIN
    SELECT (total_mem / 1000)
    INTO total_memory
    FROM host_info
    WHERE host_info.id = host_usage.host_id;
    RETURN (total_memory - memory_free) / total_memory * 100 ;
END;
$$
    LANGUAGE PLPGSQL;

--Average used memory in percentage over 5 minutes interval for each host.
select
	host_usage.host_id,
	host_info.hostname,
	round5(
      CAST(host_usage.timestamp AS timestamp
   ) As timestamp,
   AVG(used_memory_percentage(memory_free, host_id)
   ) AS avg_used_mem_percentage
FROM
   host_usage
GROUP BY
   host_usage.host_id,
   round5(
      CAST(timestamp As timestamp)
   )
   LIMIT 5;

   --Detect host failure for less than three data points within 5-min interval.
SELECT
  host_id,
  date_trunc('hour', timestamp) + date_part('minute', timestamp)::int / 5 * interval '5 min' AS ts,
  COUNT(*) as num_data_points
FROM
  host_usage
GROUP BY
  host_id, ts
HAVING
  COUNT(*) < 3;