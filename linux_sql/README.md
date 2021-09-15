# Introduction
The Jarvis Linux Cluster Administration (LCA) team manages a Linux cluster of 10 nodes/servers running the Linux distribution CentOS 7. These servers are internally connected via a switch and able to communicate through internal IPv4 addresses.

The team manages the servers, and they need to record the hardware specifications of each node, since
the servers have different aspects in terms of CPU, Memory or other characteristics. In addition, the servers monitor node resource usage (e.g. CPU/Memory) in real time.

The collected data need to be stored in an RDBMS database in order to prepare a future report for resource planning purposes.

As a developer in a dev team, I am responsible to set up a Monitoring agent on a node within the
Linux cluster to design and implement a Minimum Viable Product (MVP)to meet their business needs as follows:
- collect the hardware specifications
- monitor the cluster node resource usage in real time

This Monitoring Agent contains a PostgreSQL Server installed within a Docker Container that collects information submitted through bash scripts installed on each node. We also write SQL queries to answer some business questions.


### Technologies Used: 
PostgresSQL, SQL, [Google Cloud Platform](https://console.cloud.google.com/), Git, Docker, Bash, Linux(CentOS7)

# Quick Start

- Create a `psql` container using `psql_docker.sh` with the given `db_username` and `db_password`
  
`./Scripts/psql_docker.sh create db_username db_password`
- Start a `psql instance` using `psql_docker`

`./Scripts/psql_docker.sh start`
- Create tables using `ddl.sql`

`psql -h psql_host -p 5432 -U db_username -d host_agent -f ddl.sql`
- Insert node hardware data into the database, host_agent, using host_info.sh script
  
`./Scripts/host_info.sh  psql_host psql_port db_name psql_user psql_password`
- Insert hardware usage data into the DB using host_usage.sh
  
`./Scripts/host_usage.sh  psql_host psql_port db_name psql_user psql_password`
- Crontab setup to allow `host_usage.sh` to run every single minute
 
`crontab -e`

`* * * * * bash <path>/host_usage.sh psql_host psql_port db_name psql_user psql_password > /tmp/host_usage.log`

`crontab -l`

- Check hardware resource usage data using `queries.sql`
  
`psql -h psql_host -U psql_user -d db_name -f sql/queries.sql`

# Implemenation
`./Scripts/psql_docker.sh`

- Created a psql database using docker with the given username and password

`./sql/ddl.sql`

- Created `ddl.sql` to generate two tables storing hardware specifications and resource usage

`./Scripts/host_info.sh`
- Created bash script to parse hardware specifications

`./Scripts/host_usage.sh`
- Created bash script to parse resource usage data

- Used `crontab` to parse resource usage and send data to database every one minute.

`./sql/queries.sql`
- Created `queries.sql` to query data from database for usage information and failure detection.

## Architecture
<p align="center">
  <img src="https://github.com/halmasieh/-jarvis_data_eng_HomaAlmasieh/blob/develop/linux_sql/assets/Architecture-linux_sql%20(1).jpg" alt=""/>
</p>

## Scripts
- `psql_docker.sh` is a bash script that can create, start or stop a container as follows:
```
# Create a psql instance
./script/psql_docker.sh create [db_username] [db_password]

# Start the psql container
./script/psql_docker.sh start

# Stop the psql container
./script/psql_docker.sh stop
```
- `host_info.sh` is a bash script that collects the hardware specification data and insert it into the database

`./script/host_info.sh psql_host psql_port db_name psql_user psql_user psql_password`

- `host_usage.sh` is a bash script that collects resource usage data of a node and insert it into the database

`./script/host_usage.sh psql_host psql_port db_name psql_user psql_user psql_password`

- `Crontab` script is used to collect host_usage.sh every minute
```
#edit crontab jobs 
crontab -e 

#add this to crontab subsituting <path>
* * * * * bash <path>/host_usage.sh psql_host psql_port db_name psql_user psql_password > /tmp/host_usage.log
 
#list crontab jobs to verify process is running
crontab -l

#verify script is running by checking log file
cat /tmp/host_usage.log
```
- `./sql/ddl.sql` is SQL script used to create the `host_agent` database in postgreSQL and generate two tables `host_info` and `host_usage` in sql

```
#execute a sql file using psql command
psql -h psql_host -p 5432 -U psql_user -d db_name -f sql/ddl.sql
```
- `./sql/queries.sql` is a SQL script with 3 distinct queries  to answer 3 different business questions.

1. Group hosts by CPU number and sort by their total memory size in descending order
2. Find the average memory usage in percentage over a five-minute interval
3. Detect host failure for less than three data points within 5-min interval

```
#execute a sql file using psql command
psql -h psql_host -p 5432 -U psql_user -d db_name -f queries/ddl.sql
```
## Database Modeling
| Attribute  | Data Type   | Constraint    | Description                                     |
| :--------- | :---------: | :---------:   | :---------:                                     |
| `id`       | `SERIAL`    | `PRIMARY KEY` | Auto-incremented unique identifier of the node  |