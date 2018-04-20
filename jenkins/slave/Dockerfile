FROM openjdk:8u102-jdk

MAINTAINER manoharan rajesh

ENV JENKINS_SWARM_VERSION 3.12
ENV HOME /home/jenkins-slave

# install netstat to allow connection health check with
# netstat -tan | grep ESTABLISHED
RUN apt-get update && apt-get install -y net-tools git subversion python python-dev python-pip nodejs npm tar ssh curl bzip2 iptables && \
    rm -rf /var/lib/apt/lists/*


ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

# install maven
ENV MAVEN_VERSION=3.5.3
ENV M2_HOME=/usr/local/maven
RUN wget --directory-prefix=/tmp \
    http://mirrors.supportex.net/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
    tar xzf /tmp/apache-maven-${MAVEN_VERSION}-bin.tar.gz -C /usr/local && rm -rf /tmp/* && \
    cd /usr/local &&  ln -s apache-maven-${MAVEN_VERSION} maven && \
    ln -s /usr/local/maven/bin/mvn mvn

ENV MAVEN_HOME /usr/local/maven
# install gradle
ENV GRADLE_VERSION=4.2.1
ENV GRADLE_HOME=/usr/local/gradle
RUN wget --directory-prefix=/tmp \
    https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /usr/local && rm -rf /tmp/* && \
    cd /usr/local &&  ln -s gradle-${GRADLE_VERSION} gradle && \
    ln -s /usr/local/gradle/bin/gradle gradle

ENV GRADLE_HOME /usr/local/gradle


ENV PATH="${GRADLE_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"

RUN useradd -c "Jenkins Slave user" -d $HOME -m jenkins-slave
RUN curl --create-dirs -sSLo /usr/share/jenkins/swarm-client-$JENKINS_SWARM_VERSION.jar https://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/$JENKINS_SWARM_VERSION/swarm-client-$JENKINS_SWARM_VERSION.jar \
  && chmod 755 /usr/share/jenkins

COPY jenkins-slave.sh /usr/local/bin/jenkins-slave.sh

RUN chmod -R 777 /usr/local/bin/jenkins-slave.sh

USER jenkins-slave
VOLUME /home/jenkins-slave

ENTRYPOINT ["/usr/local/bin/jenkins-slave.sh"]