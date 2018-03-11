## prerequisites
* Linux environment
* [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Building the project
```
./gradlew oneJar
```

## Running the CLI
```
./listCommits <url>
```
The URL can be an http or ssh url
Although you will have to set up your ssh keys for the ssh url to function
 

## TODO
* investigate why dropwizard config file is not affecting the log level when called with command