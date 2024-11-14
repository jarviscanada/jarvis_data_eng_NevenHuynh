# Linux Cluster Monitoring Agent
## Introduction
This project is an automated solution for system administrators to monitor server performance metrics across a network of Linux servers. It collects hardware specifications as well as resource usage data (e.g. CPU, memory, disk) in real-time and saves it in a relational database management system. This project is built using Bash scripts, Docker, PostgreSQL, Crontab, and Git.
## Quick Start
These steps will explain how to use the Linux Cluster Monitoring Agent
- Start up the psql instance using docker
```
./scripts/psql_docker.sh start
```
- Create the database tables
```
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```
- Insert hardware specifications into the DB
```
bash scripts/host_info.sh localhost 5432 host_agent postgres password
```
- Insert hardware usage data into the DB
```
bash scripts/host_usage.sh localhost 5432 host_agent postgres password
```
- Crontab setup to automate data insertion
```
# edit crontab tasks
crontab -e

# add host_usage.sh to crontab
* * * * * bash [/path_to_host_usage/host_usage.sh] localhost 5432 host_agent psotgres password > /tmp/host_usage.log
```
## Implementation 
This project uses Docker, Bash scripts, PostgreSQL, and Git to build a reliable Linux cluster monitoring system. Docker is employed to containerize the PostgreSQL database, making it easy to deploy and manage. Bash scripts are used to collect system information from each host which is then stored on the PostgreSQL database. Crontab schedules periodic data collection to automate the data collection process. Git is used for version control, enabling collaborative development and easy tracking of changes. 
### Architecture
![Cluster Diagram](./assets/Architecture.png) 

### Scripts 

