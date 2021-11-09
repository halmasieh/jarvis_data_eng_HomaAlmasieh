# Introduction
A UK-based online store called London Gift Shop (LGS) sells gift-ware and most of the companiy's customers are
wholesales. This company has been operating online for about 10 years.
The owner's concern is that their business has not grown much in recent years. In this regard, the LGS marketing team
is looking for solutions that help economic prosperity bu using new technologies and better understanding of customer needs.
The Jarvis consulting data engineering team went to their aid for delivering a proof of concept (PoC) project.
It helps the LGS marketing by analyzing customer shopping behaviour. We used the transaction dataset in a csv file 
from 01/12/2009 to 09/12/2011. The provided data is stored in a PostgreSQL database which is provisioned using a docker 
container, and then it is connected to the Jupyter Notebook to answer all the business questions. We created plots 
to visualize the answers. 
## Technologies Used: 
Data Sources: online_retail_II.csv, and retail.sql

Software: Jupyter Notebook, PostgresSQL, Git, Docker and Linux(CentOS7)

Library: pandas, numpy, matplotlib, sqlalchemy, scipy, seaborn, and datetime
# Implementaion


## Project Architecture
<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/python_data_analytics/assets/Architecture_Data_Analytics.PNG" width="400" height="400" alt=""/>
</p>

The LGS IT team provides a high-level system design to store the transactional data which manages by their own Azure system and
the Jarvis team does not have the access permission to use the Azure environment. Instead, they provided a dumped transaction dataset
between 01/12/2009 and 09/12/2011 into a SQL file which is accessible to the Jarvis team. Then the data is loaded to a 
PostgreSQL database to find the solution for business questions using data analysis tools.  


## Data Analytics and Wrangling
- Create a link that points to your Jupyter notebook (use the relative path `./retail_data_analytics_wrangling.ipynb`)
- Discuss how would you use the data to help LGS to increase their revenue (e.g. design a new marketing strategy with data you provided)

# Improvements
- List three improvements that you want to do if you got more time