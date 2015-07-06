[![Build Status](https://travis-ci.org/javadelight/delight-log.svg)](https://travis-ci.org/javadelight/delight-log)

# delight-log

Delight Log is a minimal implementation for asynchronous logging in Java.

**Why**: To provide a logging implementation works well in multi-threaded environments with minimal performance impact.

**What Not**: This library is very feature-poor by design to keep it maintainable and portable.  

This project is part of [Java Delight](https://github.com/javadelight/delight-main#java-delight-suite).

## Usage

### Creating a Log Repository

All logs are held in a log repository which can be created as follows.

```java
PropertyNode logs = Logs.create();
```

**Note**: Before the application terminates the log repository should always be finalized as follows to ascertain that
the thread used by the logging node is released.

```java
logs.stop().get();
```

### Log a Text Entry

The following will log a number of simple Strings.

```java
logs.record(Logs.string("log1", "entry 1"));
logs.record(Logs.string("log1", "entry 2"));
``` 

### Retrieving Logged Entries

The entries written in the previous example can be retrieved as follows:

```java
String log1 = logs.retrieve("log1", StringLog.class).get().toString();

System.out.println(log1);
```

This results in an output as follows:

```
[
    'entry1',
    'entry1'
]
```

### Preventing Log from Overflowing

By default individual logs are limited to 40 items. If this limit is reached, the oldest values will be purged.

A custom limit can be set by passing an integer to the logs constructor method:

```java
PropertyNode limitedLog = Logs.create(20);
```

## Maven Dependency

```xml
<dependency>
    <groupId>org.javadelight</groupId>
	<artifactId>delight-log</artifactId>
	<version>[latest version]</version>
</dependency>
```

Find latest version [here](http://modules.appjangle.com/async-log/latest/project-summary.html).

Add repository if required:

```xml
<repositories>
	<repository>
		<id>Appjangle Releases</id>
		<url>http://maven.appjangle.com/appjangle/releases</url>
	</repository>
</repositories>
```

## Compatibility

This project is compatible with the following environments:

- Java 1.6+
- GWT 2.5.0+
- Android (any)
- OSGi (any)

## Further Resources

- [JavaDocs](http://modules.appjangle.com/delight-log/latest/apidocs/)
- [Project Reports](http://modules.appjangle.com/delight-log/latest/project-reports.html)
- [Rendered README](http://documentup.com/javadelight/delight-log)


  

