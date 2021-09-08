#!/bin/bash

# command line arguments
cmd=$1
db_username=$2
db_password=$3
container_wc=$(docker container ls -a -f name=jrvs-psql | wc -l)

# check if docker is running using bitwise operator
sudo systemctl status docker || systemctl start docker

#if cmd=create, then create a docker container
if [ "${cmd}" == "create" ]; then

  #check if docker container exists
  if [ "container_wc" == 2 ]; then
    echo "Error, Docker container already exists."
  fi

#if username or password is not in the commands.
if [ "$#" -ne 3 ]; then
  echo "Error, login is failed."
fi

#create docker volume
docker volume create pgdata
docker run --name jrvs-psql -e POSTGRES_PASSWORD="${db_password}" -e POSTGRES_USER="${db_username}" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
exit $?

fi

#check if docker container has not been created
if [ "container_wc" != 2 ]; then
    echo "Error, Docker container does not exist."
    exit 1
fi

#check if the cmd is start, stop, or invalid.
case "${cmd}" in

  start)
    echo "docker container start jrvs_psql"
    exit $?
    ;;

  stop)
    echo "docker container stop jrvs_psql"
    exit $?
    ;;

  *)   echo "Error, the command is invalid"
    exit 1
    ;;
esac








