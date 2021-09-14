#!/bin/bash

#command line arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#check the correct number of arguments
if [ "$#" -ne 5 ]; then
  echo "Error, host_info requires 5 arguments."
  exit 1
fi

# export password for psql instance (environment variable)
export PGPASSWORD="$psql_password"

#save number of CPU to a variable
lscpu_out=$(lscpu)

#save cat /proc/meminfo as mem_out
mem_out=$(cat /proc/meminfo)

#parse host hardware specifications and create data columns
#id=1, psql db auto-increment
#save hostname to a variable
hostname=$(hostname -f)

#note: `xargs` is a trick to remove leading and trailing white spaces
cpu_number=$(echo "$lscpu_out"  | grep "^CPU\(s\):" | awk '{print $2}' | xargs)

#print the cpu_architecture
cpu_architecture=$(echo "$lscpu_out"  | grep "Architecture:" | awk '{print $2}' | xargs)
# cpu_architecture =  x86_64

#print the cpu_model
cpu_model=$(echo "$lscpu_out"  | grep "Model:" | awk '{print $2}' | xargs)
# cpu_model =  63

#print the cpu_mhz
cpu_mhz=$(echo "$lscpu_out" | grep "CPU MHZ" | awk '{print $3}' | xargs)
# cpu_mhz = 2299.998

#print the l2_cache (KB)
l2_cache=$(echo "$lscpu_out" | grep "L2 cache:" | sed awk "{print $3}"  | xargs | sed 's/.$//')
# L2_cache = 256kB

#view MemTotal (KB)
total_mem=$(echo "$mem_out"| grep 'MemTotal:' | awk '{print $2}' | xargs)
# total_mem = 7489636 KB

#current timestamp in `2019-11-26 14:40:19` format
timestamp=$(date +"%Y-%m-%d %T")

# insert statement
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache,total_mem, timestamp)
VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp');"

# execute the INSERT statement through psql CLI tool
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"