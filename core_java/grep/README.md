# Introduction
The JavaGrep application is designed for pattern matching purposes in a dictionary.
The app takes three arguments and looks for a text pattern recursively in a 
dictionary and the lines that contain the text expressions 
are written and stored in a new file. This application is tested using 
the main method and JUnit testing. Lastly, the application is 
dockerized and uploaded to Docker Hub.

### Technologies Used:
Java 8, Maven, Stream APIs, Regex and Lambda expressions, Git, Docker

# Quick Start

This application takes the following variables:
- `regex`:  the regex for pattern matching
- `rootPath`: the root directory path
- `outFile`: the output file name
     
1- Run program using Jar file:
```
# compile and package the java code
Mvn clean package

# run jar file
java -jar target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp ${regex}  ${rootPath} ./out/${outfile}
```

2- Run program using the Docker image:
```
# pull Docker image from Docker Hub
Docker pull almasieh1358/grep

# run the container
docker run --rm \ -v `pwd`/data:/data -v `pwd`/log:/log \ almasieh1358/grep ${regex}  ${rootPath} ./out/${outfile}

```

# Implementation
## Pseudocode 
`process` method pseudocode
```
matchedLines = []                          // initialize a list of strings
for file in listFilesRecursively(rootDir)  // traverse a given directory and return all files
  for line in readLines(file)              // read a file and return all the lines
      if containsPattern(line)             // check if the line contains the regex pattern
        matchedLines.add(line)             // add the line to the output file  
writeToFile(matchedLines)                  // save the matched lines to the output file
```
## Performance Issue
This application may cause the `OutOfMemoryError` when 
processing huge data since the processed memory size is bigger than 
the one allocated for Heap memory. To alleviate this problem, 
it is recommended to update the application by replacing `List` 
with `Buffer` or `Stream` using JavaGrepLambda application.

# Test
The application was tested on Linux CentOS 7 virtual 
machine manually by using different sample data of three 
arguments  `regex`, `rootPath`, `outFile` 
as the variables of the program and the results were 
verified by comparing the output file and expected results.

# Deployment
- Build an `uber/fat jar` file includes both program classes and dependency classes by adding the Shade Plugin to the `pom.xml`.
- Execute `mvn clean package` to package the java app as a `fat Jar`.
- Build an image by reading instructions from the Dockerfile using ` docker build -t almasieh1358/grep . `
- Upload the image to Docker Hub using `docker push almasieh1358/grep`.

# Improvement
- Reconfigure the JVM to allocate more maximum memory (via `-Xmx30m `or similar)
- Use a stream API that does not load all the XML into the memory at once.
- Determine the source file and the number of each matched lines in the output file.