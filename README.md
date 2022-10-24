# Spring Boot Graalvm Samples

Spring Boot Graalvm Samples

<img src="https://github.com/susimsek/spring-boot-graalvm-samples/blob/main/images/introduction.png" alt="Spring Boot Graalvm Samples" width="100%" height="100%"/> 

# GraalVM Native Images

GraalVM Native Images provide a new way to deploy and run Java applications. Compared to the Java Virtual Machine, native images can run with a smaller memory footprint and with much faster startup times.

A GraalVM Native Image is a complete, platform-specific executable. You do not need to ship a Java Virtual Machine in order to run a native image.

# Development

Before you can build this project, you must install and configure the following dependencies on your machine.

* Java 17
* GraalVM 22.3+
* Kotlin
* Maven 3.x
* Mongodb

## Build

To create the executable, run the following goal:

```
$ mvn native:compile -Pnative
```

Then, you can run the app as follows:

```
$ target/spring-boot-graalvm-samples
```

## Testing

You can run application's tests by typing the following command

```
mvn verify
```


## Code Quality

You can test code quality locally via sonarqube by typing the following command

```sh
mvn -Psonar compile initialize sonar:sonar
```

## Detekt

Detekt a static code analysis tool for the Kotlin programming language

You can run detekt by typing the following command

```sh
mvn antrun:run@detekt
```

## Docker

You can also fully dockerize  the native app. To achieve this, first build a docker image of your app.
The docker image of sample app can be built as follows:


```sh
mvn -Pnative spring-boot:build-image
```

## Deployment with Docker Compose

### Prerequisites for Docker Compose Deployment

* Docker
* Docker Compose

You can deploy app by running the following bash command


```sh
 sudo chmod +x deploy.sh
```

```sh
 ./deploy.sh -d
```

You can uninstall app the following bash command

```sh
 ./deploy.sh -d -r
```

The GraphQL App be accessed from the link below.  
http://127.0.0.1:9091


## Deployment Kubernetes with Helm

### Prerequisites for Kubernetes Deployment

* Kubernetes
* Helm

You can deploy app by running the following bash command

```sh
 sudo chmod +x deploy.sh
```

```sh
 ./deploy.sh -k
```

You can uninstall app the following bash command

```sh
 ./deploy.sh -k -r
```

You can upgrade the App (if you have made any changes to the generated manifests) by running the
following bash command

```sh
 ./deploy.sh -u
```

# Used Technologies
## Backend Side
* Java 17
* Kotlin
* GraalVM
* Upx
* Docker
* Docker Compose
* Kubernetes
* Helm
* Sonarqube
* Detekt
* Mongodb
* Spring Boot
* Spring Boot Web Flux
* Spring Boot Validation
* Spring Boot Actuator