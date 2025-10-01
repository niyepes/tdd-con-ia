# TDD con IA

### Requisitos
* Java 21
* Testcontainers 1.20.4
```
<properties>
		<java.version>21</java.version>
		<testcontainers.version>1.20.4</testcontainers.version>
	</properties>
```

### Dependencias Necesarias
* Test Containers
```
  <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>testcontainers</artifactId>
            <scope>test</scope>
  </dependency>
```
* Jupiter JUnit
```
<dependency>
			<groupId>org.testcontainers</groupId>
			<artifactId>junit-jupiter</artifactId>
			<version>${testcontainers.version}</version>
			<scope>test</scope>
</dependency>
```
* Testcontainers PostgreSQL
```
<dependency>
			<groupId>org.testcontainers</groupId>
			<artifactId>postgresql</artifactId>
			<scope>test</scope>
</dependency>
```
