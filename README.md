## prerequisites
* Linux environment
* [Java 8 JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Docker](https://docs.docker.com/install/)
* [Docker Compose](https://docs.docker.com/compose/install)


## Building the project
```
./gradlew oneJar
```

## Starting the database
```
docker-compose up
```

## Running the CLI
```
./listCommits <url>
```
The URL can be an http or ssh url
Although you will have to set up your ssh keys for the ssh url to function
 
## Logging
Application logs are sent to ./log/gitviewer.log




## TODO
* investigate why dropwizard config file is not affecting the log level when called with command
* make the Git CLI CLient platform agnostic (currently works only with Linux)
* support for pipes in commit messages (risk of losing part of the title line)
* support for git branches, for now only getting the default branch
* Normalize data model and create new entities (Repository / Branch). The current model will have a lot of duplicated data (branch / repo). Indexes on branch and repository will not perform well and take up lot of space. And commit - branch relationship is many to many which currently forces us to store the same commits multiple times.
* some repositories seem to be braking our data model: org.postgresql.util.PSQLException: ERROR: value too long for type character varying(255
* Implement the API client 