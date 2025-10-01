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

## Primer Prompt
Después de montar la clase de test *ProfileControllerTest*, dejamos que la IA cree las clases para pasar el test, con el siguiente prompt:
```
Dado este test, genera cada una de las clases que necesites para pasarlo. Recuerda que la implementación debe estar separada por capas, de controller a repository, además utiliza DTO's para abstraer cada capa
```
