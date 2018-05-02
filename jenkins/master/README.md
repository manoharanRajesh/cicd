# docker-jenkins-swarm #

Docker image for Jenkins, with swarm plugin installed.
Based on the [official image](https://hub.docker.com/r/jenkins/jenkins/).

Can be used with Docker slaves from [`manoharanrajesh/jenkins-slave`](https://hub.docker.com/r/manoharanrajesh/jenkins-swarm-slave/)

# Running

    docker run --name jenkins -p 8080:8080 -p 50000:50000 -v /var/jenkins_home manoharanrajesh/jenkinsmaster:simple

##### OR USE [DOCKER_COMPOSE](https://github.com/manoharanRajesh/cicd/blob/master/jenkins/runner/README.md)

# Building

    docker build -t manoharanrajesh/jenkinsmaster:simple .
    
# Other Example
[jenkins-init](https://github.com/redfive/jenkins-init)
