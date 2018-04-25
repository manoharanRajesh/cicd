# Jenkins swarm slave Docker in docker 

[`manoharanrajesh/jenkins-swarm-slave`](https://registry.hub.docker.com/r/manoharanrajesh/jenkins-swarm-slave/)

A [Jenkins swarm](https://wiki.jenkins-ci.org/display/JENKINS/Swarm+Plugin) slave.

For a container with ssh enabled see
[`manoharanrajesh/jenkins-slave`]()

## Running

To run a Docker container passing [any parameters](https://wiki.jenkins-ci.org/display/JENKINS/Swarm+Plugin#SwarmPlugin-AvailableOptions) to the slave

    docker run manoharanrajesh/jenkins-swarm-slave -master http://jenkins:8080 -username jenkins -password jenkins -executors 1

Linking to the Jenkins master container there is no need to use `--master`

    docker run -d --name jenkins -p 8080:8080 manoharanrajesh/jenkins-swarm
    docker run -d --link jenkins:jenkins manoharanrajesh/jenkins-swarm-slave:simple -username jenkins -password jenkins -executors 1

##### OR USE [DOCKER_COMPOSE](https://github.com/manoharanRajesh/cicd/blob/master/jenkins/runner/README.md)

# Building

    docker build -t manoharanrajesh/jenkins-swarm-slave:simple-docker .
    
    
# External resources
[docker in docker](https://hub.docker.com/r/jpetazzo/dind/)
[docker-jenkins-slave](https://hub.docker.com/r/yasn77/docker-jenkins-slave/)