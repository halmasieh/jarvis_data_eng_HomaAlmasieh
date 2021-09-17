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

--Average used memory in percentage over 5 minutes interval for each host.
SELECT
  id,
  hostname,
  date_trunc('hour', hu.timestamp) + date_part('minute', hu.timestamp)::int / 5 * interval '5 min' AS interval,
  AVG((total_mem - memory_free) * 100 / total_mem)::int AS percentage_used
FROM
  host_info hi JOIN
    host_usage hu ON
    hi.id = hu.host_id
GROUP BY
  id,
  interval
ORDER BY
  id,
  interval;

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