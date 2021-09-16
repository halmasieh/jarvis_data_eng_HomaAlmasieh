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
  
`./script/psql_docker.sh create db_username db_password`
- Start a `psql instance` using `psql_docker`

`./script/psql_docker.sh start`
- Create tables using `DDL.sql`

`psql -h psql_host -p 5432 -U db_username -d host_agent -f ddl.sql`
- Insert node hardware data into the database, host_agent, using host_info.sh script
  
`./script/host_info.sh  psql_host psql_port db_name psql_user psql_password`
- Insert hardware usage data into the DB using host_usage.sh
  
`./script/host_usage.sh  psql_host psql_port db_name psql_user psql_password`
- Crontab setup to allow `host_usage.sh` to run every single minute
 
`crontab -e`

`* * * * * bash <path>/host_usage.sh psql_host psql_port db_name psql_user psql_password > /tmp/host_usage.log`

`crontab -l`

- Check hardware resource usage data using `queries.sql`
  
`psql -h psql_host -U psql_user -d db_name -f sql/queries.sql`

# Implemenation
`./script/psql_docker.sh`

- Created a psql database using docker with the given username and password

`./sql/DDL.sql`

- Created `DDL.sql` to generate two tables storing hardware specifications and resource usage

`./script/host_info.sh`
- Created bash script to parse hardware specifications

`./script/host_usage.sh`
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
- `./sql/DDL.sql` is SQL script used to create the `host_agent` database in postgreSQL and generate two tables `host_info` and `host_usage` in sql

```
#execute a sql file using psql command
psql -h psql_host -p 5432 -U psql_user -d db_name -f sql/DDL.sql
```
- `./sql/queries.sql` is a SQL script with 3 distinct queries  to answer 3 different business questions.

1. Group hosts by CPU number and sort by their total memory size in descending order
2. Find the average memory usage in percentage over a five-minute interval
3. Detect host failure for less than three data points within 5-min interval

```
#execute a sql file using psql command
psql -h psql_host -p 5432 -U psql_user -d db_name -f sql/queries.sql
```
## Database Modeling
The schema for `host_info:`
| Attribute     | Data Type   | Constraint    | Description                                     |
| :---------    | :--------- | :---------   | :---------                                     |
| `id`          | `SERIAL`    | `PRIMARY KEY` | Auto-incremented unique identifier of the node  |
| `hostname`    | `VARCHAR`   | `UNIQUE`       | Name of the host computer                      |
| `cpu_number`      | `INT`       | `NOT NULL` | Number of CPUs on the host computer            |
| `cpu_architecture` | `VARCHAR`  | `NOT NULL` | Type of CPU architecture                       |
| `cpu_model` | `VARCHAR`  | `NOT NULL` | Type of CPU processor model                           |
| `cpu_mhz`   | `FLOAT`    | `NOT NULL` | Speed of CPU processor (in MHZ)                       |
| `l2_cache`  | `INT`      | `NOT NULL` | Size of the L2 cache (in kB)                          |
| `total_mem` | `INT`      | `NOT NULL` | Total memory on the host computer (in kB)                |
| `timestamp` | `TIMESTAMP`  | `NOT NULL` | The time of data entry (in YYYY-MM-DD HH:MM:SS format) |

The schema for `host_usage:`
| Attribute     | Data Type   | Constraint    | Description                                     |
| :---------    | :--------- | :---------   | :---------                                     |
| `timestamp` | `TIMESTAMP`  | `PRIMARY KEY`  | The time of data entry ( in YYYY-MM-DD HH:MM:SS format) <br /> Unique identifier of the node  |
| `host_id`   | `INT`          | `REFERENCES host_info(id)` | Foreign key referencing `id` attribute from `host_info`  |
| `memory_free`   | `INT`       | `NOT NULL`   | Total unused memory on the host computer (in kB)   |
| `cpu_idle`      | `INT`       | `NOT NULL` | Number of CPUs on the host computer            |
| `cpu_kernel`    | `INT`       | `NOT NULL` | CPU processor idle time (%)                    |
| `cpu_model`     | `INT`       | `NOT NULL` | CPU kernel run time (%)                        |
| `disk_io`       | `INT`       | `NOT NULL` | Number of disk I/O                             |
| `disk_available`  | `INT`     | `NOT NULL` | Total available disk space ( in mB)                |

# Test
All bash scripts `./script/psql_docker.sh`, `./script/host_info.sh`, `./script/host_usage.sh` have been tested manually. `./sql/DDL.sql` and `./sql/queries.sql` were executed individually to make sure the results are correct and queries work properly.

# Deployment
The project is built with bash scripts, the source code is hosted on Github.

# Improvements
- Create queries to answer more business questions
- Create a bash script to collect Linux usage data in different time intervals
- Send out alert when certain resource is overused           
- Add the option to remove a psql instance using psql_docker.sh 