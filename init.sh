sudo apt-get install git
sudo curl -L https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
git clone https://github.com/manoharanRajesh/cicd.git && cd cicd && git checkout dev && cd ./jenkins/runnerv1 && docker-compose up