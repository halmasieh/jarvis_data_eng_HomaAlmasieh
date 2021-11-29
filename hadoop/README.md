# Hadoop Project
Table of contents
* [Introduction](#Introduction)
* [Technologies Used](#TechnologiesUsed)
* [Hadoop Cluster](#HadoopCluster)
* [Hive Project](#HiveProject)
* [Improvements](#Improvements)

# Introduction
The Hadoop project is processed for the purpose of data analytics on big data. 
We evaluated core Hadoop components, including MapReduce, HDFS, and YARN. In this regard, three node 
Hadoop clusters are provisioned with GDP DataProc service, containing one master node and two worker nodes.
The data is stored in Hive tables and analyzed using HiveQl queries and Zeppelin Notebook . 
However, HQL queries were optimized using Hive partitions and Columnar file optimizations to solve 
the business problems. The data is tested using table views and queries.

## Technologies Used:
Data Sources: Google Public Data (hosted on Google BigQuery), [worldbank_wdi](https://www.notion.so/jarvisdev/Setup-Hadoop-Cluster-04de4b26e5b1454fa2dcaf5f5c475d99#74dbd6c579334e78a7153860beb3d470)

Software: GCP DataProc, HiveQl, Zeppelin notebook, and  Git

# Hadoop Cluster
A 3-node Hadoop cluster is provisioned with Google Cloud Platform Dataproc.
<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/hadoop/assets/Hadoop_Architecture.PNG" alt=""/>
</p>
In the architecture, the client uses the browser to access the Zeppelin server that is running from the Google Cloud Hadoop cluster. All 
the HiveQl queries will be translated to MapReduce code that Hadoop can execute on its cluster, using YARN node manager and the data provided in its HDFS.

## Evaluated Big Data Tools
### MapReduce
MapReduce is a programming model for very large-scale data processing with a parallel, distributed algorithm on a cluster. To get advantage 
of the parallel processing that Hadoop provides, we express our query as a MapReduce job. MapReduce has three operations as Map, Shuffle, and Reduce.

- `Map:` In this phase, data in each split is passed to a mapping function to produce output values.

- `Shuffle:` This phase consumes the output of mapping phase and its task is to consolidate the relevant records from mapping phase output.

- `Reduce:` In this phase, output values from shuffling phase are aggregated and returns a single output value. In short, this phase summarizes the complete dataset.

### YARN
YARN is a a platform responsible for managing computing resources in clusters and using them for scheduling users' applications. YARN offers the ability to coordinate jobs across different nodes. The key components of YARN are ResourceManager(RM), NodeManager(NM) and ApplicationManger(AM). 

- `ResourceManager:' intracts with the NodeManager to keep an inventory of cluster-wide resource. It manages the availability and allocation of the resources thus gaining the ultimate authority of managing the resources.

- `NodeManager:` is for the purpose of memory management. Node Manager tracks the usage and status of the cluster inventories such as CPU, memory, and network on the local data server and reports the status regularly to the Resource Manager. A Node Manager daemon is assigned to every single data server. This holds the parallel programming in place.

`ApplicationManger:` is responsible for execution in parallel computing jobs. Its daemon is accountable for executing the job, monitoring the job for error, and completing the computer jobs.

### HDFS
HDFS is a distributed file system that handles large data sets running on commodity hardware. HDFS is one of the major components of Apache Hadoop. The components of HDFS are Blocks, Namenode, and Datanodes.

- `Block` HDFS breaks down a file into smaller units. This, however, is transparent to the user working on HDFS.  machine.
- `Namenode` is the master node that runs on a separate node in the cluster. It manages the filesystem namespace which is the filesystem tree or hierarchy of the files and directories. It also stores information like owners of files, file permissions, etc for all the files.
- `Datanode` is the worker node and is responsible for storing, retrieving, replicating, deletion, etc. of blocks when asked by the Namenode.  

### Hive 
Apache Hive is an open source data warehouse software for reading, writing and managing large data set files that are stored directly in either the Apache Hadoop Distributed File System (HDFS) or other data storage systems such as Apache HBase. Hive enables SQL developers to write Hive Query Language (HQL) statements that are similar to standard SQL statements for data query and analysis.  It is designed to make MapReduce programming easier because we don’t have to know and write lengthy Java code. Instead, we can write queries more simply in HQL, and Hive can then create the map and reduce the functions.

### Zeppelin
Apache Zeppelin is a web-based notebook that enables interactive data analytics. Interpreter is a pluggable layer for backend integration. 

### Hardware Specifications
- Master Node: 12GB memory, 100GB disk size
- 2 Worker Nodes: 12GB memory, 100GB disk size
- 4 YARN Cores: 12GB memory  

# Hive Project
We optimized HQL query using Hive partition feature and columnar file format.
- `Hive Partition:` We created the partitioned table by year. This action improves the performance for queries that involve year attribute.
- `Columnar File Format:` We created a table named `wdi_csv_parquet` and stored as `parquet`. This format stores data by column not by row and it allows for faster retrieval of columns of data.
- `SparkSQL:` Apache Spark is a fast and general-purpose cluster computing system. It is supported in Zeppelin with Spark interpreter. By using the Spark interpreter, the execution time is significantly reduced compared to the Hive Tez query.   

The Zeppelin Notebooke file of the hive project is accessible [here](https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/hadoop/assets/Jarvis_Hive_Project.png).
 

# Improvements
- Use appropriate file format on the basis of data like `ORCFILE` File Format. It will drastically increase our query performance.
- Use Bucketing in Hive, which allows the user to divide table data sets into more manageable parts.
- Use Hive Index to increase the query performance. Basically, for the original table, use of indexing will create a separate called index table which acts as a reference.
