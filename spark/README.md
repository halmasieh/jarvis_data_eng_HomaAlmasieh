# Introduction
In the [Python Data Analytics](https://github.com/jarviscanada/jarvis_data_eng_HomaAlmasieh/blob/develop/spark/assets/Architecture-Databricks2.PNG) project
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
# Databricks Implementation
- Describe the dataset and your analytics work (make sure you create a link to your ipynb)
- Describe the architecture (e.g. Databricks, DBFS, Azure, Azure storage, Hive Metastore, PySpark, data flow, etc..)
- Draw an architecture diagram


## Project Architecture
<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/assets/Architecture_Data_Analytics.PNG" width="750" height="500" alt=""/>
</p>

The LGS IT team provides a high-level system design to store the transactional data which manages by their own Azure system and
the Jarvis team does not have the access permission to use the Azure environment. Instead, they provided a dumped transaction dataset
between 01/12/2009 and 09/12/2011 which is loaded to a
PostgreSQL database to find the solution for business questions using data analysis tools.


## Data Analytics and Wrangling
The code for data analysis is accessible [here](https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/python_data_wrangling/retail_data_analytics_wrangling.ipynb).

In this analysis, we dive into the business solutions of LGT to help them to increase their revenue.
In this regard, the analysis can be performed as the following steps:
- Create distribution for the first 85 quantiles of the invoice amount data and plot that for finding the outliers as follows:

<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/python_data_wrangling/data/invoice_amount.PNG" width="650" height="300" alt=""/>
</p>

- Plot monthly placed versus the canceled orders

We can observe from the bar graph that the highest placement order is occurred in November 2011 and the same behaviour in November 2010. This could be a sign of a trend and if we would have available data for the
following years, it is quite predictable. Similarly,
the highest rate of cancelled orders has occurred in the two last months of the year.

<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/python_data_wrangling/data/placement_vs_canceled.PNG" width="700" height="300" alt=""/>
</p>

- Determine the monthly sales to get the rate of growth

As it is shown in the following graph, sales growth begins in late summer, July and August, and continues to reach its maximum value in November.
Then it will experience a downward trend by the end of the year.

<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/python_data_wrangling/data/monthly_sales.PNG" width="650" height="300" alt=""/>
</p>

<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/python_data_wrangling/data/monthly_growth.PNG" width="650" height="300" alt=""/>
</p>

- Create a merged dataset for active users (new and  existing users), and plot a bar chart to compare them for each month

As it is shown in the following graph,  the number of active users has the same trend as monthly sales and for the number
of new users that added to the existing customers, there is no significant difference in various months. LGS experiences the highest and
lowest number of new users in December 2009 and 2011, respectively.

<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/python_data_wrangling/data/new_users_existing.PNG" width="650" height="300" alt=""/>
</p>

- Find RFM for analyzing customer value and preparing customer segmentation which has a direct and close relation
with increasing revenue. This strategy allows the data analyst to divide the customers into different groups depending on
Recency, Frequency, and Monetary.

<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/python_data_wrangling/data/customer_per_segment.PNG" width="650" height="300" alt=""/>
</p>

As you observe in the above graph, the customers in the category of Champion and loyal customers are extremely loyal and responsible for
the large portion of the company revenue. Therefore, they are highly valuable, and they should be taken into consideration to keep them engaged by providing them more rewards.
In addition, the company needs to spend time for engaging the Can't Lose and New Customers categories.

# Improvements
- Add more features to the dataset which affect the customer segmentation such as seasonal or occasional choices
- Create machine learning models to predict the behavior of the customers, sales growth and the company's revenue in the future
- Use the most updated data and larger samples to perform more accurate analysis