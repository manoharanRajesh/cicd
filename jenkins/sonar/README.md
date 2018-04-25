## Running Sonar 

```bash
$ docker-compose up
```

Restart the containers (after plugin upgrade or install for example).

```bash
$ docker-compose restart sonarqube
```

Analyse a project:

```bash
mvn sonar:sonar \
  -Dsonar.host.url=http://$(boot2docker ip):9000 \
  -Dsonar.jdbc.url=jdbc:postgresql://$(boot2docker ip)/sonar
```



* https://github.com/SonarSource/docker-sonarqube/blob/master/recipes.md

* https://hub.docker.com/_/sonarqube/

