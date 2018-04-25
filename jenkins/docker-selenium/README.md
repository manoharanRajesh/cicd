# Jenkins pipelines

Jenkins pipelines to use in Continuous Integration, Devivery and Deployment using the [Jenkins Pipeline plugin](https://wiki.jenkins.io/display/JENKINS/Pipeline+Plugin)


# Description

This pipeline executes Selenium tests against Chrome and Firefox, all running in separate docker containers and in parallel

# Pre-requisites

- [Install Docker](https://docs.docker.com/engine/installation/) and [add the server as slave to your Jenkins installation](https://devopscube.com/docker-containers-as-build-slaves-jenkins/) using the label `docker-slave`.
- [Docker Pipeline plugin](https://wiki.jenkins.io/display/JENKINS/Docker+Pipeline+Plugin)

# Instructions

- Create a new `Pipeline` job and configure your `SCM` containing the Jenkinsfile or clone this repo as an example.

# External resources
- [Selenium example](https://github.com/manoharanRajesh/simple-selenium-grid)

