# Custom Layout Factory for Zipkin Modules

[![Gitter chat](http://img.shields.io/badge/gitter-join%20chat%20%E2%86%92-brightgreen.svg)](https://gitter.im/openzipkin/zipkin)
[![Build Status](https://github.com/openzipkin/zipkin-layout-factory/workflows/test/badge.svg)](https://github.com/openzipkin/zipkin-layout-factory/actions?query=workflow%3Atest)
[![Maven Central](https://img.shields.io/maven-central/v/io.zipkin.layout/zipkin-layout-factory.svg)](https://search.maven.org/search?q=g:io.zipkin.layout%20AND%20a:zipkin-layout-factory)

Zipkin Layout Factory is an assembly that allows extensions, such as custom collectors or storage implementations.

This repository includes a spring-boot-plugin layout which creates artifacts of type
module. spring-boot servers can load these using PropertiesLoader.

This plugin uses the Spring Boot's [CustomLayout Factory implementation](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/htmlsingle/#repackage-example-custom-layout).

## Usage

Include Spring boot maven plugin and modify the build task like below.

```xml
<plugin>
  <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<version>2.4.0</version>
	<configuration>
	    <layoutFactory implementation="zipkin.layout.ZipkinLayoutFactory">
		    <name>zipkin</name>
		</layoutFactory>
	</configuration>
	<dependencies>
	    <dependency>
		    <groupId>io.zipkin.layout</groupId>
			<artifactId>zipkin-layout-factory</artifactId>
			<version>0.1.0</version>
		</dependency>
	</dependencies>
</plugin>
```

## Artifacts
The artifact published is `zipkin-layout-factory` under the group ID `io.zipkin.layout`

### Library Releases
Releases are at [Sonatype](https://oss.sonatype.org/content/repositories/releases) and [Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.zipkin.layout%22)

### Library Snapshots
Snapshots are uploaded to [Sonatype](https://oss.sonatype.org/content/repositories/snapshots) after
commits to master.
