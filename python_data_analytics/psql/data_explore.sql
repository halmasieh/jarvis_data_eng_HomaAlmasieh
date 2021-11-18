-- Show table schema 
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
SELECT count(*) FROM retail;

-- Count number of clients
SELECT count(distinct customer_id) FROM retail;

-- Determine the invoice date range
SELECT MAX(invoice_date), MIN(invoice_date) FROM retail;

-- Determine the number of SKU/merchants(stock code)
SELECT count(distinct stock_code) FROM retail;

-- Calculate average invoice amount excluding invoices with a negative amount (e.g. canceled orders have negative amount)
SELECT AVG(qu.total)
FROM (
     SELECT invoice_no, SUM(quantity * unit_price) AS total
     FROM retail
     GROUP By invoice_no
     HAVING SUM(quantity * unit_price) > 0
     ) AS qu;

-- Calculate total revenue (e.g. sum of unit_price * quantity)
SELECT SUM(quantity*unit_price) AS "Revenue"
From retail;

-- Calculate total revenue by YYYYMM
SELECT CAST(EXTRACT(YEAR FROM invoice_date)  As INTEGER) * 100 + CAST(EXTRACT(MONTH FROM invoice_date)  As INTEGER) AS "yyyymm", SUM(quantity*unit_price) AS "sum"
FROM retail
GROUP BY yyyymm
ORDER BY yyyymm ASC;


