FROM jpetazzo/dind

MAINTAINER manoharan rajesh

# build parameters
ARG JAVA_DISTRIBUTION=jdk
ARG JAVA_MAJOR_VERSION=8
ARG JAVA_UPDATE_VERSION=171
ARG JAVA_BUILD_NUMBER=11
ARG JAVA_HASH=512cd62ec5174c3487ac17c61aaa89e8
ARG BUILD_DATE=undefined

ENV JAVA_VERSION=1.${JAVA_MAJOR_VERSION}.0_${JAVA_UPDATE_VERSION}
ENV JAVA_HOME=/opt/java/${JAVA_DISTRIBUTION}${JAVA_VERSION}
ENV PATH=$PATH:$JAVA_HOME/bin

# install java
RUN apt-get update && \
    apt-get -y install wget && \
    export JAVA_TARBALL=${JAVA_DISTRIBUTION}-${JAVA_MAJOR_VERSION}u${JAVA_UPDATE_VERSION}-linux-x64.tar.gz && \
    if  [ -n "${JAVA_HASH}" ]; \
      then JAVA_HASH_EXTENTION='/'$JAVA_HASH'/' ; \
      else JAVA_HASH_EXTENTION= ; \
    fi && \
    wget --directory-prefix=/tmp \
         --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" \
         http://download.oracle.com/otn-pub/java/jdk/${JAVA_MAJOR_VERSION}u${JAVA_UPDATE_VERSION}-b${JAVA_BUILD_NUMBER}${JAVA_HASH_EXTENTION}/${JAVA_TARBALL} && \
    mkdir -p /opt/java && \
    tar -xzf /tmp/${JAVA_TARBALL} -C /opt/java/ && \
    if  [ "${JAVA_DISTRIBUTION}" = "server-jre" ]; \
      then mv /opt/java/jdk${JAVA_VERSION} ${JAVA_HOME} ; \
    fi && \
    ln -s ${JAVA_HOME}/bin/java /usr/bin/java && \
    apt-get -y autoremove  \
          wget && \
    apt-get autoclean && \
    rm -rf /tmp/* && rm -rf /var/log/*


# install netstat to allow connection health check with
# netstat -tan | grep ESTABLISHED
RUN apt-get update && apt-get install -y net-tools git subversion python python-dev python-pip tar ssh curl bzip2 unzip xz-utils iptables \
     && rm -rf /var/lib/apt/lists/*

ENV NODEJS_VERSION=v9.9.0 YARN_VERSION=1.5.1



RUN curl -SLO "https://nodejs.org/dist/$NODEJS_VERSION/node-$NODEJS_VERSION-linux-x64.tar.xz" \
    && tar -Jxvf "node-$NODEJS_VERSION-linux-x64.tar.xz" -C /usr/local --strip-components=1 --no-same-owner \
    && ln -s /usr/local/bin/node /usr/local/bin/nodejs \
    &&  rm "node-$NODEJS_VERSION-linux-x64.tar.xz" \
    && curl -fSLO --compressed "https://yarnpkg.com/downloads/$YARN_VERSION/yarn-v$YARN_VERSION.tar.gz" \
    && mkdir -p /opt \
    && tar -xzf yarn-v$YARN_VERSION.tar.gz -C /opt/ \
    && ln -s /opt/yarn-v$YARN_VERSION/bin/yarn /usr/local/bin/yarn \
    && ln -s /opt/yarn-v$YARN_VERSION/bin/yarnpkg /usr/local/bin/yarnpkg \
    && rm yarn-v$YARN_VERSION.tar.gz

ENV JENKINS_SWARM_VERSION 3.12
ENV HOME /home/jenkins-slave





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


