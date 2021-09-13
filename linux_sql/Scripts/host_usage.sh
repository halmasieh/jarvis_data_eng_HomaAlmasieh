#!/bin/bash

# collects usage data and inserts into psql instance

#command line arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#check the correct number of arguments
if [ "$#" -ne 5 ]; then
  echo "Error, host_usage requires 5 arguments."
  exit 1
fi

# export password for psql instance (environment variable)
export PGPASSWORD="$psql_password"

#parse host usage and create data columns
#save hostname to a variable
hostname=$(hostname -f)

#current timestamp in `2019-11-26 14:40:19` format
timestamp=$(date +"%Y-%m-%d %T")

#cresed host_id based on the unique hostname from host.info table
host_id=$(psql -h localhost -p 5432 -U postgres -d host_agent -c "SELECT id FROM host_info WHERE hostname='$hostname'" | sed -n "3p" | xargs)

#view MemFree
memory_free= $(cat /proc/meminfo | grep 'MemFree:' | awk '{print $2}' | xargs)
# Memory_free = 6395063 KB

#view cpu idle in percentage
cpu_idle=$(vmstat | grep -v 'cpu|id' | awk '{print $15}' | xargs)

#view cpu kernel in percentage
cpu_kernel=$(vmstat | grep -v 'cpu|sy' | awk '{print $14}' | xargs)

#view the number of disk io
disk_io=$(vmstat | grep -v 'io|bi' | awk '{print $9+$10}' | xargs)

#view disk available (root directory available disk
disk_available=$(df -BM / | egrep "^/dev/$da2" | awk '{print $4}' | sed 's/.$//' | xargs)

# insert statement
insert_stmt="INSERT INTO host_info (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES ('$timestamp', '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available);'

# execute the INSERT statement through psql CLI tool
psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$insert_stmt"