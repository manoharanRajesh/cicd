FROM jenkins/jenkins:lts

MAINTAINER manoharan rajesh

#COPY plugins.txt /usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# remove executors in master
COPY master-executors.groovy /usr/share/jenkins/ref/init.groovy.d/

