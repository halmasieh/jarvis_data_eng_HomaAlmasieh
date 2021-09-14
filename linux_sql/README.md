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


