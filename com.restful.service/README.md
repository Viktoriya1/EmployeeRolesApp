Required tools: Java 1.8 and higher, SpringBoot, Maven, and other libraries in pom.xml


1. Need to install all dependencies: mvn clean install -DskipTests
2. For running UNIT tests and generating JACOCO report:
 - mvn -Dgroups='com.restful.service.Categories$UnitTests' test
 - mvn jacoco:report  (report will be generated in target/site/jacoco/index.html)
3. For running INTEGRATION tests:
 - need to run SpringBoot app (by default on 8080 port)
 - mvn -Dgroups='com.restful.service.Categories$IntegrationTests' test