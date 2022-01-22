# Introduction
In the [Python Data Analytics](https://github.com/jarviscanada/jarvis_data_eng_HomaAlmasieh/tree/master/python_data_analytics) project
, we have successfully performed a data analytics solution for the London Gift Shop (LGS) marketing team
with Jupyter Notebook.

In the current project, the marketing team of LGS decided to re-architect the business solution using Apache
Spark, since the existing Jupyter Notebook runs on a single machine and it would not be able to handle a large
dataset. They performed a framework that allows big data processing in a cluster using Spark running environment-
Databricks (on Azure).

## Technologies Used:
Data Sources: online_retail_II.csv

Software: Azure Databrick Notebook, Git, and Linux(CentOS7)

Library: pyspark

# Azure Databricks Implementation

## Description of Dataset and Attributes
The dataset consists of two years' worth of transaction data stored in an online_retail_II.csv file and the attributes
are shown in the following table:
| Attribute     | Data Type   | Description                                      |
| :---------    | :---------  | :---------                                       |
| `Invoice`     | `string`    | `Identifies the invoice the record belongs to`   |
| `StockCode`   | `string`    | `Identifies the the item purchased`              |
| `Description` | `string`    | `Describes the purchased item`                   |
| `Quantity`    | `integer`   | `Counts how many items of the same stock_code were purchased`   |
| `InvoiceDate` | `timestamp` | `The date and time that the transaction occurred`               |
| `Price`       | `double`    | `Unit price of item purchased`                   |
| `Customer ID` | `double`    | `Identifies the customer who made the purchased` |
| `Country`     | `string`    | `The country the customer lives in`              |

## Description of Data Analysis
The code for data analysis is accessible [here](https://github.com/jarviscanada/jarvis_data_eng_HomaAlmasieh/blob/develop/spark/notebook/RetailDataAnalyticsWithPySpark.ipynb).
- Data Ingestion: We exported table data to a csv file and the uploaded the csv file to DBFS. For
big dat, we can use JDBC using firewall, Azure Storage, or other ETL tools(e.g. Azure Data factory)
- Data Analysis: In this analysis, we dive into the business solutions of LGT to help them
to increase their revenue. In this regard, the analysis can be performed to determine the followings:
  - Total Invoice Amount
  - Monthly Placed and Canceled Orders
  - Monthly Sales
  - Monthly Active Users
  - New and Existing Users
- RFM Analysis: We performed RFM analysis to determine Customer Distribution
Based on Recency, Frequency and Monetary.

# Improvements
- Add more features to the dataset which affect the customer segmentation such as seasonal or occasional choices
- Create machine learning models to predict the behavior of the customers, sales growth and the company's revenue in the future
- Use the most updated data and larger samples to perform more accurate analysis