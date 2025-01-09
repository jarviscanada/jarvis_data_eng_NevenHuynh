# Introduction
The Java Grep Application is a Java based tool that mimics the usage of the `grep` command in Linux. The app recursively searches a specified file directory for any files and searches each file for a line of text matching a specified regular expression. All matching lines are then outputted to an output file specified by the user. The application was dockerized as a Docker image, available on DockerHub. 

# Technologies 
- Java
- Maven
- Java Regex API
- Logging frameworks (log4j)
- Java IO API 
- Docker
- IntelliJ IDE

# Quick Start
To use the application, navigate to the core_java/grep directory and package the application
```bash
# Clean and compile the maven project and package it into a jar file
mvn clean package 
# Run the jar file using three arguments: regex, rootDirectory, and outputFile
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp [regex] [rootDirectory] [outputFile]
```

# Implementation
## Pseudocode
- Initialize an empty list of lines to store lines matching the regex pattern
- Search for files from the root path and store files in a list of files
- For each file in the list of files:
  - Read each line in a file
  - For each line in a file:
    - If the line matches the regex
      - Write the line to the list of lines
- Write the list of lines to the output file

## Performance Issue
As this program reads files and stores each line in memory, it faces memory issues when processing large files. To mitigate this issue, Java Streams and Lambda expressions can be used to avoid loading entire files into memory and instead, process files by each line at a time.

# Test
The program was tested manually using sample files to verify file searching, line reading and pattern matching.

# Deployment
The application was dockerized as a Docker image, available on DockerHub.
The steps to deploying the image are as follows:
1. Created a dockerfile with commands to set the working directory and copy the packaged jar file into the container
2. Packaged the application using maven to create a jar file 
3. Built a new local docker image using the dockerfile
4. Pushed the docker image to DockerHub

To pull and run the image from DockerHub
```bash
# Pull the image
docker/pull neven267/grep
# Run the container
docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log neven267/grep .[regex] /data /log/grep.out
```

# Improvement
- Provide a more user-friendly GUI as opposed to the current CLI usage
- Add support for searching across multiple root directories
- Adding performance metrics, such as the number of files processed and time taken for each file.
